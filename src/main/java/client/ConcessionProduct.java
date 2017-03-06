package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.nio.charset.Charset;

/**
 * Created by mi on 3/6/17.
 */
@Component
public class ConcessionProduct {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Autowired
    private RestOperations restOperations;

    private final static String url = "";

    public entity.ConcessionProduct getConcessionProduct(int id){

        ResponseEntity<entity.ConcessionProduct> responseEntity = restOperations.getForObject(url, ResponseEntity.class);
        return responseEntity.getBody();


    }
}
