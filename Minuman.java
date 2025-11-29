public class Minuman extends MenuItem { 
    private String jenisMinuman; // atribut tambahan 
     
    public Minuman(String nama, double harga, String jenisMinuman) { 
        super(nama, harga, "Minuman"); 
        this.jenisMinuman = jenisMinuman; 
    } 
     
    @Override 
    public void tampilMenu() { 
        System.out.printf("%s - Rp %.0f (Jenis: %s)%n",  
                         getNama(), getHarga(), jenisMinuman); 
    } 
     
    public String getJenisMinuman() { return jenisMinuman; } 
}