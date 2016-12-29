package console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.lang.System.out;

/**
 * Created by mi on 12/21/16.
 */
public class IfaceTest {


    public static void main(String[] args) {
        String  a = "asd3/api/api asd1";
        if(a.contains("/ap1/")){
            out.println("ASD");
        }
        out.println("ASD1");
    }

}


interface IfaceA{
    String getText();
}
interface IfaceB extends IfaceA{
    String getType();
}
class A implements IfaceA,IfaceB{
    public String getType() {
        return "TYPE";
    }

    public String getText() {
        return "TEXT";
    }
}