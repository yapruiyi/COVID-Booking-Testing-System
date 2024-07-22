import java.util.Date;

public class BookingModel {
    String userID;
    String siteID;
    String bookingID;
    Date date;
    String Venue;

    public BookingModel(String userID,String siteID,Date date){
        this.userID = userID;
        this.siteID = siteID;
        this.date = date;
    }

    public BookingModel(String bookingID){
        this.bookingID = bookingID;
    }

    public BookingModel(String userID, String bookingID, String Venue, Date date){
        this.userID = userID;
        this.bookingID = bookingID;
        this.Venue = Venue;
        this.date = date;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }
}
