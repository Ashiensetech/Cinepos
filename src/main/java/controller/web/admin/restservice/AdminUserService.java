package controller.web.admin.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by mi on 1/3/17.
 */
public class AdminUserService{

    public ResponseEntity<?> create(){




        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }
}
