/**
 * Created by mi on 3/1/17.
 */


$(document).ready(function () {

    radioOption=[];
    radioOption["ticketRadio"]="ticketRadio";


    $('input:radio[name=inlineRadioOptions]').click(function () {
        radioOption=[];
        radioOption[$(this).val()]=$(this).val();
        if (radioOption.hasOwnProperty('ticketRadio')){
            addSeatTypeToCombo();
            $("#ticketBlock").show("slow");
        }else{
            removeSeatType();
            $("#ticketBlock").hide("slow");
        }

        console.log(radioOption);
    });


});

function productListPrice() {

    var price=0;
    products=[];
    var pListPrice=$(".plistPrice");

    pListPrice.each(function (index) {
        var product = {"seatTypeId":0,"productId":$(this ).data('proids'),"quantity":$(this).data('quantity'),"type":$(this ).data('type')};
        try{
            if(product.type == "PRODUCT"){
                product.productId = parseInt($(this ).data('proids'));
            }else if(product.type == "TICKET"){
                product.seatTypeId = parseInt($(this ).data('proids'));
            }else{
                alert("No type found in product array")
            }
            products.push(product);

            price+=parseFloat($(this ).data("price"));
        }catch(ex){
            alert(ex+" .. Ask developer to fix it");
        }

    });

    $('#plistTotal').text("$"+price);
}
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
        $("#productMsg").text("Product quantity are required").show();
        return false;
    }else{
        $("#productMsg").hide();
    }
    UnBindErrors("errorMsg_");

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
            productHtml+=data.name+' <span class="plist-price plistPrice" data-quantity="'+productQuantity+'" data-type="PRODUCT" data-price="'+productQuantity*data.sellingPrice+'" data-proids="'+data.id+'">('+productQuantity+' X $'+data.sellingPrice+') = $'+productQuantity*data.sellingPrice+'</span>'
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

function addSeatTypeToCombo() {
    var tmpData = $("#tickets option:selected").data();
    var productHtml="";
    var pListPrice="";
    var price = 0;
    var data = {id:tmpData.id,name:tmpData.name,price:tmpData.price,quantity:tmpData.quantity};
    pListPrice=$(".plistPrice");


    if(pListPrice.length>0){
        pListPrice.each(function (index) {
            var tmpPids = parseInt($(this ).data('proids'));
            var tmpType = $(this ).data('type');
            if(tmpType=='TICKET') {
                $(this).parent('li').remove();
            }
        });
    }

    if(data.id==0){
        productListPrice();
        return false;
    }
    productHtml+='<li>';
    productHtml+=data.name+' <span class="plist-price plistPrice" data-quantity="'+1+'" data-type="TICKET" data-price="'+data.price+'" data-proids="'+data.id+'">('+data.quantity+' X $'+data.price+') = $'+(1*data.price)+'</span>';
    productHtml+='<span class="plist-remove" data-proid="'+data.id+'">X</span>';
    productHtml+='</li>';

    $("#addedProductList").append(productHtml);


    productListPrice();
    $('.plist-remove').click(function () {
        if($(this).parent().find(".plistPrice").first().data().type == "TICKET"){
            $("#tickets").val(0);
        }
        $(this).parent('li').remove();
        productListPrice();
    });



}
function removeSeatType(){
    var pListPrice=$(".plistPrice");

    var flg=false;

    if(pListPrice.length>0){
        pListPrice.each(function (index) {
            var tmpPids = parseInt($(this ).data('proids'));
            var tmpType = $(this ).data('type');
            if(tmpType=='TICKET') {
                $(this).parent('li').remove();
                productListPrice();
                return false;
            }
        });
    }
}