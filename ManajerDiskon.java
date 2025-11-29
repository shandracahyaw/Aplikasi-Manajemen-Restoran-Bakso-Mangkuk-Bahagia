import java.io.*; 
import java.util.ArrayList; 

public class ManajerDiskon { 
    private ArrayList<DiskonPromo> daftarDiskonPromo; 
    private static final String FILE_DISKON = "diskon_promo.txt"; 

    public ManajerDiskon() { 
        daftarDiskonPromo = new ArrayList<>(); 
        loadDiskonFromFile(); 
    } 

    public void tambahDiskon(DiskonPromo diskon) { 
        // Cek duplikasi kode 
        for (DiskonPromo dp : daftarDiskonPromo) { 
            if (dp.getKode().equalsIgnoreCase(diskon.getKode())) { 
                System.out.println("Kode diskon sudah ada."); 
                return; 
            } 
        } 
        daftarDiskonPromo.add(diskon); 
        System.out.println("Diskon promo berhasil ditambahkan: " + diskon.getKode()); 
        saveDiskonToFile(); 
    } 

    public DiskonPromo getDiskonByKode(String kode) { 
        for (DiskonPromo diskon : daftarDiskonPromo) { 
            if (diskon.getKode().equalsIgnoreCase(kode)) { 
                return diskon; 
            } 
        } 
        return null; // Tidak ditemukan 
    } 

    public void tampilkanDaftarDiskon() { 
        System.out.println("\n============DAFTAR DISKON PROMO============"); 
        if (daftarDiskonPromo.isEmpty()) { 
            System.out.println("Belum ada diskon promo yang tersedia."); 
            return; 
        } 
        for (int i = 0; i < daftarDiskonPromo.size(); i++) { 
            System.out.println((i + 1) + ". " + daftarDiskonPromo.get(i).toString()); 
        } 
    } 

    // File I/O for DiskonPromo 
    private void saveDiskonToFile() { 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_DISKON))) { 
            oos.writeObject(daftarDiskonPromo); 
        } catch (IOException e) { 
            System.err.println("Error menyimpan diskon promo: " + e.getMessage()); 
        } 
    } 

    @SuppressWarnings("unchecked") 
    private void loadDiskonFromFile() { 
        File file = new File(FILE_DISKON); 
        if (!file.exists()) return; 

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { 
            daftarDiskonPromo = (ArrayList<DiskonPromo>) ois.readObject(); 
        } catch (IOException | ClassNotFoundException e) { 
            System.err.println("Error memuat diskon promo: " + e.getMessage()); 
        } 
    } 
}
