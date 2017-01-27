<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"  %>
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
                    <h1 class="page-header">Add Ticket</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="editTicketForm">
                        <input id="ticketId" type="hidden" value="${ticket.id}">
                        <div class="form-group">
                            <label>Name</label>
                            <input id="name" value="${ticket.name}" class="form-control">
                            <p class="help-block error" id="errorMsg_name"></p>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <input id="description"  value="${ticket.description}" class="form-control">
                            <p class="help-block error" id="errorMsg_description"></p>
                        </div>
                        <div class="form-group">
                            <label>Annotation</label>
                            <input id="annotation"  value="${ticket.annotation}" class="form-control">
                            <p class="help-block error" id="errorMsg_annotation"></p>
                        </div>
                        <div class="form-group">
                            <label>Seat</label>
                            <select class="form-control" id="seatTypeId" >
                                <d:forEach var="seat" items="${seatTypes}" >
                                    <option value="${seat.id}"
                                            <d:if test="${seat.id == ticket.seatType.id}" >
                                                selected
                                            </d:if>
                                            data-adult-price="${seat.adultPrice}" data-child-price="${seat.childPrice}">${seat.name}</option>
                                </d:forEach>
                            </select>
                            <p class="help-block error" id="errorMsg_seatTypeId"></p>
                        </div>

                        <div class="form-group">
                            <label>Vat</label>
                            <select class="form-control" id="vatId" >
                                <d:forEach var="vat" items="${vats}" >
                                    <option value="${vat.id}"
                                            <d:if test="${vat.id == ticket.vat.id}" >
                                                selected
                                            </d:if>
                                            data-price = ${vat.amount}>${vat.name}</option>
                                </d:forEach>
                            </select>
                            <p class="help-block error" id="errorMsg_vatId"></p>
                        </div>

                        <div class="form-group">
                            <label>Is Child</label><br>
                            <label class="checkbox-inline">
                                <input type="checkbox"
                                <d:if test="${ticket.child}" >
                                       checked
                                </d:if>
                                       id="isChild">Yes
                            </label>
                            <p class="help-block error" id="errorMsg_isChild"></p>
                        </div>

                        <div class="form-group">
                            <label>Printed Price</label>
                            <input id="printedPrice"  value="${ticket.printedPrice}" class="form-control">
                            <p class="help-block error" id="errorMsg_printedPrice"></p>
                        </div>

                        <div class="form-group">
                            <label>Start Date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input id="startDate"  value="<fmt:formatDate  value="${ticket.startDate}" pattern="MM/dd/yyy" /> " type='text' class="form-control" name="date" placeholder="MM/DD/YYY" />

                            </div>
                            <p class="help-block error" id="errorMsg_startDate"></p>
                        </div>
                        <div class="form-group">
                            <label>End Date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' value=" <fmt:formatDate  value="${ticket.endDate}" pattern="MM/dd/yyy" />"  class="form-control" id="endDate" name="date" placeholder="MM/DD/YYY" />

                            </div>
                            <p class="help-block error" id="errorMsg_endDate"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button class="btn btn-primary" onclick="return submitTicketData()">Edit Ticket</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<script>
    doPriceCalculation();
    function submitTicketData(){

        $("#statusMsg").html("").hide();

        var ticketId = $("#ticketId").val();
        var seatTypeId =$("#seatTypeId").val();
        var vatId =$("#vatId").val();
        var startDate =$("#startDate").val();
        var endDate =$("#endDate").val();
        var isChild = $("#isChild").prop("checked");
        var isAdult = true;
        if(isChild)
            isAdult=false;
        var printedPrice =$("#printedPrice").val();
        var name =$("#name").val();
        var description =$("#description").val();
        var annotation =$("#annotation").val();
        enableDisableFormElement("editTicketForm",["input","button","select"],false);
        var postData={
            id:ticketId,
            seatTypeId:seatTypeId,
            name:name,
            description:description,
            annotation:annotation,
            printedPrice:printedPrice,
            vatId:vatId,
            isChild:isChild,
            isAdult : isAdult
        };

        if(startDate)
            postData['startDate'] = startDate;
        if(endDate)
            postData['endDate'] = endDate;

        $.ajax({
            url: BASEURL+'api/admin/ticket/edit/',
            type: 'POST',
            data: postData ,
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                    enableDisableFormElement("editTicketForm",["input","button","select"],true);
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    enableDisableFormElement("editTicketForm",["input","button","select"],true);
                }
            },success: function(data){
                $("#statusMsg").html("Ticket updated successfully").show();
                setTimeout(function(){
                    window.location = BASEURL+"admin/ticket/all";
                },2000);
            }
        });
        return false;
    }

    $("#isChild").change(doPriceCalculation);
    $("#isAdult").change(doPriceCalculation);
    $("#seatTypeId").change(doPriceCalculation);
    $("#vatId").change(doPriceCalculation);


    function doPriceCalculation(){
        var adultPrice = $("option:selected","#seatTypeId").attr("data-adult-price");
        var childPrice = $("option:selected","#seatTypeId").attr("data-child-price");
        var isChild = $("#isChild").prop("checked");
        var seatPrice = adultPrice;
        if(isChild)
            seatPrice = childPrice;
        var vatPercentage = $("option:selected","#vatId").attr("data-price");
        var totalPrice = parseFloat(seatPrice)+(parseFloat(seatPrice)*parseFloat(vatPercentage))/100;
        $("#printedPrice").val(totalPrice);
    }
</script>
</body>
</html>


