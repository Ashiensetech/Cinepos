package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.ConcessionProductCategoryDao;
import dao.ConcessionProductDao;
import entity.ConcessionProduct;
import entity.ConcessionProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequestMapping(AppUriPreFix.apiUriPrefix +"/concession-product")
public class AppProductService {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;
    @Autowired
    ConcessionProductDao concessionProductDao;

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProduct(){

        List<ConcessionProduct> concessionProductList= concessionProductDao.getAll();

        if(concessionProductList==null && concessionProductList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionProductList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(concessionProductList);
    }

    @RequestMapping(value = "/get/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductByProductId(@PathVariable(value = "product_id")Integer product_id){

        ConcessionProduct concessionProduct=concessionProductDao.getById(product_id);
        if(concessionProduct==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionProduct);
        }
        return ResponseEntity.status(HttpStatus.OK).body(concessionProduct);
    }

    @RequestMapping(value = "/get/category-id/{category_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable(value = "category_id")Integer category_id){

        List<ConcessionProduct> concessionProductList=concessionProductDao.getByCategoryId(category_id);
        if(concessionProductList==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionProductList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(concessionProductList);
    }

    @RequestMapping(value = "/check-quantity/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<?> checkQuantity(@PathVariable(value = "product_id")Integer product_id){
        ConcessionProduct concessionProduct=concessionProductDao.getById(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(concessionProduct.getUnit());
    }

    @RequestMapping(value = "/get/quantity", method = RequestMethod.GET)
    public ResponseEntity<?> getQuantities(){
        List<ConcessionProduct> concessionProduct=concessionProductDao.getProductQuantity();
        return ResponseEntity.status(HttpStatus.OK).body(concessionProduct);
    }

}
