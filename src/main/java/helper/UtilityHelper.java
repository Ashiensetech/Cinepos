package helper;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public class UtilityHelper {
    public static String getRandomNumber(){
        Random rnd = new Random();
        int n = 1000 + rnd.nextInt(900000);
        return Integer.toString(n);
    }
    public static String getRandomNumber(int n){
        if(n>100){
            return null;
        }
        String randomText = "";
        Random rnd = new Random();
        int i=0;
        while(i<n){
            randomText +=Integer.toString(rnd.nextInt(9));
            i++;
        }
        return randomText;
    }
    public static String getMD5Text(String txt){
        return DigestUtils.md5DigestAsHex(txt.getBytes());
    }
    public static double round(double value, int places) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    public static String getRandomStr(int length) {

        String catalogStr="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(catalogStr.charAt(random.nextInt(catalogStr
                    .length())));
        }

        return sb.toString();
    }


}
