<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:directive.include file="../layouts/header.jsp"/>
    <!-- Timetable -->
    <link href="<c:url value="/admin-resources/timetable/timetablejs.css" />" rel="stylesheet">

    <!-- Timepicker -->
    <link href="<c:url value="/admin-resources/timepicker/bootstrap-timepicker.css"/>" rel="stylesheet">
</head>


<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <jsp:directive.include file="../layouts/navbar-top.jsp"/>
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Schedules</h1>
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
                                           onchange=""
                                            />
                                </div>
                            </div>
                            <button type="" class="btn btn-primary" onclick="return showScheduling()">Show Schedule</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-12 clearfix">
                    <div id="scheduleViewer" class="timetable clearfix"></div>
                </div>

                <hr>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>


<%-- Delveper Hidden Input --%>
<input type="hidden" id="currentFilmTimeId" value="0" />


<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- dropzone JavaScript -->

<!-- Timetable -->
<%--<script src="<c:url value="/admin-resources/timetable/timetable.js" />"></script>--%>
<!-- Timepicker @Overridden-->
<script src="<c:url value="/admin-resources/developer/custom/vendor/timetable/timetable.js" />" ></script>

<!-- Timepicker -->
<script src="<c:url value="/admin-resources/timepicker/bootstrap-timepicker.js" />" ></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/admin-resources/dist/js/sb-admin-2.js" />"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript" src="<c:url value="/admin-resources/bootstrap/js/bootstrap-datepicker.1.4.1.min.js" />" ></script>

<%--Delveper Helper [ Vendor ]--%>
<script type="text/javascript" src="<c:url value="/admin-resources/moment/moment.min.js" />" ></script>
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
    })
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

<!-- Time picker -->

<!-- Time table -->
<style>
    .recent-film-time-entry{
        background-color: #5B66EC;
        transition: 200ms background-color;
        height: 45px;
        display: block;
        position: absolute;
        z-index: 2;
        padding: 0 10px;
        white-space: normal;
        overflow: hidden;
        color: #fff;
        border: 1px solid #e32c1b;
        transform-style: preserve-3d;
    }

</style>
</body>

</html>


