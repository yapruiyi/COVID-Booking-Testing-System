package User;

import API.readAPIkey;
import classObjects.AdditionalInfo;
import classObjects.Address;
import classObjects.Booking;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class User {
    private String userName;
    private String id, givenName, familyName, phoneNumber;
    private AdditionalInfo additionalInfo;

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public boolean isHealthCareWorker() {
        return isHealthCareWorker;
    }

    public void setHealthCareWorker(boolean healthCareWorker) {
        isHealthCareWorker = healthCareWorker;
    }

    public boolean isReceptionist() {
        return isReceptionist;
    }

    public void setReceptionist(boolean receptionist) {
        isReceptionist = receptionist;
    }

    private boolean isCustomer, isHealthCareWorker, isReceptionist;

    public User(){};

    public User(String id, String givenName, String familyName, String userName, String phoneNumber, boolean isCustomer, boolean isReceptionist, boolean isHealthCareWorker, AdditionalInfo additionalInfo) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.userName = userName;
        this.isCustomer = isCustomer;
        this.isReceptionist = isReceptionist;
        this.isHealthCareWorker = isHealthCareWorker;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    final String rootUrl = "https://fit3077.com/api/v2";
    final String myApiKey = new readAPIkey().getApiKey();


    /**
     * find the user ID based on the provided input
     * @param givenName given name
     * @param familyName family name
     * @param phone phone number
     * @return user ID
     * @throws Exception
     */
    public String findUserID(String givenName, String familyName, String phone) throws Exception{
        String usersUrl = rootUrl + "/user";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String userID = "";
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        for (ObjectNode node: jsonNodes) {
            String nodeGiven = node.get("givenName").textValue();
            String nodeFamily = node.get("familyName").textValue();
            String nodePhone = node.get("phoneNumber").textValue();
            if(nodeGiven.equals(givenName) && nodeFamily.equals(familyName) && nodePhone.equals(phone)){
                userID = node.get("id").textValue();
            }
        }

        return userID;
    }

    public String findUserID(String bookingID) throws Exception{
        String usersUrl = rootUrl + "/booking/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl + bookingID))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String userID = "";
        ObjectNode jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode.class);

        if (response.statusCode() == 200) {
            userID = jsonNodes.get("customer").get("id").textValue();
        }
        else{
            JOptionPane.showMessageDialog(null, "UserID is not found");
        }

        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
