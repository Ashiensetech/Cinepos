package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.ConcessionProductCategoryDao;
import entity.ConcessionProduct;
import entity.ConcessionProductCategory;
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
 * Created by Sarwar on 1/27/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/product-category")
public class AppCategoryService {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategory(){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        List<ConcessionProductCategory> concessionProductCategoryList= concessionProductCategoryDao.getAll();

        if(concessionProductCategoryList==null && concessionProductCategoryList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionProductCategoryList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(concessionProductCategoryList);
    }

    @RequestMapping(value = "/get/{category_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable(value = "category_id")Integer category_id){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        ConcessionProductCategory concessionProductCategory=concessionProductCategoryDao.getById(category_id);
        if(concessionProductCategory==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionProductCategory);
        }
        return ResponseEntity.status(HttpStatus.OK).body(concessionProductCategory);
    }


}
