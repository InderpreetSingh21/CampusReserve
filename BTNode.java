import java.util.LinkedList;

class BTNode {
    BTNode left, right;
    Object[] data;

    public BTNode(String club, LinkedList<Booking> bookings) {
        data = new Object[]{club, bookings};
    }

    public String getClub() {
        return (String) data[0];
    }

    public LinkedList<Booking> getBookingsList() {
        return (LinkedList<Booking>) data[1];
    }
}
