<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:directive.include file="../layouts/header.jsp" />
</head>


<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <jsp:directive.include file="../layouts/navbar-top.jsp" />
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Add Concession Product</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <form id="createConcessionProductForm">
                    <div class="col-lg-5">
                        <div class="form-group">
                            <label>Name</label>
                            <input class="form-control" id="name" value="">
                            <p class="help-block error" id="errorMsg_name"></p>
                        </div>
                        <div class="form-group">
                            <label>Annotation</label>
                            <textarea name="" class="form-control" id="annotation" value=""></textarea>
                            <p class="help-block error" id="errorMsg_annotation"></p>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="" class="form-control" id="description"></textarea>
                            <p class="help-block error" id="errorMsg_description"></p>

                        </div>
                        <div class="form-group">
                            <label>Select Product Category</label>
                            <select class="form-control" id="productCategory">
                                <option value="">Select Product Category</option>

                                <d:choose>
                                    <d:when test="${not empty ProductCategoryList}">
                                        <d:forEach var="ProductCategoryValue" items="${ProductCategoryList}">
                                            <option value="${ProductCategoryValue.id}">${ProductCategoryValue.name}</option>
                                        </d:forEach>
                                    </d:when>
                                </d:choose>

                            </select>
                            <p class="help-block error" id="errorMsg_productCategory"></p>

                        </div>

                        <div class="form-group">
                            <label>Unit</label>
                            <input class="form-control" type="number" value="" id="unit">
                            <p class="help-block error" id="errorMsg_unit"></p>

                        </div>

                        <div class="form-group">
                            <label>Buying Price</label>
                            <input class="form-control" type="number" value="" id="buyingPrice">
                            <p class="help-block error" id="errorMsg_buyingPrice"></p>

                        </div>
                        <div class="form-group">
                            <label>Selling Price</label>
                            <input class="form-control" type="number" value="" id="sellingPrice">
                            <p class="help-block error" id="errorMsg_sellingPrice"></p>

                        </div>
                        <div class="form-group clearfix">
                            <label class="pull-left">Remote Print</label>
                            <div class="col-md-6">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="remotePrint" class="onoffswitch-checkbox" id="remotePrint">
                                    <label class="onoffswitch-label" for="remotePrint">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="pull-left">Is Combo?</label>
                            <div class="col-md-6">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="isCombo" class="onoffswitch-checkbox" id="isCombo">
                                    <label class="onoffswitch-label" for="isCombo">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>

                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="pull-left">Is Price Shift?</label>
                            <div class="col-md-6">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="isPriceShift" value="0" class="onoffswitch-checkbox" id="isPriceShift">
                                    <label class="onoffswitch-label" for="isPriceShift">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-lg-7">
                        <div class="row clearfix">
                            <label style="padding-left: 15px;font-size: 16px;">Choose Product Image</label>
                            <div class="col-md-12">
                                <div id="productImg" >
                                    <div class="dz-default dz-message">
                                        <span>Add product image</span>
                                    </div>
                                </div>
                                <p class="help-block error" id="errorMsg_productImageToken"></p>
                            </div>

                        </div>
                    </div>
                    <hr>
                    <div class="col-md-12 nopadding">
                        <p class="help-block" id="statusMsg"></p>
                        <button type="button" id="concessionProductBtn" class="btn btn-primary">SAVE</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp"/>

    <script type="application/javascript">

        Dropzone.autoDiscover = false;
        var productImageToken = 0;

        $(function() {
            var productImgDropzone = new Dropzone("div#productImg",
                {
                    url: BASEURL+"api/admin/file-upload/product",
                    method:"post",
                    paramName:"productImage",
                    maxFilesize: 2,
                    maxFiles:1,
                    addRemoveLinks: true,
                    init:function(){
                        $("div#productImg").addClass("dropzone");
                    },
                    removedfile:function(file){
                        productImageToken = 0;
                        var _ref;
                        if(file.token == undefined){
                            return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
                        }
                        removeImageByToken(file.token);
                        return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;

                    },
                    success:function(file,response){
                        productImageToken = response;
                        console.log(productImageToken);
                        file.token = response;
                        console.log(response);
                    }
                }
            );

        });

        function removeImageByToken(token){
            $.ajax({
                url: BASEURL + 'api/admin/file-upload/delete/temp-file',
                type: 'POST',
                data: {token:token},
                statusCode: {
                    401: function (response) {
                        console.log("unauthorized");
                        console.log(response);
                        showLoginModal();
                    },
                    422: function (response) {
                        console.log(response);
                        enableDisableFormElement("createFilmForm", ["input", "button", "select","textarea"], true);
                        BindErrorsWithHtml("errorMsg_", response.responseJSON);
                    }
                }, success: function (data) {
                }
            });
        }


        $(document).ready(function () {
            $('#concessionProductBtn').click(function () {
               var name=$("#name").val();
               var annotation=$("#annotation").val();
               var description=$("#description").val();
               var productCategory=$("#productCategory").val();
               var unit=$("#unit").val();
               var buyingPrice=$("#buyingPrice").val();
               var sellingPrice=$("#sellingPrice").val();
               var isPriceShiftSel=$("#isPriceShift");
               var isComboSel=$("#isCombo");
               var isPriceShiftSel=$("#isPriceShift");
               var remotePrintSel=$("#remotePrint");

                isPriceShiftSel.is(':checked') ? isPriceShiftSel.val(1) : isPriceShiftSel.val(0);
                isComboSel.is(':checked') ? isComboSel.val(1) : isComboSel.val(0);
                remotePrintSel.is(':checked') ? remotePrintSel.val(1) : remotePrintSel.val(0);


                var postData={
                    name:name,
                    annotation:annotation,
                    description:description,
                    productCategory:productCategory,
                    unit:unit,
                    buyingPrice:buyingPrice,
                    sellingPrice:sellingPrice,
                    isPriceShift:isPriceShiftSel.val(),
                    isCombo:isComboSel.val(),
                    remotePrint:remotePrintSel.val(),
                };

                if(productImageToken>0){
                    postData["productImageToken"] = productImageToken;
                }



                enableDisableFormElement("createConcessionProductForm",["input","button","select","textarea"],false);


                $.ajax({
                    url: BASEURL+'api/admin/concession-product/create',
                    type: 'POST',
                    data: postData,
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("createConcessionProductForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("createConcessionProductForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html("Concession Product created successfully").show();
                        setTimeout(function(){
                            window.location = BASEURL+"admin/concession-product/all";
                        },2000);
                    }
                });
            });
        });
    </script>

<!-- Date picker -->
</body>

</html>


