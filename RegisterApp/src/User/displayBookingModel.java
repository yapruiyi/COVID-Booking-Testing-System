package User;

import API.readAPIkey;
import Covid.CovidTest;
import classObjects.AdditionalInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;

public class displayBookingModel {
    private String id, smsPin, status, notes, testingSite;
    private String customer;
    private String createdAt, updatedAt, startTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmsPin() {
        return smsPin;
    }

    public void setSmsPin(String smsPin) {
        this.smsPin = smsPin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTestingSite() {
        return testingSite;
    }

    public void setTestingSite(String testingSite) {
        this.testingSite = testingSite;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public displayBookingModel(){};
    public displayBookingModel(String id, String customer, String testingSite, String createdAt,
                               String updatedAt, String startTime, String smsPin, String status, String notes){
        this.id = id;
        this.customer = customer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startTime = startTime;
        this.testingSite = testingSite;
        this.smsPin = smsPin;
        this.status = status;
        this.notes = notes;

    }

    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();

    public ArrayList<displayBookingModel> getBookingRecords() throws Exception {
        ArrayList<displayBookingModel> bookingsArray = new ArrayList<displayBookingModel>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(rootUrl + "/booking"))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Error");
        }

        //loop through the nodes
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        for (ObjectNode node: jsonNodes) {
            //additional info is the nested/child node of "node"
            String bookingID = node.get("id").textValue();
            JsonNode customer = node.get("customer");
            String createdAt = node.get("createdAt").textValue();
            JsonNode testingSite = node.get("testingSite");
            String updatedAt = node.get("updatedAt").textValue();
            String startTime = node.get("startTime").textValue();
            String smsPIN = node.get("smsPin").textValue();
            String status = node.get("status").textValue();
            String notes = node.get("notes").textValue();
            //add the record into booking array
            displayBookingModel booking = new displayBookingModel(bookingID,customer.get("givenName").textValue() + " "+ customer.get("familyName").textValue(),testingSite.get("name").textValue(),createdAt,updatedAt,startTime,smsPIN,status,notes);

            bookingsArray.add(booking);

        }

        return bookingsArray;
    }
}
