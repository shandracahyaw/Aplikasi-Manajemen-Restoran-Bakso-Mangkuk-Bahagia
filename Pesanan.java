import java.util.ArrayList; 
import java.io.*; 

public class Pesanan { 
    private ArrayList<MenuItem> items; 
    private String namaPelanggan; 
    private DiskonPromo appliedDiskon; // Tambahkan atribut ini 
    private static final String FILE_STRUK = "struk_pesanan.txt"; // Ubah nama konstanta agar tidak rancu dengan file menu 

    public Pesanan(String namaPelanggan) { 
        this.namaPelanggan = namaPelanggan; 
        this.items = new ArrayList<>(); 
        this.appliedDiskon = null; // Inisialisasi diskon yang diterapkan 
    } 

    public void tambahItem(MenuItem item) { 
        items.add(item); 
    } 

    // Metode baru untuk menerapkan diskon promo 
    public void setDiskonPromo(DiskonPromo diskon) { 
        this.appliedDiskon = diskon; 
    } 

    public double hitungTotal() { 
        double subtotal = 0; 
        for (MenuItem item : items) { 
            subtotal += item.getHarga(); // Hanya jumlahkan harga item 
        } 

        double totalSetelahDiskon = subtotal; 
        if (appliedDiskon != null) { 
            totalSetelahDiskon -= appliedDiskon.hitungDiskon(subtotal); 
        } 
        return Math.max(0, totalSetelahDiskon); // Pastikan total tidak negatif 
    } 

    public void tampilkanStruk() { 
        System.out.println("\n============STRUK PESANAN============"); 
        System.out.println("Pelanggan: " + namaPelanggan); 
        System.out.println("------------------------"); 

        for (int i = 0; i < items.size(); i++) { 
            System.out.print((i+1) + ". "); 
            items.get(i).tampilMenu(); // Polymorphism tetap berlaku untuk item 
        } 

        if (appliedDiskon != null) { 
            System.out.printf("Diskon Promo: %s (%.0f%%) - Rp %.0f%n", 
                              appliedDiskon.getKode(), 
                              appliedDiskon.getPersenDiskon(), 
                              appliedDiskon.hitungDiskon(calculateSubtotalRaw())); // Hitung diskon dari subtotal murni untuk tampilan 
        } 

        System.out.printf("Total: Rp %.0f%n", hitungTotal()); 
        System.out.println("========================"); 
        simpanStrukKeFile(); 
    } 

    // Helper method untuk menghitung subtotal tanpa diskon promo 
    private double calculateSubtotalRaw() { 
        double subtotal = 0; 
        for (MenuItem item : items) { 
            subtotal += item.getHarga(); 
        } 
        return subtotal; 
    } 

    private void simpanStrukKeFile() { 
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_STRUK, true))) { 
            writer.println("=== STRUK PESANAN " + java.time.LocalDateTime.now() + " ==="); 
            writer.println("Pelanggan: " + namaPelanggan); 
            for (MenuItem item : items) { 
                writer.println(item.getNama() + " - Rp " + item.getHarga()); 
            } 
            if (appliedDiskon != null) { 
                writer.printf("Diskon Promo: %s (%.0f%%) - Rp %.0f%n", 
                              appliedDiskon.getKode(), 
                              appliedDiskon.getPersenDiskon(), 
                              appliedDiskon.hitungDiskon(calculateSubtotalRaw())); 
            } 
            writer.println("Total: Rp " + hitungTotal()); 
            writer.println("=============================================="); 
            writer.flush(); 
        } catch (IOException e) { 
            System.err.println("Error menyimpan struk: " + e.getMessage()); 
        } 
    } 

    public ArrayList<MenuItem> getItems() { return items; } 
}