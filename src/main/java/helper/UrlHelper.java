package helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by mi on 10/26/16.
 */
public class UrlHelper {
    public static String getLoadMoreUrlParams(Integer stateId,Integer cityId,String title,Integer categoryId,Double lat,Double lng,Float radius) {
        String url = "?";
        if(title!=null && !title.equals("")){
            try {
                url += "title="+ URLEncoder.encode(title,"UTF-8")+"&";
            } catch (UnsupportedEncodingException e) {
                url = "?";
                System.out.print(e.getMessage());
            }
        }

        if(stateId!=null && stateId>0){
            url += "stateId="+ stateId+"&";
        }
        if(cityId!=null && cityId>0){
            url += "cityId="+ cityId+"&";
        }
        if(categoryId!=null && categoryId>0){
            url += "cid="+categoryId+"&";
        }
        if(radius!=null){
            url += "radius="+radius+"&";
        }
        if(lat!=null){
            url += "lat="+lat+"&";
        }
        if(lng!=null){
            url += "lng="+lng+"&";
        }

        return url;
    }
}
