package User;

import API.readAPIkey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ModifyBookingModel {
    private String givenName, familyName, phoneNumber, date, time, site, smsPin;
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();

    public String getSmsPin() {
        return smsPin;
    }

    public void setSmsPin(String smsPin) {
        this.smsPin = smsPin;
    }

    public ModifyBookingModel(){};
    public ModifyBookingModel(String givenName, String familyName, String phoneNumber, String smsPin, String date, String time, String site) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.smsPin = smsPin;
        this.date = date;
        this.time = time;
        this.site = site;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void updateRecord(String bookingID, String siteID, Date date) throws IOException, InterruptedException {

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String jsonDate = sdf.format(date); // format date into json ISO 8601 format

        String infoUpdate = "{\"testingSiteId\":\""+ siteID + "\","+
                "\"startTime\":\"" + jsonDate + "\"," +
                "\"status\":" + "\"UPDATED\"" + "}";


        String usersUrl = rootUrl + "/booking/";
        HttpClient client = HttpClient.newHttpClient();
        // This header needs to be set when sending a JSON request body.
        HttpRequest request = HttpRequest.newBuilder(URI.create(usersUrl + bookingID))
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .method("PATCH", HttpRequest.BodyPublishers.ofString(infoUpdate))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        if(response.statusCode() == 200){
            //success
            JOptionPane.showMessageDialog(null, "Update Successful. Related Receptionist are notified.");
        }
    }

    public void modifyBooking(String givenName,String familyName, String phoneNumber, String smsPin, String siteID,Date date) throws Exception {
        User user = new User();
        String userID = user.findUserID(givenName,familyName, phoneNumber);
        if(userID.equals("")){//if user id is invalid
            JOptionPane.showMessageDialog(null, "user not found");
        }else { //proceed with modify booking
            String usersUrl = rootUrl + "/booking";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(usersUrl))
                    .setHeader("Authorization", myApiKey)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String bookingID = "";
            ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

            for (ObjectNode node: jsonNodes) {
                String nodePin = node.get("smsPin").textValue();
                if(nodePin.equals(smsPin)){
                    bookingID = node.get("id").textValue();
                    String originalDate = node.get("startTime").textValue();
                    boolean validDate = validateDate(originalDate,date);
                    if (validDate){
                        updateRecord(bookingID,siteID,date);
                        break;
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid Date");
                    }

                }
            }
            if(bookingID.equals("")){
                JOptionPane.showMessageDialog(null,"No record Found");
            }
        }
    }

    public boolean validateDate(String originalDate, Date updateDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(originalDate.substring(0,22));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        String date = LocalDateTime.now().format(formatter);
        String updateDateStr = format.format(updateDate);
        boolean valid = false;

        Date originDate = format.parse(formatDateTime);
        Date currentDate = format.parse(date);
        int result = originDate.compareTo(currentDate);
        int result2 = updateDate.compareTo(currentDate);
        System.out.println(originalDate);
        System.out.println(currentDate);
        System.out.println(updateDateStr);
        System.out.println(result);
        System.out.println(result2);
        if (result > 0 && result2 > 0){ //if the proposed date havent pass

            valid = true;
        }
        if (result<0){
            JOptionPane.showMessageDialog(null,"Previous booking day has already passed");
        }
        if (result2 < 0){
            JOptionPane.showMessageDialog(null,"New date must be in the future.");
        }

        return valid;
    }
}
