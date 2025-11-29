import java.io.Serializable; 
 
public abstract class MenuItem implements Serializable { 
    private String nama; 
    private double harga; 
    private String kategori; 
     
    public MenuItem(String nama, double harga, String kategori) { 
        this.nama = nama; 
        this.harga = harga; 
        this.kategori = kategori; 
    } 
     
    // Encapsulation - getter methods 
    public String getNama() { return nama; } 
    public double getHarga() { return harga; } 
    public String getKategori() { return kategori; } 
     
    // Setter dengan validasi 
    public void setHarga(double harga) { 
        if (harga < 0) throw new IllegalArgumentException("Harga tidak boleh negatif"); 
        this.harga = harga; 
    } 
     
    // Metode abstrak untuk polymorphism 
    public abstract void tampilMenu(); 
}