package controller;

import com.fasterxml.jackson.annotation.JsonView;
import dao.CategoryDao;
import entity.Category;
import entity.iface.AdminCategory;
import entity.iface.AppCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;



import java.util.HashMap;
import java.util.List;

/**
 * Created by mi on 12/21/16.
 */
@RestController
public class Test {
    @Autowired
    CategoryDao categoryDao;
    @RequestMapping(value="/app-category")
    @JsonView(AppCategory.class)
    public Category getAppCategory(){
        return categoryDao.getById(8);
    }

    @RequestMapping(value="/app-category/list")
    @JsonView(AppCategory.class)
    public ResponseEntity<?> getAppCategoryList(){

        return new ResponseEntity<List<Category>>(categoryDao.getAll(), HttpStatus.BAD_REQUEST);
    }

    @JsonView(AdminCategory.class)
    @RequestMapping(value="/admin-category")
    public Category getAdminCategory(){
        return categoryDao.getById(8);
    }


    @RequestMapping(value="/test")
    @JsonView(AppCategory.class)
    public ResponseEntity<?> test(){
        return new ResponseEntity<HashMap<String,String>>(ServiceResponse.getMsg("Bad request"), HttpStatus.BAD_REQUEST);
    }
}
