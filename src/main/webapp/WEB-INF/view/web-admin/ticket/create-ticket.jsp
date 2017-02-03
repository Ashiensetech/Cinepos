<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:directive.include file="../layouts/header.jsp" />
    <link href="<c:url value="/admin-resources/timetable/timetablejs.css" />" rel="stylesheet">

    <!-- Timepicker -->
    <link href="<c:url value="/admin-resources/timepicker/bootstrap-timepicker.css"/>" rel="stylesheet">
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
                <div class="row clearfix">
                    <div class="col-lg-12 ">
                            <form class="form-inline" onsubmit="return false;">
                                <div class="form-group">
                                    <label>Choose Screen</label>
                                    <select id="screenSelector" class="form-control" >
                                        <d:forEach var="screen" items="${screens}" >
                                            <option value="${screen.id}">${screen.name}</option>
                                        </d:forEach>
                                    </select>
                                </div> |
                                <div class="form-group">
                                    <label>Start date</label>
                                    <div class='input-group date'>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                        <input type='text' class="form-control" id="startDate" name="date" placeholder="MM/DD/YYY" type="text" />


                                    </div>
                                </div> |
                                <div class="form-group">
                                    <label>End date</label>
                                    <div class='input-group date'>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                        <input type='text' class="form-control"
                                               id="endDate"
                                               name="date"
                                               placeholder="MM/DD/YYY"
                                               type="text"
                                               onchange=""
                                                />
                                    </div>
                                </div>
                                <button type="" class="btn btn-primary" onclick="return showSchedulingToGenerateTicket()">Show Schedule</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 clearfix">
                    <div id="scheduleViewer" class="timetable clearfix"></div>
                </div>
                <div id="screenSeats" class="col-lg-12 clearfix row">

                </div>
            </div>
            <div  class="row">
                <div id="createTicketFormDiv" class="col-lg-6">

                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />
<%--Delveper Helper [ Vendor ]--%>
<script type="text/javascript" src="<c:url value="/admin-resources/moment/moment.min.js" />" ></script>
<!-- Timetable -->
<%--<script src="<c:url value="/admin-resources/timetable/timetable.js" />"></script>--%>
<!-- Timepicker @Overridden-->
<script src="<c:url value="/admin-resources/developer/custom/vendor/timetable/timetable.js" />" ></script>

<!-- Timepicker -->
<script src="<c:url value="/admin-resources/timepicker/bootstrap-timepicker.js" />" ></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/admin-resources/dist/js/sb-admin-2.js" />"></script>
<%--Delveper JS--%>
<%--Scheduling--%>
<script type="text/javascript" src="<c:url value="/admin-resources/developer/admin/film-scheduling/film-scheduling-core.js" />" ></script>
<script type="text/javascript" src="<c:url value="/admin-resources/developer/admin/film-scheduling/film-scheduling-view.js" />" ></script>
<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true
        });

        /*Initially disabled */
        /* Enable after schedule created*/
        enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
    });


    function getTicketSeatInfByFilmIdAndSeatId(filmTimeId,seatId){
        console.log("Getting Seats");
        $.ajax({
            url: BASEURL+'admin/ticket/partial/ticket-create/'+filmTimeId+"/"+seatId,
            type: 'GET',
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    showLoginModal();
                    enableDisableFormElement("createFilmForm",["input","button","select"],true);
                    $("#submitCreateScheduleMsg").html("Please login").show();
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();

                    enableDisableFormElement("createFilmForm",["input","button","select"],true);
                    $("#submitCreateScheduleMsg").html("Internal server error . Reload the page").show();

                },
                204:function(response){
                    $("#scheduleViewer").html("No scheduling information found");
                    console.log("ASD");
                }
            },success: function(data,textStatus, xhr){
                //$("#statusMsg").html("Film Rental created successfully").show();
                console.log(data,textStatus, xhr.status);
                $("#createTicketFormDiv").html(data);

            }
        });
    }
</script>

<!-- Date picker -->

<!-- Time picker -->
<script type="text/javascript">
    $('#startTimePicker').timepicker(
            {
                showMeridian: false,
                defaultTime: false
            });
    $('#endTimePicker').timepicker( {
        showMeridian: false,
        defaultTime: false
    });
    /**
     * Modal Time picker
     * */
    /*  $('#modalStartTimePicker').timepicker(
     {
     showMeridian: false,
     defaultTime: false
     });
     $('#modalSndTimePicker').timepicker( {
     showMeridian: false,
     defaultTime: false
     });*/
</script>


<script>
    doPriceCalculation();
    function submitTicketData(){

        $("#statusMsg").html("").hide();

        var seatId =$("#seatId").val();
        var filmTimeId =$("#filmTimeId").val();
        var ticketId =$("#ticketId").val();
        var vatId = $("#vatId").val();
        var isChild = $("#isChild").prop("checked");

        var isAdult = true;
        if(isChild)
            isAdult=false;

        var printedPrice =$("#printedPrice").val();
        var description =$("#description").val();
        var annotation =$("#annotation").val();
        enableDisableFormElement("createTicketForm",["input","button","select"],false);
        var postData={
            seatId:seatId,
            filmTimeId:filmTimeId,
            ticketId:ticketId,
            description:description,
            annotation:annotation,
            printedPrice:printedPrice,
            vatId:vatId,
            isChild:isChild,
            isAdult : isAdult
        };

        $.ajax({
            url: BASEURL+'api/admin/ticket/create',
            type: 'POST',
            data: postData ,
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                    enableDisableFormElement("createTicketForm",["input","button","select"],true);
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    enableDisableFormElement("createTicketForm",["input","button","select"],true);
                }
            },success: function(data){
                $("#statusMsg").html("Ticket created successfully").show();
                $("#filmTime"+filmTimeId).trigger("click");
               // enableDisableFormElement("createTicketForm",["input","button","select"],true);


                setTimeout(function(){
                    getTicketSeatInfByFilmIdAndSeatId(data.filmTime.id,data.screenSeat.id);
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


