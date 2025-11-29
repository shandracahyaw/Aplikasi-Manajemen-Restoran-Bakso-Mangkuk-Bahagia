public class Makanan extends MenuItem { 
    private String jenisMakanan; // atribut tambahan 
     
    public Makanan(String nama, double harga, String jenisMakanan) { 
        super(nama, harga, "Makanan"); 
        this.jenisMakanan = jenisMakanan; 
    } 
     
    @Override 
    public void tampilMenu() { 
        System.out.printf("%s - Rp %.0f (Jenis: %s)%n",  
                         getNama(), getHarga(), jenisMakanan); 
    } 
     
    public String getJenisMakanan() { return jenisMakanan; } 
}