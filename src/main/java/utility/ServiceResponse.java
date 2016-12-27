package utility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mi on 8/1/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Service
public class ServiceResponse {

    private ResponseStat responseStat;
    private Object       responseData;
    private HashMap<String,String> parameterAlias;
    private Object       extras;
    private ServiceResponse() {
       this.responseStat = new ResponseStat();
        this.responseData = new Object();
        parameterAlias = new HashMap<String,String>();
        this.extras = null;
    }
    public static ServiceResponse getInstance(){
        return new ServiceResponse();
    }
    public static HashMap<String,String> getMsg(final String msg){
        return new HashMap<String, String>(){{put("msg",msg);}};
    }

    public class ResponseStat {
        public boolean status;
        public boolean isLogin;
        public String  msg;
        public ArrayList<RequestError> requestErrors;

        public ResponseStat() {
            System.out.println("ResponseStat() Called");
            this.status = true;
            this.isLogin = false;
            this.msg = "";
            this.requestErrors = new ArrayList<RequestError>();
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public boolean getIsLogin() {
            System.out.println("getIsLogin() Called");
            return isLogin;
        }

        public void setIsLogin(boolean isLogin) {
            this.isLogin = isLogin;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
        public void setErrorMsg(String msg) {
            this.status = false;
            this.msg = msg;
        }
        public ArrayList<RequestError> getRequestErrors() {
            return requestErrors;
        }

        public void setRequestErrors(ArrayList<RequestError> requestErrors) {
            this.requestErrors = requestErrors;
        }
    }
    public class RequestError{
        public String params;
        public String msg;
        RequestError() {
            this.params = "";
            this.msg = "";
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public ResponseStat getResponseStat() {
        return responseStat;
    }

    public void setResponseStat(ResponseStat responseStat) {
        this.responseStat = responseStat;
    }

    @JsonIgnore
    public HashMap<String, String> getParameterAlias() {
        return parameterAlias;
    }

    public void setParameterAlias(String parameter, String replacedValue) {
        this.parameterAlias.put(parameter,replacedValue);
    }
    @JsonIgnore
    public String getAliasedParameter(String parameter) {
        return this.parameterAlias.get(parameter);
    }
    @JsonIgnore
    public boolean hasAliasedParameter(String parameter) {
        return this.parameterAlias.containsKey(parameter);
    }
    public Object getResponseData() {
        System.out.println(this.responseData.getClass());
        return (this.responseData.getClass().getSimpleName().equals("Object"))?null:this.responseData;
    }

    public Object getExtras() {
        return extras;
    }

    public void setExtras(Object extras) {
        this.extras = extras;
    }

    public void setResponseData(Object responseData) {
        if(responseData==null){
            this.responseStat.status = false;
        }
        this.responseData = responseData;
    }
    public void setResponseData(Object responseData,String ifNullMsg) {
        if(responseData==null){
            this.responseStat.status = false;
            this.responseStat.msg = ifNullMsg;
            return;
        }else if(responseData instanceof List){
            if( ((List)responseData).size() == 0){
                this.responseStat.status = false;
                this.responseStat.msg = ifNullMsg;
                return;
            }
        }
        /*------------ this.responseData can't be NULL, Must not reach here if null or similar --------------*/
        this.responseData = responseData;
    }
    public boolean hasErrors(){
        return this.responseStat.requestErrors.size()>0;
    }
    public void setError(BindingResult result,boolean filterDot,boolean replaceDot){
        if(result.hasErrors()) {
            this.responseStat.setStatus(false);
            for (ObjectError object : result.getAllErrors()) {
                RequestError requestError = new RequestError();

                if(object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    String param = fieldError.getField();
                    if(filterDot){

                        param = this.filterDot(param);

                        if(hasAliasedParameter(param)){
                            param = getAliasedParameter(param);
                        }
                    }
                    requestError.setParams(param);
                    requestError.setMsg(fieldError.getCode());
                }

                if(object instanceof ObjectError) {
                    ObjectError objectError = (ObjectError) object;

                    //requestError.setParams(objectError.get());
                    //requestError.setMsg(objectError.getCode());
                    // requestError.setParams(objectError.getCode());
                }
                this.responseStat.requestErrors.add(requestError);
            }
        }
    }
    public void setRequestError(String params, String msg){
        this.responseStat.setStatus(false);
        RequestError requestError = new RequestError();
        requestError.setParams(params);
        requestError.setMsg(msg);
        this.responseStat.requestErrors.add(requestError);
    }
    private String filterDot(String fieldName){
        String[] str = fieldName.split("\\.");
        if(str.length>1){
            return str[str.length-1];
        }
        return str[0];
    }

}