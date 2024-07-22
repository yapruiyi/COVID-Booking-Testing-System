import java.util.Date;

public class PhoneBookingModel {
    String BookingID;
    String Pin;
    String UserID;
    String Venue;
    Date date;

    public PhoneBookingModel(String bookingID, String Pin){
        this.BookingID = bookingID;
        this.Pin = Pin;
    }
    public PhoneBookingModel(String userID, String bookingID, String Venue, Date date){
        this.UserID = userID;
        this.BookingID = bookingID;
        this.Venue = Venue;
        this.date = date;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String pin) {
        Pin = pin;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
