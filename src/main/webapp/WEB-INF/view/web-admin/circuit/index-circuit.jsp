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
                    <h1 class="page-header">${(not empty circuit)?"EDIT":"ADD"} Circuit</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="createCircuitForm">
                        <input type="hidden" id="circuitId" value="${circuit.id}">
                        <d:if test="${not empty circuit.siteCode}">
                            <div class="form-group">
                                <label>Site Code</label>
                                <input class="form-control" value="${circuit.siteCode}" id="siteCode" disabled>
                                <p class="help-block error" id="errorMsg_siteCode"></p>
                            </div>
                        </d:if>

                        <div class="form-group">
                            <label>Site Name</label>
                            <input class="form-control" value="${circuit.siteName}" id="siteName">
                            <p class="help-block error" id="errorMsg_siteName"></p>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <input class="form-control" value="${circuit.address}" id="address">
                            <p class="help-block error" id="errorMsg_address"></p>
                        </div>
                        <div class="form-group">
                            <label>City</label>
                            <input class="form-control" value="${circuit.city}" id="city">
                            <p class="help-block error" id="errorMsg_city"></p>
                        </div>
                        <div class="form-group">
                            <label>Country</label>
                            <input class="form-control" value="${circuit.country}" id="country">
                            <p class="help-block error" id="errorMsg_country"></p>
                        </div>
                        <div class="form-group">
                            <label>Website</label>
                            <input class="form-control" value="${circuit.webSite}" id="webSite">
                            <p class="help-block error" id="errorMsg_webSite"></p>
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input class="form-control" value="${circuit.phoneNo}" id="phoneNo">
                            <p class="help-block error" id="errorMsg_phoneNo"></p>
                        </div>
                        <div class="form-group">
                            <label>Number of Screen</label>
                            <input class="form-control" type="number" value="${circuit.screenNo}" min="0" id="screenNo">
                            <p class="help-block error" id="errorMsg_screenNo"></p>
                        </div>
                        <div class="form-group">
                            <label>Refund Deduction Percentage</label>
                            <input class="form-control" type="number" min="0" value="${circuit.refundDeductionPercentage}" id="refundDeductionPercentage">
                            <p class="help-block error" id="errorMsg_refundDeductionPercentage"></p>
                        </div>
                        <div class="form-group">
                            <label>Booking Cancellation Time</label>
                            <input class="form-control" type="time" value="${circuit.bookingCancellationTime}" id="bookingCancellationTime">
                            <p class="help-block error" id="errorMsg_bookingCancellationTime"></p>
                        </div>
                        <div class="form-group">
                            <label>Refund Cancellation Time</label>
                            <input class="form-control" type="time" value="${circuit.refundCancellationTime}" id="refundCancellationTime">
                            <p class="help-block error" id="errorMsg_refundCancellationTime"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button type="button" id="circuitBtn" class="btn btn-primary">${(not empty circuit)?"EDIT":"ADD"}</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>

    <jsp:directive.include file="../layouts/footer.jsp"/>

    <script type="application/javascript">
        $(document).ready(function () {
            $('#circuitBtn').click(function () {
                var siteName=$("#siteName").val();
                var address=$("#address").val();
                var city=$("#city").val();
                var country=$("#country").val();
                var webSite=$("#webSite").val();
                var phoneNo=$("#phoneNo").val();
                var screenNo=$("#screenNo").val();
                var refundDeductionPercentage=$("#refundDeductionPercentage").val();
                var bookingCancellationTime=$("#bookingCancellationTime").val();
                var refundCancellationTime=$("#refundCancellationTime").val();
                var circuitId=$("#circuitId").val();

                var pageData={
                    Id:circuitId,
                    siteName:siteName,
                    address:address,
                    city:city,
                    country:country,
                    webSite:webSite,
                    phoneNo:phoneNo,
                    screenNo:screenNo,
                    refundDeductionPercentage:refundDeductionPercentage,
                };

                //alert(refundCancellationTime);



//                bookingCancellationTime = (bookingCancellationTime=="")?null:bookingCancellationTime;
//                refundCancellationTime = (refundCancellationTime=="")?null:refundCancellationTime;
                circuitId = (circuitId=="")?null:circuitId;

                if(circuitId==null){
                    bookingCancellationTime = (bookingCancellationTime=="")?null:bookingCancellationTime+":00";
                    refundCancellationTime = (refundCancellationTime=="")?null:refundCancellationTime+":00";
                }

                if(bookingCancellationTime != null){
                    pageData['bookingCancellationTime'] = bookingCancellationTime;
                }
                if(refundCancellationTime != null) {
                    pageData['refundCancellationTime'] = refundCancellationTime;
                }





                enableDisableFormElement("createCircuitForm",["input","button","select","textarea"],false);


                $.ajax({
                    url: BASEURL+'api/admin/circuit/create',
                    type: 'POST',
                    data: pageData,
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("createCircuitForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("createCircuitForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html(data.msg).show();
                        setTimeout(function(){
                            window.location = BASEURL+"admin/circuit/index";
                        },2000);
                    }
                });
            });
        });
    </script>

    <!-- Date picker -->
</body>

</html>


