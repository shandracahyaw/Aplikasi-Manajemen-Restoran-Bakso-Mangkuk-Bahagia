import java.io.Serializable; 

public class DiskonPromo implements Serializable { 
    private String kode; 
    private double persenDiskon; // Dalam persentase, misal 10 untuk 10% 
    private String deskripsi; 

    public DiskonPromo(String kode, double persenDiskon, String deskripsi) { 
        this.kode = kode; 
        if (persenDiskon < 0 || persenDiskon > 100) { 
            throw new IllegalArgumentException("Persentase diskon harus antara 0-100."); 
        } 
        this.persenDiskon = persenDiskon; 
        this.deskripsi = deskripsi; 
    } 

    public String getKode() { return kode; } 
    public double getPersenDiskon() { return persenDiskon; } 
    public String getDeskripsi() { return deskripsi; } 

    public double hitungDiskon(double subtotal) { 
        return subtotal * (persenDiskon / 100); 
    } 

    @Override 
    public String toString() { 
        return String.format("Diskon Promo '%s': %.0f%% - %s", kode, persenDiskon, deskripsi); 
    } 
}
