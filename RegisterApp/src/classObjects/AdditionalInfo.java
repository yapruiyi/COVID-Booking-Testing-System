package classObjects;
import java.util.Random;
import java.nio.charset.Charset;


public class AdditionalInfo {
    private String QRcode;
    private String Url;

    /**
     * generates a random text as "QR code"
     * @return a random string
     */
    public String generateQRcode() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        QRcode = buffer.toString();

        return QRcode;
    }

    /**
     * generates a random URL
     * @return
     */
    public String generateURL() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        Url = "https://" + buffer.toString() + ".com";

        return Url;
    }


}
