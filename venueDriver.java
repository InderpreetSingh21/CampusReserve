import java.util.*;

public class venueDriver {

    private static Scanner keyboard = new Scanner(System.in);
    public static HashMap<String, Venue> venues = new HashMap<>();
    public static Queue<Booking> bookingQueue = new LinkedList<>();
    public static BT clubBT = new BT();

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void showClubMenu() {
        clearTerminal();
        System.out.println("Enter your club name:");
        String clubName = keyboard.nextLine().toUpperCase();

        if (!clubBT.search(clubName)) {
            clubBT.insert(clubName, new LinkedList<>());
        }

        while (true) {
            clearTerminal();
            System.out.println(" - LOGED IN AS " + clubName + " - ");
            System.out.println("1. View All Available Venues");
            System.out.println("2. Book a Venue");
            System.out.println("4. View Our Bookings");
            System.out.println("5. Exit");

            String option = keyboard.nextLine();

            switch (option) {
                case "1":
                    for (String key : venues.keySet()) {
                        System.out.println(venues.get(key));
                    }
                    break;

                case "2":
                    System.out.println("Enter venue name:");
                    String venueName = keyboard.nextLine().toUpperCase();

                    if (venues.containsKey(venueName)) {
                        venues.get(venueName).showBookings();
                        System.out.println("Enter booking #:");
                        int bookingNum = keyboard.nextInt();
                        keyboard.nextLine();

                        Booking b = venues.get(venueName).bookings.get(bookingNum - 1);
                        if (b.clubName.equals("")) {
                            b.clubName = clubName;
                            b.approvedByAdmin = false;
                            bookingQueue.add(b);
                            venues.get(venueName).history.push("Booking pending by " + clubName);
                        } else {
                            System.out.println("Slot already taken.");
                        }
                    }
                    break;

                case "4":
                    BTNode node = clubBT.getTargetNode(clubName);
                    for (Booking booking : node.getBookingsList()) {
                        System.out.println(booking);
                    }
                    break;

                case "5":
                    return;
            }

            System.out.println("Press Enter to continue...");
            keyboard.nextLine();
        }
    }

    public static void showAdminMenu() {
        while (true) {
            clearTerminal();
            System.out.println(" - ADMIN MENU - ");
            System.out.println("1. View Venues");
            System.out.println("2. Add Venue");
            System.out.println("3. Remove Venue");
            System.out.println("4. Approve Bookings");
            System.out.println("5. Show History");
            System.out.println("6. List Reserved Times");
            System.out.println("7. Exit");

            String option = keyboard.nextLine();

            switch (option) {
                case "2":
                    Venue v = new Venue();
                    v.createVenue();
                    venues.put(v.name, v);
                    break;

                case "4":
                    for (Booking b : bookingQueue) {
                        System.out.println(b);
                        System.out.println("Approve? (Y/N)");
                        if (keyboard.nextLine().equalsIgnoreCase("y")) {
                            b.approvedByAdmin = true;
                            clubBT.getTargetNode(b.clubName).getBookingsList().add(b);
                        }
                    }
                    bookingQueue.clear();
                    break;

                case "7":
                    return;
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            clearTerminal();
            System.out.println("Login as Club (C) or Admin (A)?");
            String user = keyboard.nextLine().toLowerCase();

            if (user.equals("c")) showClubMenu();
            else if (user.equals("a")) showAdminMenu();
        }
    }
}

