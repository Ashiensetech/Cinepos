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
                <form id="createComboForm">
                    <div class="row clearfix">
                        <div class="col-lg-6">
                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label>Combo name</label>
                                    <input type="text" name="" id="comboName" class="form-control">
                                    <p class="help-block error" id="errorMsg_comboName"></p>
                                </div>
                                <div class="form-group">
                                    <label>Combo Details</label>
                                    <textarea class="form-control" id="details" name=""></textarea>
                                    <p class="help-block error" id="errorMsg_details"></p>
                                </div>

                                <div class="form-group">
                                    <label>Price</label>
                                    <input type="number" min="0" name="" id="price" class="form-control">
                                    <p class="help-block error" id="errorMsg_price"></p>
                                </div>

                                <div class="form-group">
                                    <label>Start date</label>
                                    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                        <input type='text' class="form-control" id="startDate" name="date"
                                               placeholder="MM/DD/YYY" />
                                    </div>
                                    <p class="help-block error" id="errorMsg_startDate"></p>
                                </div>
                                <div class="form-group">
                                    <label>End date</label>
                                    <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                        <input type='text' class="form-control" id="endDate" name="date" placeholder="MM/DD/YYY"
                                        />
                                    </div>
                                    <p class="help-block error" id="errorMsg_endDate"></p>
                                </div>

                            </div>

                            <div class="col-lg-12 clearfix combo-selector">
                                <label class="radio-inline">
                                    <input type="radio" name="inlineRadioOptions" id="ticketRadio" value="ticketRadio" checked="checked"> Ticket + Concession Product
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="inlineRadioOptions" id="productRadio" value="productRadio"> Concession Product
                                </label>

                            </div>

                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label>Seat Type</label>
                                    <select class="form-control" id="tickets">
                                        <option value="">Select  Seat Type</option>
                                        <d:choose>
                                            <d:when test="${not empty seatTypeList}">
                                                <d:forEach var="varseatType" items="${seatTypeList}">
                                                    <option value="${varseatType.id}" data-subtext="${varseatType.name}">${varseatType.name}</option>
                                                </d:forEach>
                                            </d:when>
                                        </d:choose>
                                    </select>
                                    <p class="help-block error" id="errorMsg_tickets"></p>

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
                                </div>

                            </div>
                        </div>



                        <div class="col-lg-6">
                            <div class="row clearfix">
                                <label style="padding-left: 15px;font-size: 16px;">Products</label>
                                <div class="col-md-12">
                                    <div class="well">
                                        <ul class="prod-list" id="addedProductList">

                                        </ul>
                                        <div class="plist-total">
                                            Total<span class="plist-price" id="plistTotal"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 text-left" style="margin-top:30px;">
                        <p class="help-block" id="statusMsg"></p>
                        <button type="button" id="comboBtn" class="btn btn-lg btn-primary">Submit</button>
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

        function addProductToCombo() {
            var productId=$("#concessionProduct").val();
            if(productId==""){
                  $("#errorMsg_concessionProduct").text("Concession product are required");
                  return false;
            }
            $.ajax({
                url: BASEURL+'api/admin/concession-product/getproductbyid/'+productId,
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
                    var productHtml="";
                    var pListPrice="";

                    productHtml+='<li>'
                    productHtml+=data.name+' <span class="plist-price plistPrice" data-price="'+data.sellingPrice+'" data-proids="'+data.id+'">$'+data.sellingPrice+'</span>'
                    productHtml+='<span class="plist-remove" data-proid="'+data.id+'">X</span>'
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
                products.push($(this ).data('proids'));
                price+=parseFloat($(this ).data("price"));
            });

            $('#plistTotal').text("$"+price);
        }



        $(document).ready(function () {



            $('input:radio[name=inlineRadioOptions]').click(function () {
                radioOption=[];
                radioOption[$(this).val()]=$(this).val();
                    if (radioOption.hasOwnProperty('ticketRadio'))
                        $("#tickets").show("slow");
                    else
                        $("#tickets").hide("slow");

                console.log(radioOption);
            });

            $('#comboBtn').click(function () {
                if(radioOption.hasOwnProperty('ticketRadio')){
                   $("#errorMsg_tickets").text("Tickets are required");
                    //BindErrorsWithHtml("errorMsg_",response.responseJSON);
                }

                var comboName=$("#comboName").val();
                var details=$("#details").val();
                var price=$("#price").val();
                var startDate=$("#startDate").val();
                var endDate=$("#endDate").val();

                var ticket=$("#tickets").val();

                if(ticket==""){
                    ticket=0;
                }

                enableDisableFormElement("createComboForm",["input","button","select","textarea"],false);

                var pageData={
                    comboName:comboName,
                    details:details,
                    price:price,
                    startDate:startDate,
                    endDate:endDate,
                    seatTypeId:ticket,
                    comboType:"product",
                };

                (products.length<=0)? pageData['productIds']=null:  pageData['productIds']=JSON.stringify(products);

                $.ajax({
                    url: BASEURL+'api/admin/combo/create',
                    type: 'POST',
                    data: pageData,
                    statusCode: {
                        401: function (response) {
                            enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("createComboForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html("Combo created successfully").show();
                        setTimeout(function(){
                            window.location = BASEURL+"admin/combo/all";
                        },2000);
                    }
                });
            });
        });
    </script>

    <!-- Date picker -->
</body>

</html>


