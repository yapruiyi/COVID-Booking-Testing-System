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
import java.util.Date;

public class BookingController {
    public BookingController(){}
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    HttpClient previousclient;
    HttpRequest previousrequest;
    HttpClient client;
    HttpRequest request;
    HttpClient testingsiteclient;
    HttpRequest testingsiterequest;
    Date previousdate;
    String previousvenue;


    /**
     *  perform booking using the credentials obtained
     * @param bookingModel booking model
     * @throws IOException
     * @throws InterruptedException
     */
    public void performBooking(BookingModel bookingModel) throws IOException, InterruptedException {
        Date date = bookingModel.getDate();
        String userID = bookingModel.getUserID();
        String siteID = bookingModel.siteID;

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String jsonDate = sdf.format(date); // format date into json ISO 8601 format

        String jsonString = "{" +
                "\"customerId\":\"" + userID + "\"," +
                "\"testingSiteId\":\"" + siteID + "\"," +
                "\"startTime\":\"" + jsonDate + "\"," +
                "\"notes\":\"" + "String" + "\"," +
                "\"additionalInfo\":{}" +
                "}";

        String bookingUrl = rootUrl + "/booking";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(bookingUrl)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() == 201){
            //success
            JOptionPane.showMessageDialog(null, "Booking Completed");
        }else{
            //print error message
            System.out.println(request.uri());
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Full JSON response: " + response.body()); // The JWT token that has just been issued will be returned since we set ?jwt=true.
            System.out.println("----\n\n");
        }
    }

    public void cancelBooking(BookingModel bookingModel) throws IOException, InterruptedException {
        String bookingID = bookingModel.getBookingID();

        String usersUrl = rootUrl + "/booking/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl + bookingID))
                .setHeader("Authorization", myApiKey)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        if (response.statusCode() == 204){
            JOptionPane.showMessageDialog(null, "Booking is cancelled");
        }
        else{
            JOptionPane.showMessageDialog(null, "Booking is not cancelled");
        }


    }

    public void changeBooking(BookingModel bookingModel) throws IOException, InterruptedException, ParseException {
        String UserID = bookingModel.getUserID();
        String BookingID = bookingModel.getBookingID();
        String Venue = bookingModel.getVenue();
        Date date = bookingModel.getDate();

        String bookingUrl = rootUrl + "/booking/";
        HttpClient client = HttpClient.newHttpClient();
        String VenueID = "";
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String jsonDate = sdf.format(date);


        //First store previous booking configuration in objectnode before updating using patch
        previousclient = HttpClient.newHttpClient();
        previousrequest = HttpRequest.newBuilder(URI.create(bookingUrl + BookingID)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .GET()
                .build();

        HttpResponse<String> previousresponse = client.send(previousrequest, HttpResponse.BodyHandlers.ofString());


        //create singleton
        BookingDatabaseSingleton singleton = BookingDatabaseSingleton.getInstance(new ObjectMapper().readValue(previousresponse.body(), ObjectNode.class));


        //previousdate = sdf.parse(singleton.node.get("startTime").textValue());
        if (singleton.node != null){

            previousdate = sdf.parse(singleton.node.get("startTime").textValue());
            previousvenue = singleton.node.get("testingSite").get("name").textValue();
            if (date.compareTo(previousdate) >= 0 && Venue.equals(previousvenue)){
                //unable to change booking
                JOptionPane.showMessageDialog(null, "cannot change into previous booking!");
                return;
            }
        }
        if (singleton.node2 != null){
            previousdate = sdf.parse(singleton.node2.get("startTime").textValue());
            previousvenue = singleton.node2.get("testingSite").get("name").textValue();
            if (date.compareTo(previousdate) >= 0 && Venue.equals(previousvenue)){
                //unable to change booking
                JOptionPane.showMessageDialog(null, "cannot change into previous booking!");
                return;
            }
        }
        if (singleton.node3 != null){

            previousdate = sdf.parse(singleton.node3.get("startTime").textValue());
            previousvenue = singleton.node3.get("testingSite").get("name").textValue();
            if (date.compareTo(previousdate) >= 0 && Venue.equals(previousvenue)){
                //unable to change booking
                JOptionPane.showMessageDialog(null, "cannot change into previous booking!");
                return;
            }
        }
        // ObjectNode node =new ObjectMapper().readValue(previousresponse.body(), ObjectNode.class);//test

        //  Date previousdate = sdf.parse(node.get("startTime").textValue());//test

        // System.out.println(date.compareTo(previousdate)); //test, compare new date with old date


        //System.out.println(node.get("testingSite"));

        //above is new implementation


        //System.out.println(jsonDate);
        testingsiteclient = HttpClient.newHttpClient();
        testingsiterequest = HttpRequest
                .newBuilder(URI.create(rootUrl + "/testing-site"))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();
        HttpResponse<String> testingsiteresponse = testingsiteclient.send(testingsiterequest, HttpResponse.BodyHandlers.ofString());
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(testingsiteresponse.body(), ObjectNode[].class);
        for (ObjectNode node: jsonNodes) {
            if (node.get("name").textValue().equals(Venue)){
                VenueID = node.get("id").textValue();
                System.out.println("VenueID is obtained");
            }

        }

/*
        if (Venue.equals("Monash University - Clayton")){
            VenueID = "7fbd25ee-5b64-4720-b1f6-4f6d4731260e"; // testing site id
        }else if (Venue.equals("4Cyte Pathology - Mount Waverley Drive-Through")){
            VenueID = "ccad0b5b-0786-42d2-802d-3497c5eda14e"; // testing site id
        }*/

        String jsonString = "{" +
                "\"customerId\":\"" + UserID + "\"," +
                "\"testingSiteId\":\"" + VenueID + "\"," +
                "\"startTime\":\"" + jsonDate + "\"," +
                "\"status\":\"" + "INITIATED" + "\","   +
                "\"notes\":\"" + "string" + "\"," +
                "\"additionalInfo\":{}" +
                "}";


   /*     if (singleton.node != null) {
            if (singleton.node.get("testingSite").get("name").equals(Venue) && ) {

            }
        }*/


        client = HttpClient.newHttpClient();

        request = HttpRequest.newBuilder(URI.create(bookingUrl + BookingID)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .method("PATCH",  HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            //
            JOptionPane.showMessageDialog(null, "Booking Venue and Time is changed");

            //update storage at singleton
            if (singleton.node2 == null){
                singleton.node2 = new ObjectMapper().readValue(response.body(), ObjectNode.class);
                System.out.println("node2 is filled");
            }
            else if (singleton.node3 == null){
                singleton.node3 = new ObjectMapper().readValue(response.body(), ObjectNode.class);
                System.out.println("node3 is filled");
            }
        }
        else {
            //System.out.println(response.statusCode());
            // System.out.println(BookingID);
            //System.out.println(UserID);
            //System.out.println(VenueID);
            System.out.println(response.statusCode());

            JOptionPane.showMessageDialog(null, "Booking Venue and Time is not changed");
            return;
        }

    }

    public void checkBooking(BookingModel bookingModel)  throws IOException, InterruptedException {
        String bookingID = bookingModel.getBookingID();

        String usersUrl = rootUrl + "/booking/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl + bookingID))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectNode node = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        System.out.println(30);
        System.out.println(node.get("testingSite").get("additionalInfo").get("waitingtime").asText());


        if(response.statusCode() == 200){
            //
            JOptionPane.showMessageDialog(null, "Booking is already done");
        }
        else {
            JOptionPane.showMessageDialog(null, "Booking is not done yet");
        }

        /*ObjectNode node = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        if (node.get("status").textValue() == "INITIATED"){
            // do something
            JOptionPane.showMessageDialog(null, "Booking has already been done");
        }
        else{
            JOptionPane.showMessageDialog(null, "Booking has not been done");

        }*/
/*
        if(response.statusCode() == 200){
            //
            JOptionPane.showMessageDialog(null, "Booking is already done");
        }
        else{
            JOptionPane.showMessageDialog(null, "Booking is not done yet");

        }*/
    }



}
