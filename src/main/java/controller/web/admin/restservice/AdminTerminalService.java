package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.TerminalDao;
import entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminTerminalService.createTerminal.createTerminalForm;
import validator.admin.AdminTerminalService.editTerminal.editTerminalForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/terminal")
public class AdminTerminalService {
    @Autowired
    TerminalDao terminalDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid createTerminalForm createTerminalForm,
                                    BindingResult result,
                                    HttpServletRequest request){
        String errorMsg="Terminal successfully created";

        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            Terminal terminal=new Terminal();

            terminal.setName(createTerminalForm.getName());
            terminal.setIpAddress(createTerminalForm.getIpAddress());
            terminal.setType(createTerminalForm.getType());
            terminal.setStatus(1);
            terminal.setCreatedBy(1);

            terminalDao.insert(terminal);

        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));


    }
    @RequestMapping(value = "/edit/{terminalId}",method = RequestMethod.POST)
     public ResponseEntity<?> edit(@Valid editTerminalForm editTerminalForm,
                                   BindingResult result,
                                   HttpServletRequest request,
                                   @PathVariable Integer terminalId){
        String errorMsg="Terminal successfully created";

        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            Terminal terminal=terminalDao.getById(terminalId);

            if(terminal==null){
                serviceResponse.setValidationError("terminalId","No terminal found");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }
            terminal.setId(terminalId);
            terminal.setName(editTerminalForm.getName());
            terminal.setIpAddress(editTerminalForm.getIpAddress());
            terminal.setType(editTerminalForm.getType());
            terminal.setStatus(1);
            terminal.setCreatedBy(1);

            terminalDao.insert(terminal);

        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));


    }

    @RequestMapping(value = "/active-inactive/{terminalId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> editStatus(@Valid editTerminalForm editTerminalForm,
                                        BindingResult result,
                                        @PathVariable Integer terminalId,
                                        @PathVariable String activationType){
        int status;
        System.out.println(activationType);

        if(activationType.equals("activate")){
            status = 1;
        }else  if(activationType.equals("deactivate")){
            status = 0;
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ServiceResponse.getMsg("Uri segment wrong"));
        }
        Terminal terminal=terminalDao.getById(terminalId);

        if(terminal == null) {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("terminalId", "No terminal found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);

        }

        terminal.setStatus(status);

        terminalDao.update(terminal);


        return ResponseEntity.status(HttpStatus.OK).body(terminal);


    }
}
