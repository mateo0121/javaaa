package hr.obskc_toplak.concerts;

public class Concert {
    
    // podaci na razini klase - korisni za sve objekte na jednom mjestu
    public static final int DEFAULT_CAPACITY = 100;
    private static int concertCount = 0;
    
    // osnovni podaci - podaci svakog objekta pojedinačno
    private final String code;
    private String artist;
    private double ticketPrice;
    private int freeSeats;
   
    // konstruktor
    public Concert(String artist, double ticketPrice, int freeSeats) {
        setArtist(artist);
        setTicketPrice(ticketPrice);
        checkCount(freeSeats); // neovisna provjera bez settera
        this.freeSeats = freeSeats;
        
        concertCount++; // registriramo novi koncert
        this.code = "K" + String.format("%03d", concertCount); // npr. K + 001 = K001
    }
   
    // skraćeni konstruktor koji koristi "glavni" konstruktor
    public Concert(String artist, double ticketPrice){
        this(artist, ticketPrice, DEFAULT_CAPACITY);
    }
   
    // getteri
    public String getCode() {
        return code;
    }

    public String getArtist() {
        return artist;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public static int getConcertCount() {
        return concertCount;
    }

    // setteri
    public void setArtist(String artist) {
        if (artist == null || artist.isBlank()){
            throw new IllegalArgumentException("Izvođač ne smije biti prazan.");
        }
        this.artist = artist.strip();
    }

    public void setTicketPrice(double ticketPrice) {
        if (ticketPrice < 0) {
            throw new IllegalArgumentException("Cijena ulaznice ne može biti negativna");
        }
        this.ticketPrice = ticketPrice;
    }
   
    // samostalna validacijska funkcija
    private void checkCount(int count) {
        if(count < 0) {
            throw new IllegalArgumentException("Broj ne može biti negativan");
        }
    }
    
    // DODANE METODE KOJE SU NEDOSTAJALE:
    
    public String description() {
        return String.format("[%s] %s - %.2f EUR (%d slobodnih mjesta)", code, artist, ticketPrice, freeSeats);
    }
    
    public void reserve(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Broj karata za rezervaciju mora biti pozitivan.");
        }
        if (n > freeSeats) {
            throw new IllegalArgumentException("Nema dovoljno slobodnih mjesta.");
        }
        freeSeats -= n;
    }
    
    public void cancel(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Broj karata za otkazivanje mora biti pozitivan.");
        }
        freeSeats += n;
    }
    
    public double remainigRevenue() {
        return freeSeats * ticketPrice;
    }
    
    public double remainigRevenue(double discountPercentage) {
        double discountedPrice = ticketPrice * (1 - discountPercentage / 100.0);
        return freeSeats * discountedPrice;
    }
    
    public static boolean isValidCode(String code) {
        if (code == null || !code.startsWith("K") || code.length() < 2) {
            return false;
        }
        // Provjera jesu li znakovi nakon 'K' brojevi
        try {
            Integer.parseInt(code.substring(1));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
