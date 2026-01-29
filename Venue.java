import java.util.*;

public class Venue {
    public String name;
    public int startTime;
    public int endTime;
    public ArrayList<Booking> bookings;
    public Stack<String> history;

    public Venue() {
        bookings = new ArrayList<>();
        history = new Stack<>();
    }

    public void createVenue() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Venue name:");
        name = kb.nextLine().toUpperCase();
        System.out.println("Opening time:");
        startTime = kb.nextInt();
        System.out.println("Closing time:");
        endTime = kb.nextInt();

        history.push("Venue created: " + name);

        for (int i = startTime; i < endTime; i += 100) {
            bookings.add(new Booking(this, i, i + 100, "", true));
        }
    }

    public void showBookings() {
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println("BOOKING #" + (i + 1) + ": " + bookings.get(i));
        }
    }

    public void showHistory() {
        for (String h : history) {
            System.out.println(h);
        }
    }

    public String toString() {
        return name + " (" + startTime + " - " + endTime + ")";
    }
}
