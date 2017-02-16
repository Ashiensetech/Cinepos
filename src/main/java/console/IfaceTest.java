package console;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import helper.DateHelper;
import validator.admin.AdminFilmService.editFilm.EditFilmForm;

import javax.persistence.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import static java.lang.System.out;
import static java.lang.System.runFinalizersOnExit;

/**
 * Created by mi on 12/21/16.
 */
class Animal{
    public void sound(){
        System.out.println("Sound Like Animal");
    }
}
class Dog extends Animal{
    public String belt = "YELLOW";
    public void sound(){
        System.out.println("Sound Like Dog");
    }
}
class Cat extends Animal{
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    Time dob;

    public void sound(){
        System.out.println("Sound Like Cat");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "dob=" + dob +
                "} " + super.toString();
    }
}
public class IfaceTest {


    public static void main(String[] args) {
       //Dog d =(Dog) new Animal();
        Arrays.asList(null);
    }


    //don't remove
    // Under construction
    public static void trimmerAndSanitiser(){
        final EditFilmForm o = new EditFilmForm();
        List<String> schema = new ArrayList<String>(){{add("trim");add("sanitize");}};
        List<String> ignoredField = new ArrayList<String>(){{add("name");add("trailer");}};
        Map<String,Object> vals = new  HashMap<String,Object>();
        o.setName("   asda      ");
        o.setGenreIds("[sd,gfhm1]");

        for (Method m : o.getClass().getMethods()){
            if (m.getName().startsWith("get")
                    && !ignoredField.contains(m.getName().substring(3).toLowerCase())
                    && m.getParameterTypes().length == 0
                    && m.getReturnType().getName().equals("java.lang.String")) {

                try {
                    String r =(String) m.invoke(o);
                    if(r!=null){
                        r = r.trim();
                        vals.put(m.getName().substring(3),r);
                    }
                    out.println(m.getName().substring(3));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        for (Method m : o.getClass().getMethods()){
            if (m.getName().startsWith("set")) {
                try {
                    String val =(String) vals.get(m.getName().substring(3));
                    if(val!=null){
                        m.invoke(o,val);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}

