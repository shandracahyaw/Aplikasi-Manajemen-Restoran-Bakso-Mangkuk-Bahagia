import java.util.Scanner; 

public class Main { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        Menu menuRestoran = new Menu(); 
        ManajerDiskon manajerDiskon = new ManajerDiskon(); // Inisialisasi ManajerDiskon 
        Pesanan pesananSaatIni = null; // Menyimpan pesanan yang sedang diproses 

        System.out.println("SELAMAT DATANG DI MANAJEMEN RESTORAN BAKSO MANGKUK BAHAGIA"); 

        while (true) { 
            tampilkanMenuUtama(); 
            int pilihan = scanner.nextInt(); 
            scanner.nextLine(); // consume newline 

            try { 
                switch (pilihan) { 
                    case 1: 
                        tambahItemMenu(menuRestoran, scanner); 
                        break; 
                    case 2: 
                        menuRestoran.tampilkanMenu(); 
                        break; 
                    case 3: // Opsi baru: Kelola Diskon Promo 
                        kelolaDiskonPromo(manajerDiskon, scanner); 
                        break; 
                    case 4: // Buat Pesanan (nomor opsi berubah) 
                        pesananSaatIni = prosesPesanan(menuRestoran, manajerDiskon, scanner); 
                        break; 
                    case 5: // Tampilkan Struk (nomor opsi berubah) 
                        if (pesananSaatIni != null) { 
                            pesananSaatIni.tampilkanStruk(); 
                        } else { 
                            System.out.println("Belum ada pesanan yang sedang diproses!"); 
                        } 
                        break; 
                    case 6: // Keluar (nomor opsi berubah) 
                        System.out.println("Terima kasih telah menggunakan aplikasi Mangkuk Bahagia!"); 
                        return; 
                    default: 
                        System.out.println("Pilihan tidak valid!"); 
                } 
            } catch (Exception e) { 
                System.out.println("Error: " + e.getMessage()); 
            } 

            System.out.print("\nTekan Enter untuk melanjutkan..."); 
            scanner.nextLine(); 
        } 
    } 

    private static void tampilkanMenuUtama() { 
        System.out.println("\n============DAFTAR MENU UTAMA============"); 
        System.out.println("1. Tambah Item Menu Restoran"); 
        System.out.println("2. Tampilkan Menu Restoran"); 
        System.out.println("3. Kelola Diskon Promo");
        System.out.println("4. Buat Pesanan Baru"); 
        System.out.println("5. Tampilkan Struk Pesanan Terakhir"); 
        System.out.println("6. Keluar"); 
        System.out.print("Pilih menu [1-6]: "); 
    } 

    private static void tambahItemMenu(Menu menuRestoran, Scanner scanner) { 
        System.out.println("\n=== TAMBAH ITEM MENU ==="); 
        System.out.print("Nama item: "); 
        String nama = scanner.nextLine(); 
        System.out.print("Harga: Rp "); 
        double harga = scanner.nextDouble(); 
        scanner.nextLine(); 

        System.out.println("Jenis item:"); 
        System.out.println("1. Makanan | 2. Minuman"); // Hapus opsi Diskon 
        int jenis = scanner.nextInt(); 
        scanner.nextLine(); 

        MenuItem item = null; 
        switch (jenis) { 
            case 1: 
                System.out.print("Jenis makanan (bakso/mie/etc): "); 
                String jenisMakanan = scanner.nextLine(); 
                item = new Makanan(nama, harga, jenisMakanan); 
                break; 
            case 2: 
                System.out.print("Jenis minuman (kopi/jus/etc): "); 
                String jenisMinuman = scanner.nextLine(); 
                item = new Minuman(nama, harga, jenisMinuman); 
                break; 
            default: 
                System.out.println("Jenis item tidak valid."); 
        } 

        if (item != null) { 
            menuRestoran.tambahMenuItem(item); 
        } 
    } 

    // Metode baru untuk mengelola diskon promo 
    private static void kelolaDiskonPromo(ManajerDiskon manajerDiskon, Scanner scanner) { 
        while (true) { 
            System.out.println("\n============KELOLA DISKON PROMO============"); 
            System.out.println("1. Tambah Diskon Promo Baru"); 
            System.out.println("2. Tampilkan Semua Diskon Promo"); 
            System.out.println("3. Kembali ke Menu Utama"); 
            System.out.print("Pilih menu [1-3]: "); 
            int pilihan = scanner.nextInt(); 
            scanner.nextLine(); 

            switch (pilihan) { 
                case 1: 
                    System.out.print("Kode Diskon: "); 
                    String kode = scanner.nextLine(); 
                    System.out.print("Persentase Diskon (0-100%): "); 
                    double persen = scanner.nextDouble(); 
                    scanner.nextLine(); 
                    System.out.print("Deskripsi Diskon: "); 
                    String deskripsi = scanner.nextLine(); 
                    try { 
                        DiskonPromo newDiskon = new DiskonPromo(kode, persen, deskripsi); 
                        manajerDiskon.tambahDiskon(newDiskon); 
                    } catch (IllegalArgumentException e) { 
                        System.out.println("Error: " + e.getMessage()); 
                    } 
                    break; 
                case 2: 
                    manajerDiskon.tampilkanDaftarDiskon(); 
                    break; 
                case 3: 
                    return; 
                default: 
                    System.out.println("Pilihan tidak valid!"); 
            } 
            System.out.print("\nTekan Enter untuk melanjutkan..."); 
            scanner.nextLine(); 
        } 
    } 

    private static Pesanan prosesPesanan(Menu menuRestoran, ManajerDiskon manajerDiskon, Scanner scanner) { 
        System.out.print("Nama pelanggan: "); 
        String namaPelanggan = scanner.nextLine(); 
        Pesanan pesananBaru = new Pesanan(namaPelanggan); 

        menuRestoran.tampilkanMenu(); 
        while (true) { 
            System.out.print("Nomor item (0 untuk selesai): "); 
            int nomorItem = scanner.nextInt(); 
            scanner.nextLine(); 

            if (nomorItem == 0) break; 

            try { 
                MenuItem item = menuRestoran.cariMenu(nomorItem); 
                pesananBaru.tambahItem(item); // Tambahkan item ke pesanan 
                System.out.println("Item ditambahkan!"); 
            } catch (IllegalArgumentException e) { 
                System.out.println("Error" + e.getMessage()); 
            } 
        } 

        // Tawarkan untuk menerapkan diskon promo 
        System.out.print("Apakah pelanggan memiliki kode diskon? (ya/tidak): "); 
        String pakaiDiskon = scanner.nextLine(); 
        if (pakaiDiskon.equalsIgnoreCase("ya")) { 
            manajerDiskon.tampilkanDaftarDiskon(); // Tampilkan diskon yang tersedia agar pengguna tahu kode yang ada 
            System.out.print("Masukkan Kode Diskon: "); 
            String kodeDiskon = scanner.nextLine(); 
            DiskonPromo diskonDitemukan = manajerDiskon.getDiskonByKode(kodeDiskon); 
            if (diskonDitemukan != null) { 
                pesananBaru.setDiskonPromo(diskonDitemukan); 
                System.out.println("Diskon promo '" + kodeDiskon + "' berhasil diterapkan!"); 
            } else { 
                System.out.println("Kode diskon tidak ditemukan atau tidak valid."); 
            } 
        } 

        return pesananBaru; 
    } 
}