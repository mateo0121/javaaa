package hr.obskc_toplak.concerts;

public class Concerts {
 
    public static void main(String[] args) {
        Concert first = new Concert ("Prljavo kazalište", 25.00, 120);
        Concert second = new Concert ("Gibonni", 32.50, 80);
       
        System.out.println(first.description());
        System.out.println(second.description());
       
        Concert third = new Concert("Hladno pivo", 20);
        System.out.println(third.description());
       
        first.reserve(20);
        third.cancel(5);
        second.setTicketPrice(30);
        System.out.println(first.getArtist() + ": slobodno još " + first.getFreeSeats() + " mjesta");
       
        try {
            second.reserve(1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
       
        try {
            new Concert("    ", 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
       
        try {
            third.setTicketPrice(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Odbijeno: " + e.getMessage());
        }
         
        System.out.printf("Preostala zarada za (%s): %.2f EUR%n", second.getArtist(), second.remainigRevenue());
        System.out.printf("Preostala zarada uz 20 %% popusta (%s): %.2f EUR%n", second.getArtist(), second.remainigRevenue(20));
       
        System.out.println("Broj koncerata: " + Concert.getConcertCount());
        System.out.println("Zadani kapacitet: " + Concert.DEFAULT_CAPACITY);
        System.out.println("Je li K042 ispravna šifra? " + Concert.isValidCode("K042"));
        System.out.println("Je li X042 ispravna šifra? " + Concert.isValidCode("X042"));
       
        Concert[] program = {first, second, third};
        int totalFreeSeats = 0;
        for (Concert c : program) {
            totalFreeSeats += c.getFreeSeats();
        }
        System.out.println("Ukupno slobodnih mjesta: " + totalFreeSeats);
    }
}
