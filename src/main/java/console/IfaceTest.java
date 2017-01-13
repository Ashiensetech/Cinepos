package console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.validator.routines.UrlValidator;

import static java.lang.System.out;

/**
 * Created by mi on 12/21/16.
 */
public class IfaceTest {


    public static void main(String[] args) {
        String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid("http://foo.bar.com/")) {
            System.out.println("URL is valid");
        } else {
            System.out.println("URL is invalid");
        }
    }

}