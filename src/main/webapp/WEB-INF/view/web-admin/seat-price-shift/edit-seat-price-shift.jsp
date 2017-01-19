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
                    <h1 class="page-header">Seat Price Shift</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="editSeatPriceShiftForm">
                        <input type="hidden" id="seatPriceShiftId" value="${seatPriceShift.id}">
                        <div class="form-group">
                            <label>Seat</label>
                            <select class="form-control" id="seatTypeId" >
                                <d:forEach var="seat" items="${seatTypes}" >

                                    <option
                                            <d:if test="${seat.id == seatPriceShift.seatType.id}" >
                                                selected
                                            </d:if>
                                            value="${seat.id}">${seat.name}</option>
                                </d:forEach>
                            </select>
                            <p class="help-block error" id="errorMsg_seatTypeId"></p>
                        </div>

                        <div class="form-group">
                            <label>Price</label>
                            <input id="price" class="form-control" value="${seatPriceShift.price}">
                            <p class="help-block error" id="errorMsg_price"></p>
                        </div>

                        <div class="form-group">
                            <label>Start Date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input id="startDate" type='text' class="form-control"
                                       value="<fmt:formatDate  value="${seatPriceShift.startDate}" pattern="MM/dd/yyy" />"
                                       name="date" placeholder="MM/DD/YYY" />

                            </div>
                            <p class="help-block error" id="errorMsg_startDate"></p>
                        </div>
                        <div class="form-group">
                            <label>End Date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="endDate"
                                       value="<fmt:formatDate  value="${seatPriceShift.endDate}" pattern="MM/dd/yyy" />"
                                       name="date" placeholder="MM/DD/YYY" />

                            </div>
                            <p class="help-block error" id="errorMsg_endDate"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button class="btn btn-primary" onclick="return submitPriceShiftData()">Create Price Shift</button>
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
    function submitPriceShiftData(){

        $("#statusMsg").html("").hide();

        var seatPriceShiftId =$("#seatPriceShiftId").val();
        var seatTypeId =$("#seatTypeId").val();
        var startDate =$("#startDate").val();
        var endDate =$("#endDate").val();
        var price =$("#price").val();
        enableDisableFormElement("editSeatPriceShiftForm",["input","button","select"],false);
        var postData={
            id : seatPriceShiftId,
            seatTypeId:seatTypeId,
            price:price
        };

        if(startDate)
            postData['startDate'] = startDate;
        if(endDate)
            postData['endDate'] = endDate;

        $.ajax({
            url: BASEURL+'api/admin/seat-price-shift/edit/',
            type: 'POST',
            data: postData ,
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                    enableDisableFormElement("editSeatPriceShiftForm",["input","button","select"],true);
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    enableDisableFormElement("editSeatPriceShiftForm",["input","button","select"],true);
                }
            },success: function(data){
                $("#statusMsg").html("Seat Price Shift updated successfully").show();
                setTimeout(function(){
                    window.location = BASEURL+"admin/seat-price-shift/all";
                },2000);
            }
        });
        return false;
    }
</script>
</body>
</html>


