public class Homebookingmodel {
    String userID;
    Boolean issuedkit;

    public Homebookingmodel(String userID, boolean issuedkit){
        this.userID = userID;
        this.issuedkit = issuedkit;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isIssuedkit() {
        return issuedkit;
    }

    public void setIssuedkit(boolean issuedkit) {
        this.issuedkit = issuedkit;
    }
}
