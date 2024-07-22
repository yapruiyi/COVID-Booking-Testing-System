import API.readAPIkey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class onsiteTestingController {
    public onsiteTestingController(){}

    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();

    /**
     *
     * @param onsitetestingmodel from Onsitetestingmodel class
     * @throws Exception
     */
    public void submitTest(Onsitetestingmodel onsitetestingmodel) throws Exception {
        String sessionId = onsitetestingmodel.getSessionId();
        String smsPIN = onsitetestingmodel.getSmsPIN();
        String type = onsitetestingmodel.getType();
        String result = onsitetestingmodel.getResult();
        String status = onsitetestingmodel.getStatus();

        String usersUrl = rootUrl + "/booking";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(usersUrl))
                .setHeader("Authorization", myApiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String userID = "";
        String BookingID = "";
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        for (ObjectNode node: jsonNodes) {
            String nodePin = node.get("smsPin").textValue();
            if(nodePin.equals(smsPIN)){
                userID = node.get("customer").get("id").textValue();
                System.out.println(userID);
                BookingID = node.get("id").textValue();
            }
        }

        if(userID.equals("")){
            JOptionPane.showMessageDialog(null, "No Record Found.");
        }else{
            String jsonString = "{" +
                    "\"type\":\"" + type + "\"," +
                    "\"patientId\":\"" + userID + "\"," +
                    "\"administererId\":\"" + sessionId + "\"," +
                    "\"bookingId\":\"" + BookingID + "\"," +
                    "\"result\":\"" + result + "\"," +
                    "\"status\":\"" + status + "\"," +
                    "\"notes\":\"" + "String" + "\"," +
                    "\"additionalInfo\":" + "{}" +
                    "}";

            String submitUrl = rootUrl + "/covid-test";
            client = HttpClient.newHttpClient();
            // This header needs to be set when sending a JSON request body.
            request = HttpRequest.newBuilder(URI.create(submitUrl))
                    .setHeader("Authorization", myApiKey)
                    .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response1 = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response1.statusCode() == 201){
                JOptionPane.showMessageDialog(null, "Booking Completed");
            }else{
                System.out.println(request.uri());
                System.out.println("Response code: " + response1.statusCode());
                System.out.println("Full JSON response: " + response1.body()); // The JWT token that has just been issued will be returned since we set ?jwt=true.
                System.out.println("----\n\n");
            }
        }

    }
}
