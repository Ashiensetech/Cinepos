package console;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.lang.System.out;

/**
 * Created by mi on 12/21/16.
 */
public class IfaceTest {


    public static void main(String[] args) {
        IfaceB b = new A();
        IfaceA a = new A();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            out.println(objectMapper.writerWithType(IfaceA.class).writeValueAsString(a));
            out.println(objectMapper.writeValueAsString(b));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

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