package API;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readAPIkey {
    public readAPIkey(){}

    private String apiKey;

    /**
     * read the api key from apikey file
     * @return the apikey string
     */
    public String getApiKey(){
        try {
            File myObj = new File("RegisterApp/src/apikey.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                apiKey = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return apiKey;
    }
}
