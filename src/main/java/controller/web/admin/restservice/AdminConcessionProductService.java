package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import custom_exception.TempFileException;
import dao.ConcessionProductCategoryDao;
import dao.ConcessionProductDao;
import entity.ConcessionProduct;
import entity.ConcessionProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.FileUtil;
import utility.ServiceResponse;
import validator.admin.AdminConcessionProductService.createConcessionProduct.CreateConcessionProductForm;
import validator.admin.AdminConcessionProductService.editConcessionProduct.EditConcessionProductForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/concession-product")
public class AdminConcessionProductService {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;
    @Autowired
    ConcessionProductDao concessionProductDao;
    @Autowired
    FileUtil fileUtil;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid CreateConcessionProductForm createConcessionProductForm, BindingResult result, HttpServletRequest request){
        String errorMsg="Concession product successfully created";
        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            ConcessionProduct concessionProduct=new ConcessionProduct();


            concessionProduct.setName(createConcessionProductForm.getName());
            concessionProduct.setAnnotation(createConcessionProductForm.getAnnotation());
            concessionProduct.setDescription(createConcessionProductForm.getDescription());
            concessionProduct.setConcessionProductCategory(concessionProductCategoryDao.getById(createConcessionProductForm.getProductCategory()));
            concessionProduct.setUnit(createConcessionProductForm.getUnit());
            concessionProduct.setBuyingPrice(createConcessionProductForm.getBuyingPrice());
            concessionProduct.setSellingPrice(createConcessionProductForm.getSellingPrice());
            concessionProduct.setIsCombo(createConcessionProductForm.getIsCombo());
            concessionProduct.setIsPriceShift(createConcessionProductForm.getIsPriceShift());
            concessionProduct.setRemotePrint(createConcessionProductForm.getRemotePrint());
            concessionProduct.setStatus(1);
            concessionProduct.setCreatedBy(1);

            concessionProductDao.insert(concessionProduct);


            /**
             * Film product Image
             * */
            List<ConcessionProductImage> productImages = new ArrayList<>();

            ConcessionProductImage concessionProductImages = new ConcessionProductImage();
            concessionProductImages.setConcessionProductId(concessionProduct.getId());
            concessionProductImages.setIsBanner(1);
            concessionProductImages.setHeight(1);
            concessionProductImages.setWidth(1);
            concessionProductImages.setCreatedBy(1);
            try {
                String filePath = fileUtil.moveProductFileFromTemp(concessionProduct.getId(),createConcessionProductForm.getProductImageToken());
                System.out.println(filePath);
                concessionProductImages.setFilePath(filePath);
                productImages.add(concessionProductImages);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TempFileException e) {
                e.printStackTrace();
            }

            concessionProduct.setConcessionProductImages(productImages);

            System.out.println(concessionProduct);

            concessionProductDao.update(concessionProduct);




        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }



    @RequestMapping(value = "/edit/{productId}",method = RequestMethod.POST)
    public ResponseEntity<?> edit (@PathVariable Integer productId,
                                   @Valid EditConcessionProductForm editConcessionProductForm,
                                   BindingResult result){
        String errorMsg="Concession product successfully updated";
        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            //ConcessionProduct concessionProduct=new ConcessionProduct();

            ConcessionProduct concessionProduct=concessionProductDao.getById(productId);


            concessionProduct.setName(editConcessionProductForm.getName());
            concessionProduct.setAnnotation(editConcessionProductForm.getAnnotation());
            concessionProduct.setDescription(editConcessionProductForm.getDescription());
            concessionProduct.setConcessionProductCategory(concessionProductCategoryDao.getById(editConcessionProductForm.getProductCategory()));
            concessionProduct.setUnit(editConcessionProductForm.getUnit());
            concessionProduct.setBuyingPrice(editConcessionProductForm.getBuyingPrice());
            concessionProduct.setSellingPrice(editConcessionProductForm.getSellingPrice());
            concessionProduct.setIsCombo(editConcessionProductForm.getIsCombo());
            concessionProduct.setIsPriceShift(editConcessionProductForm.getIsPriceShift());
            concessionProduct.setRemotePrint(editConcessionProductForm.getRemotePrint());
            concessionProduct.setStatus(1);
            concessionProduct.setCreatedBy(1);

            concessionProductDao.update(concessionProduct);


            /**
             * Film product Image
             * */
            if(editConcessionProductForm.getProductImageToken()!=null){

                ConcessionProductImage concessionProductImages = new ConcessionProductImage();


                Optional<ConcessionProductImage> optionalBannerImage = concessionProduct.getConcessionProductImages().stream().findFirst();

                if(optionalBannerImage.isPresent()){
                    concessionProductImages = optionalBannerImage.get();
                }


               // List<ConcessionProductImage> productImages = new ArrayList<>();



                concessionProductImages.setConcessionProductId(concessionProduct.getId());
                concessionProductImages.setIsBanner(1);
                concessionProductImages.setHeight(1);
                concessionProductImages.setWidth(1);
                concessionProductImages.setCreatedBy(1);
                try {
                    String filePath = fileUtil.moveProductFileFromTemp(concessionProduct.getId(),editConcessionProductForm.getProductImageToken());
                    concessionProductImages.setFilePath(filePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (TempFileException e) {
                    e.printStackTrace();
                }
            }

            concessionProductDao.update(concessionProduct);

        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }

    @RequestMapping(value = "/active-inactive/{concessionProductId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> editStatus(@Valid EditConcessionProductForm editConcessionProductForm,
                                        BindingResult result,
                                        @PathVariable Integer concessionProductId,
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
        ConcessionProduct concessionProduct=concessionProductDao.getById(concessionProductId);

        if(concessionProduct == null) {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("concessionProductId", "No Concession Product found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);

        }

        concessionProduct.setStatus(status);

        concessionProductDao.update(concessionProduct);


        return ResponseEntity.status(HttpStatus.OK).body(concessionProduct);


    }

    @RequestMapping(value = "/getproductbyid/{productId}",method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@Valid EditConcessionProductForm editConcessionProductForm,
                                            BindingResult result,
                                            @PathVariable Integer productId){

        ConcessionProduct concessionProduct=concessionProductDao.getById(productId);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();


        if(concessionProduct == null) {
            serviceResponse.setValidationError("productQuantity", "No Concession Product found");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);
        }


        return ResponseEntity.status(HttpStatus.OK).body(concessionProduct);
    }


}
