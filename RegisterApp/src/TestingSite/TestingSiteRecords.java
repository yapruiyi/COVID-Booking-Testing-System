package TestingSite;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import classObjects.Address;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import API.readAPIkey;


/**
 * Provides an arraylist of type: TestingSites
 */
public class TestingSiteRecords {
    public TestingSiteRecords(){}
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();

    public ArrayList<TestingSiteModel> getTestingSiteRecords() throws Exception {

        ArrayList<TestingSiteModel> testingSiteArray = new ArrayList<TestingSiteModel>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(rootUrl + "/testing-site"))
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
            JsonNode additionalInfo = node.get("additionalInfo");
            ObjectMapper mapper = new ObjectMapper();

            //map the address value to address class
            Address address = mapper.readValue(node.get("address").toString(), Address.class);

            //add the record into testingsite array
            TestingSiteModel testingSite = new TestingSiteModel(node.get("id").textValue(),node.get("name").textValue(), additionalInfo.get("availableDay").textValue(),address,additionalInfo.get("isRAT").asBoolean(),
                    additionalInfo.get("isPCR").asBoolean(),additionalInfo.get("onsiteBooking").asBoolean(),additionalInfo.get("waitingtime").asInt(),additionalInfo.get("facilityType").textValue());

            testingSiteArray.add(testingSite);

        }

        return testingSiteArray;
    }
}
