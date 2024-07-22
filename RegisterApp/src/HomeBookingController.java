import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import API.readAPIkey;
import classObjects.AdditionalInfo;

public class HomeBookingController {
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    HttpClient client;
    HttpRequest request;
    AdditionalInfo additionalInfo = new AdditionalInfo();
    String qrCode = additionalInfo.generateQRcode();
    String userURL = additionalInfo.generateURL();


    public HomeBookingController(){}

    public void updateSystem(Homebookingmodel homebookingmodel) throws IOException, InterruptedException{
        String userID = homebookingmodel.getUserID();
        Boolean issuedkit = homebookingmodel.isIssuedkit();

        String infoUpdate = "{\"QRCode\":\""+ qrCode + "\","+
                            "\"URL\":\"" + userURL + "\"," +
                                "\"IssuedKit\": " + issuedkit.toString() + " }";

        String jsonString = "{" +
                "\"additionalInfo\":" + infoUpdate + "" +
                "}";

        String usersUrl = rootUrl + "/user/";
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder(URI.create(usersUrl + userID)) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .method("PATCH",  HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        if(response.statusCode() == 200){
            //success
            JOptionPane.showMessageDialog(null, "Register Complete");


    }
}
}
