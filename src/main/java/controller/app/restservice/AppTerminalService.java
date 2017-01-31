package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.TerminalDao;
import entity.ConcessionProductCategory;
import entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;

import java.util.List;

/**
 * Created by Sarwar on 1/30/2017.
 */

@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/terminal")
public class AppTerminalService {
   @Autowired
    TerminalDao terminalDao;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTerminal(){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        List<Terminal> terminalList= terminalDao.getAll();

        if(terminalList==null && terminalList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(terminalList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(terminalList);
    }

    @RequestMapping(value = "/get/{terminal_code}", method = RequestMethod.GET)
    public ResponseEntity<?> getTerminalCode(@PathVariable(value = "terminal_code")String terminal_code){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        Terminal terminal=terminalDao.getByTerminalCode(terminal_code);
        if(terminal==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(terminal);
        }
        return ResponseEntity.status(HttpStatus.OK).body(terminal);
    }
}
