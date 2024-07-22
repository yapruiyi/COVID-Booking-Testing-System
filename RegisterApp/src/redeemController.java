import API.readAPIkey;
import classObjects.AdditionalInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class redeemController {
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    HttpClient client;
    HttpRequest request;


    public redeemController(){}

    /**
     * A class that checks for QRcode validity when provided userID
     * @param redeemmodel from Redeemmodel class
     * @throws IOException
     * @throws InterruptedException
     */
    public void redeemQRCode(Redeemmodel redeemmodel) throws IOException, InterruptedException {
        String userID = redeemmodel.getUserID();
        String QRCode = redeemmodel.getQrField();
        String usersUrl = rootUrl + "/user/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl + userID))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectNode node = new ObjectMapper().readValue(response.body(), ObjectNode.class);

            String nodeQR = node.get("additionalInfo").get("QRCode").textValue();
            boolean nodeKit = node.get("additionalInfo").get("IssuedKit").asBoolean();
            String nodeURL = node.get("additionalInfo").get("URL").textValue();

            if(nodeQR.equals(QRCode) && !nodeKit){
                updateRecord(userID,nodeQR,nodeURL);
            }else{
                JOptionPane.showMessageDialog(null, "QRCode has already been redeemed");
            }

    }

    /**
     * update record of redeemTestkit for user additional information
     * @param userID user ID
     * @param QRCode QRCode provided by user
     * @param URL url generated from QRcode
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateRecord(String userID, String QRCode, String URL) throws IOException, InterruptedException {

        String infoUpdate = "{\"QRCode\":\""+ QRCode + "\","+
                "\"URL\":\"" + URL + "\"," +
                "\"IssuedKit\": true }";

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
            JOptionPane.showMessageDialog(null, "user received RAT test kit");


        }
    }
}
