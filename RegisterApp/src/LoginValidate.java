import API.readAPIkey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;


public class LoginValidate {

    public LoginValidate(){}
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    private String jwtString;

    /**
     * validate user credentials
     * @param userText username
     * @param pwText password
     * @return validation on login. True/False
     * @throws Exception
     */
    public boolean validateUser(JTextField userText, JPasswordField pwText) throws Exception {
        String usersUrl = rootUrl + "/user";
        HttpClient client = HttpClient.newHttpClient();

        String username = userText.getText();
        String passcode = String.valueOf(pwText.getPassword());

        // Create the request body. The password for each of the sample user objects that have been created for you are the same as their respective usernames.
        String jsonString = "{" +
                "\"userName\":\"" + username + "\"," +
                "\"password\":\"" + passcode + "\"" +
                "}";

        String usersLoginUrl = usersUrl + "/login";
        client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(usersLoginUrl + "?jwt=true")) // Return a JWT so we can use it in Part 5 later.
                .setHeader("Authorization", myApiKey)
                .header("Content-Type", "application/json") // This header needs to be set when sending a JSON request body.
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(request.uri());
        System.out.println("Response code: " + response.statusCode());
        System.out.println("Full JSON response: " + response.body()); // The JWT token that has just been issued will be returned since we set ?jwt=true.
        System.out.println("----\n\n");

        boolean validate = false;
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);

        // if the retrieve is successful
        if(response.statusCode() == 200){
            validate = true;
            jwtString = jsonNode.get("jwt").textValue();
            System.out.println("JWT: " + jwtString);
        }
        return validate;
    }

    //decoder for jwt information using base64

    /**
     * decode the jwt token and return the objectnode for future use
     * @return an object node carrying the information of the login session
     * @throws Exception
     */
    public ObjectNode session() throws Exception {
        String[] jwtChunk = jwtString.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(jwtChunk[1]));
        ObjectNode session =new ObjectMapper().readValue(payload,ObjectNode.class);
        return session; //return the onject node as a session for future use

    }
}
