public class Booking {
    public Venue venue;
    public int startTime;
    public int endTime;
    public String clubName;
    public boolean approvedByAdmin;

    public Booking(Venue venue, int startTime, int endTime,
                   String clubName, boolean approvedByAdmin) {
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clubName = clubName;
        this.approvedByAdmin = approvedByAdmin;
    }

    public String toString() {
        return startTime + " - " + endTime + " (" + clubName + ") @ " + venue.name;
    }
}
