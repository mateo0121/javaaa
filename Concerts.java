package mz.proizvod;


public class Proizvod {
    
    private static int proizvodCount = 0;
    
    private final String sifra;
    private String naziv;
    private double cijena;
    private int kolicina;
   
    public Proizvod(String naziv, double cijena, int kolicina) {
        setNaziv(naziv);
        setCijena(cijena);
        checkKolicina(kolicina);
        this.kolicina = kolicina;
        
        proizvodCount++;
        this.sifra = "P" + String.format("%03d", proizvodCount);
    }
   
    public Proizvod(String naziv, double cijena) {
        this(naziv, cijena, 0);
    }
   
    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public static int getProizvodCount() {
        return proizvodCount;
    }

    public void setNaziv(String naziv) {
        if (naziv == null || naziv.isBlank()) {
            throw new IllegalArgumentException("Naziv ne smije biti prazan.");
        }
        this.naziv = naziv.strip();
    }

    public void setCijena(double cijena) {
        if (cijena < 0) {
            throw new IllegalArgumentException("Cijena ne smije biti negativna.");
        }
        this.cijena = cijena;
    }
   
    private void checkKolicina(int kolicina) {
        if (kolicina < 0) {
            throw new IllegalArgumentException("Količina ne smije biti negativna.");
        }
    }
   
    public void dodaj(int kolicinaZaDodati) {
        if (kolicinaZaDodati <= 0) {
            throw new IllegalArgumentException("Količina za dodavanje mora biti pozitivna.");
        }
        this.kolicina += kolicinaZaDodati;
    }
   
    public void prodaj(int kolicinaZaProdati) {
        if (kolicinaZaProdati <= 0) {
            throw new IllegalArgumentException("Količina za prodaju mora biti pozitivna.");
        }
        if (kolicinaZaProdati > this.kolicina) {
            throw new IllegalArgumentException("Količina ne smije pasti ispod nule.");
        }
        this.kolicina -= kolicinaZaProdati;
    }
   
    public double ukupnaVrijednost() {
        return this.kolicina * this.cijena;
    }
   
    public double ukupnaVrijednost(double popust) {
        double osnovnaVrijednost = ukupnaVrijednost();
        return osnovnaVrijednost * (1 - popust / 100.0);
    }
   
    public String opis() {
        return String.format("%s | %s | %.2f EUR | %d kom.", sifra, naziv, cijena, kolicina);
    }
}
