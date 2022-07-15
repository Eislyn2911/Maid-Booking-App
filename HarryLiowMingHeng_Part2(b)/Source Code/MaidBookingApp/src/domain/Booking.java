package domain;
public class Booking {
    private Client client;
    private String bookingDate;
    private String bookingTime;
    private int bookingId;
    private Maid maid;

    public Booking(Client client, Maid maid, String bookingDate, String bookingTime) {
        this.maid = maid;
        this.client = client;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Maid getMaid() {
        return maid;
    }

    public void setMaid(Maid maid) {
        this.maid = maid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

}