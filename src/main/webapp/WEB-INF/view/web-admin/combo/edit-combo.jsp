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
                    <h1 class="page-header">Add Combo</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row clearfix">
                <form id="createComboForm" onsubmit="return submitUpdateComboData()">
                    <div class="row clearfix">
                        <div class="col-lg-6">
                            <div class="col-lg-12">
                                <input type="hidden" value="${combos.id}" id="comboId">

                                <div class="form-group">
                                    <label>Combo name</label>
                                    <input type="text" value="${combos.comboName}" name="" id="comboName" class="form-control">
                                    <p class="help-block error" id="errorMsg_comboName"></p>
                                </div>
                                <div class="form-group">
                                    <label>Combo Details</label>
                                    <textarea class="form-control" id="details" name="">${combos.details}</textarea>
                                    <p class="help-block error" id="errorMsg_details"></p>
                                </div>

                                <div class="form-group">
                                    <label>Price</label>
                                    <input type="number" min="0" value="${combos.price}" name="" id="price" class="form-control">
                                    <p class="help-block error" id="errorMsg_price"></p>
                                </div>

                                <div class="form-group">
                                    <label>Start date</label>
                                    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                        <input type='text' class="form-control" id="startDate" name="date"
                                               value="<fmt:formatDate  value="${combos.startDate}" pattern="MM/dd/yyy" />"  placeholder="MM/DD/YYYY" />
                                    </div>
                                    <p class="help-block error" id="errorMsg_startDate"></p>
                                </div>
                                <div class="form-group">
                                    <label>End date</label>
                                    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                        <input type='text' class="form-control" id="endDate" name="date"
                                               value="<fmt:formatDate  value="${combos.endDate}" pattern="MM/dd/yyy" />"  placeholder="MM/DD/YYYY"/>
                                    </div>

                                </div>

                            </div>

                            <div class="col-lg-12 clearfix combo-selector">
                                <label class="radio-inline">
                                    <input type="radio"
                                           name="inlineRadioOptions"
                                           id="ticketRadio"
                                           value="ticketRadio"
                                        <d:if test="${combos.comboType.equals('TICKET_PRODUCT')}" >checked="checked"</d:if>
                                           > Ticket + Concession Product
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="inlineRadioOptions"
                                           id="productRadio"
                                           value="productRadio"
                                           <d:if test="${combos.comboType.equals('PRODUCT')}" >checked="checked"</d:if>
                                            > Concession Product
                                </label>

                            </div>
                            <d:set var="selectedSeatTypeId" value="0" ></d:set>
                            <d:forEach var="comboDetails" items="${combos.comboDetails}" >
                                <d:if test="${comboDetails.comboProductType.equals('TICKET')}" >
                                    <d:set var="selectedSeatTypeId" value="${comboDetails.seatType.id}" ></d:set>
                                </d:if>

                            </d:forEach>

                            <div class="col-lg-12">
                                <div class="form-group" id="ticketBlock">
                                    <label>Seat Type</label>
                                    <select class="form-control" id="tickets" >
                                        <option value="0">Select  Seat Type</option>
                                        <d:choose>
                                            <d:when test="${not empty seatTypeList}">
                                                <d:forEach var="varseatType" items="${seatTypeList}">
                                                    <option ${(selectedSeatTypeId==varseatType.id)?"selected":""}  value="${varseatType.id}" data-subtext="${varseatType.name}">${varseatType.name}</option>
                                                </d:forEach>
                                            </d:when>
                                        </d:choose>
                                    </select>
                                    <p class="help-block error" id="errorMsg_seatTypeId"></p>

                                </div>
                                <div class="form-group">
                                    <label>Product</label><br>
                                    <select class="selectpicker" id="concessionProduct" data-show-subtext="true" data-live-search="true">
                                        <option value="">Select Concession Product</option>
                                        <d:choose>
                                            <d:when test="${not empty concessionProductList}">
                                                <d:forEach var="concessionProductValue" items="${concessionProductList}">
                                                    <option value="${concessionProductValue.id}" data-subtext="${concessionProductValue.name}">${concessionProductValue.name}</option>
                                                </d:forEach>
                                            </d:when>
                                        </d:choose>
                                    </select>

                                    <button type="button" class="btn btn-primary" onclick="return addProductToCombo()">Add</button>
                                    <p class="help-block error" id="errorMsg_productIds"></p>
                                    <p class="help-block error custome_error" id="productMsg"></p>
                                </div>

                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input type="number" min="1" name="" id="productQuantity" class="form-control">
                                    <p class="help-block error" id="errorMsg_productQuantity"></p>
                                </div>

                            </div>
                        </div>

                        <%
                            float totalPrice=0;
                        %>
                        <div class="col-lg-6">
                            <p class="help-block error" id="errorMsg_ComboProductId"></p>
                            <div class="row clearfix">
                                <label style="padding-left: 15px;font-size: 16px;">Products</label>
                                <div class="col-md-12">
                                    <div class="well">
                                        <ul class="prod-list" id="addedProductList">
                                            <d:set var="totalPrice"  value="0"/>
                                            <d:forEach var="comboProductValue" items="${combos.comboDetails}" >
                                                <d:if test="${comboProductValue.comboProductType.equals('PRODUCT')}" >
                                                    <li>
                                                            ${comboProductValue.concessionProduct.name}<span class="plist-price plistPrice" data-quantity="${comboProductValue.productQuantity}"
                                                                                                             data-price="${comboProductValue.productQuantity*comboProductValue.concessionProduct.sellingPrice}"
                                                                                                             data-proids="${comboProductValue.concessionProduct.id}">(${comboProductValue.productQuantity} X $${comboProductValue.concessionProduct.sellingPrice})$${comboProductValue.productQuantity*comboProductValue.concessionProduct.sellingPrice}</span>
                                                        <span class="plist-remove" data-comboproductid="${comboProductValue.id}">X</span>
                                                    </li>
                                                    <d:set var="totalPrice"  value="${comboProductValue.concessionProduct.sellingPrice+totalPrice}"/>
                                                </d:if>
                                            </d:forEach>
                                        </ul>
                                        <div class="plist-total">
                                            Total <span class="plist-price" id="plistTotal"><d:out value="$${totalPrice}"/></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-12 text-left" style="margin-top:30px;">
                        <p class="help-block" id="statusMsg"></p>

                        <input type="submit" id="comboBtn" class="btn btn-lg btn-primary" value="Submit" >
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

    var products = [];
    var radioOption=[];
    var prodcutUnit=[];
    var comboType="ticket";
    setAliasMessage("seatTypeId","No seat type found with ID : 0","Seat type required");
    function addProductToCombo() {
        var productId=$("#concessionProduct").val();
        var productQuantity=$("#productQuantity").val();

        if(productId=="" || productId<=0){
            $("#productMsg").text("Concession product are required").show();
            return false;
        }else{
            $("#productMsg").hide();
        }

        if(productQuantity=="" || productQuantity<=0){
            $("#productMsg").text("Product qunatity are required").show();
            return false;
        }else{
            $("#productMsg").hide();
        }


        $.ajax({
            url: BASEURL+'api/admin/concession-product/getproductbyid/'+productId,
            type: 'GET',
            data: {

            },
            statusCode: {
                401: function (response) {
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                },
                422: function (response) {
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);

                }
            },
            success: function(data){

                var productHtml="";
                var pListPrice="";

                pListPrice=$(".plistPrice");

                var flg=false;

                if(pListPrice.length>0){
                    pListPrice.each(function (index) {
                        if(parseInt($(this ).data('proids'))==parseInt(productId)) {
                            $(this).parent('li').remove();
                        }
                    });
                }

                productHtml+='<li>';
                productHtml+=data.name+' <span class="plist-price plistPrice" data-quantity="'+productQuantity+'" data-price="'+productQuantity*data.sellingPrice+'" data-proids="'+data.id+'">('+productQuantity+' X $'+data.sellingPrice+')$'+productQuantity*data.sellingPrice+'</span>'
                productHtml+='<span class="plist-remove" data-proid="'+data.id+'">X</span>';
                productHtml+='</li>';

                $("#addedProductList").append(productHtml);

                productListPrice();

                $('.plist-remove').click(function () {
                    $(this).parent('li').remove();
                    productListPrice();
                });
            }
        });
    }

    function productListPrice() {

        var price=0;
        products=[];
        pListPrice=$(".plistPrice");

        pListPrice.each(function (index) {
            products.push({"seatTypeId":0,"productId":$(this ).data('proids'),"quantity":$(this ).data('quantity'),"type":"PRODUCT"});

            console.log(typeof products);

            price+=parseFloat($(this ).data("price"));
        });

        $('#plistTotal').text("$"+price);
    }

    function deleteCombo(comboDetailId) {



        $.ajax({
            url: BASEURL+'api/admin/combo/delete/'+comboDetailId,
            type: 'GET',
            data: {

            },
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);

                },
                422: function (response) {
                    console.log(response);
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                }
            },
            success: function(data){

            }
        });
    }



    $(document).ready(function () {

        productListPrice();
        $('.plist-remove').unbind().bind("click",function () {

            $(this).parent('li').remove();

            var comboProductId=$(this).data("comboproductid");

            if(comboProductId!=""){
                deleteCombo(comboProductId);
            }
            productListPrice();
        });

        radioOption=[];
        radioOption["ticketRadio"]="ticketRadio";
        var isTicketRadioChecked = $("#ticketRadio").is(":checked");
        if(!isTicketRadioChecked){
            $("#ticketBlock").hide("slow");
        }


        /**
         * Combo type radio selection click event
        * */
        $('input:radio[name=inlineRadioOptions]').click(function () {
            radioOption=[];
            radioOption[$(this).val()]=$(this).val();
            if (radioOption.hasOwnProperty('ticketRadio')){
                $("#ticketBlock").show("slow");
            }else{
                $("#ticketBlock").hide("slow");
            }


            console.log(radioOption);
        });
    });
    function getSeatType(){
        var ticket=$("#tickets").val();

        if(ticket!=""){
            for(var i=0;i<products.length;i++){
               var  product = products[i];
                if(product.type=="TICKET"){
                    product.seatTypeId = parseInt(ticket);
                    return;
                }
            }
           return {"seatTypeId":parseInt(ticket),"productId":0,"quantity":1,"type":"TICKET"};
        }
    }

    function submitUpdateComboData() {

        var comboName=$("#comboName").val();
        var details=$("#details").val();
        var price=$("#price").val();
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        var productQuantity=$("#productQuantity").val();

        var ticket=$("#tickets").val();
        var ticketArray = [];
        var comboId=$("#comboId").val();
        var isTicketRadioChecked = $("#ticketRadio").is(":checked");

        if(isTicketRadioChecked){
            comboType="ticket";
            ticketArray.push(getSeatType());
        }else{
            ticket=null;
            comboType="product";
        }


        enableDisableFormElement("createComboForm",["input","button","select","textarea"],false);

        var pageData={
            comboName:comboName,
            details:details,
            price:price,
            startDate:startDate,
            endDate:endDate,
            seatTypeId:ticket,
            comboType:comboType,
            productQuantity:productQuantity
        };

        pageData['productIds']= (products.length<=0)? null:JSON.stringify(products.concat(ticketArray));

        console.log(products+"Hello");

        $.ajax({
            url: BASEURL+'api/admin/combo/edit/'+comboId,
            type: 'POST',
            data: pageData,
            statusCode: {
                401: function (response) {
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);

                    if(radioOption.hasOwnProperty('ticketRadio')){

                        var ticket=$("#tickets").val();
                        if(ticket==""){
                            $("#errorMsg_tickets").text("Tickets are required").show();
                        }else{
                            $("#errorMsg_tickets").text("Tickets are required").hide();
                        }
                        //BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    }

                },
                422: function (response) {
                    enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);


                    if(radioOption.hasOwnProperty('ticketRadio')){
                        var ticket=$("#tickets").val();
                        if(ticket==""){
                            $("#errorMsg_tickets").text("Tickets are required").show();
                        }else{
                            $("#errorMsg_tickets").text("Tickets are required").hide();
                        }
                    }
                }
            },
            success: function(data){
                $("#statusMsg").html("Combo updated successfully").show();
                setTimeout(function(){
                    window.location = BASEURL+"admin/combo/all";
                },2000);
            }
        });
        return false;
    }
</script>

<!-- Date picker -->
</body>

</html>


