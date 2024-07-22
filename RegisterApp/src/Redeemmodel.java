public class Redeemmodel {
    String userID;
    String qrField;

    public Redeemmodel(String userID, String qrField){
        this.userID = userID;
        this.qrField = qrField;

    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQrField() {
        return qrField;
    }

    public void setQrField(String qrField) {
        this.qrField = qrField;
    }
}
