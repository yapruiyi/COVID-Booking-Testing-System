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

public class CancelBookingModel {
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    private String givenName, familyName, smsPin, phoneNumber;

    public CancelBookingModel(){};
    public CancelBookingModel(String givenName, String familyName, String smsPin, String phoneNumber){
        this.givenName = givenName;
        this.familyName = familyName;
        this.smsPin = smsPin;
        this.phoneNumber = phoneNumber;
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

    public String getSmsPin() {
        return smsPin;
    }

    public void setSmsPin(String smsPin) {
        this.smsPin = smsPin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void cancelBooking(String givenName,String familyName, String phoneNumber, String smsPin) throws Exception {
        User user = new User();
        String userID = user.findUserID(givenName,familyName, phoneNumber);
        if(userID.equals("")){//if user id is invalid
            JOptionPane.showMessageDialog(null, "Invalid Credentials");
        }else{ //proceed with cancel Booking
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
                    updateRecord(bookingID);
                    break;
                }
            }
            if(bookingID.equals("")){
                JOptionPane.showMessageDialog(null,"No record Found");
            }
        }
    }

    public void updateRecord(String bookingID) throws IOException, InterruptedException {
        String infoUpdate = "{\"status\":" + "\"CANCELLED\"" + "}";
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
            JOptionPane.showMessageDialog(null, "Cancel Successful. Related Receptionist are notified.");
        }
    }
}
