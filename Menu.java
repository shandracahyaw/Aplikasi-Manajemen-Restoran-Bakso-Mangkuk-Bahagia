import java.util.ArrayList; 
import java.io.*; 
 
public class Menu { 
    private ArrayList<MenuItem> daftarMenu; 
    private static final String FILE_MENU = "menu_restoran.txt"; 
     
    public Menu() { 
        daftarMenu = new ArrayList<>(); 
        loadFromFile(); 
    } 
     
    public void tambahMenuItem(MenuItem item) { 
        daftarMenu.add(item); 
        System.out.println("Item berhasil ditambahkan: " + item.getNama()); 
        saveToFile(); 
    } 
     
    public void tampilkanMenu() { 
        System.out.println("\n============DAFTAR MENU RESTORAN============"); 
        if (daftarMenu.isEmpty()) { 
            System.out.println("Menu masih kosong!"); 
            return; 
        } 
         
        for (int i = 0; i < daftarMenu.size(); i++) { 
            System.out.print((i+1) + ". "); 
            daftarMenu.get(i).tampilMenu(); // Polymorphism 
        } 
    } 
     
    public MenuItem cariMenu(int index) { 
        if (index < 1 || index > daftarMenu.size()) { 
            throw new IllegalArgumentException("Item menu tidak ditemukan!"); 
        } 
        return daftarMenu.get(index - 1); 
    } 
     
    public ArrayList<MenuItem> getDaftarMenu() { 
        return daftarMenu; 
    } 
     
    // File I/O Operations 
    private void saveToFile() { 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_MENU))) { 
            oos.writeObject(daftarMenu); 
        } catch (IOException e) { 
            System.err.println("Error menyimpan menu: " + e.getMessage()); 
        } 
    } 
     
    @SuppressWarnings("unchecked") 
    private void loadFromFile() { 
        File file = new File(FILE_MENU); 
        if (!file.exists()) return; 
         
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) { 
            daftarMenu = (ArrayList<MenuItem>) ois.readObject(); 
        } catch (IOException | ClassNotFoundException e) { 
            System.err.println("Error memuat menu: " + e.getMessage()); 
        } 
    } 
}