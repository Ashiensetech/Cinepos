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
                    <div class="col-lg-6 center text-center">
                        <div class="form-group">
                            <label>Choose Screen</label>
                            <select id="screenSelector" class="form-control" >
                                <d:forEach var="screen" items="${screens}" >
                                    <option value="${screen.id}">${screen.name}</option>
                                </d:forEach>
                            </select>
                        </div>
                        <button type="" class="btn btn-primary" onclick="triggerSubmitCreateSchedule()">Submit</button>
                    </div>
                </div>
                <div class="col-lg-12 clearfix">
                    <div class="timetable clearfix"></div>
                </div>
                <div class="row clearfix">
                    <div class="col-lg-6 schedule-edit ">
                        <div class="well">
                            <div class="form-group">
                                <label>Start date</label>
                                <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                    <input type='text' class="form-control" id="startDate" name="date" placeholder="MM/DD/YYY" type="text" />

                                </div>
                            </div>
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


                            <button type="" class="btn btn-primary" onclick="createScheduling()">Create schedule</button>
                        </div>
                        <div id="filmTimingAddForm" class="well">
                            <div class="form-group">
                                <label>Select date</label>
                                <select id="scheduleDateSelector" class="form-control">
                                    <option value=""></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Select Film</label>
                                <select id="filmSelector" class="form-control">
                                    <d:forEach var="film" items="${films}" >
                                        <option value="${film.id}" data-details='{"id":${film.id},
                                                                                  "name":"${film.name}"}'>${film.name}</option>
                                    </d:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Start time</label>
                                <div class="input-group bootstrap-timepicker timepicker">
                                    <input id="startTimePicker" type="text" class="form-control input-small">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>End time</label>
                                <div class="input-group bootstrap-timepicker timepicker">
                                    <input id="endTimePicker" type="text" class="form-control input-small">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                </div>
                            </div>

                            <button type="" class="btn btn-primary" onclick="addFilmToSchedule()">Add film to schedule</button>
                        </div>
                    </div>

                    <div id="filmTimeDetails" class="col-lg-6 schedule-show" style="display: none">
                        <div class="well text-center">
                            <h4>Film Schedule</h4>
                            <p id="screenName"></p>
                            <p id="scheduleDate"></p>
                            <p id="filmName" class="f-name"></p>
                            <p><span id="startTimeSpan"></span>-<span id="endTimeSpan"></span></p>
                            <hr>
                            <p><span id="filmTimeDetailsStatusMsg"></span></p>
                            <button type="" class="btn btn-danger" onclick="removeFilmTime()">Remove this film</button>
                            <button onclick="showFilmTimeModal()" type="" class="btn btn-success">Change timetable</button>
                            <button onclick="hideFilmTimeDetails()" type="" class="btn btn-success">Cancel</button>
                        </div>
                    </div>
                </div>
                <hr>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
    <%------------------------------------------------------------------%>
    <div class="table table-striped" class="files" id="previews">

        <div id="template" class="file-row">
            <!-- This is used as the file preview template -->
            <div>
                <span class="preview"><img data-dz-thumbnail /></span>
            </div>
            <div>
                <strong class="error text-danger" data-dz-errormessage></strong>
            </div>
        </div>
    </div>
    <%------------------------------------------------------------------------------------%>
</div>
<div class="modal fade" id="changeFilmTimeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Schedule</h4>
            </div>
            <div class="modal-body">
                <form accept-charset="utf-8">
                    <div class="form-group" style="display: none;">
                        <label>Select date</label>
                        <select id="modalScheduleDateSelector"  class="form-control">
                            <option value=""></option>
                        </select>
                    </div>
                    <div class="form-group" style="display: none;" >
                        <label>Select Film</label>
                        <select id="modalFilmSelector"  class="form-control">
                            <d:forEach var="film" items="${films}" >
                                <option value="${film.id}" data-details='{"id":${film.id},"name":"${film.name}"}'>${film.name}</option>
                            </d:forEach>
                        </select>
                    </div>
                    <div class="form-group" >
                        <label>Start time</label>
                        <div class="input-group bootstrap-timepicker timepicker">
                            <input id="modalStartTimePicker" type="time" class="form-control input-small">
                         <%--   <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>End time</label>
                        <div class="input-group bootstrap-timepicker timepicker">
                            <input id="modalEndTimePicker" type="time" class="form-control input-small">
                            <%--<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>--%>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <p id="changeFilmTimeModalStatusMsg" class="help-block has-error"></p>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="updateFilmTime()" >Update</button>
            </div>
        </div>
    </div>
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
<script type="text/javascript" src="<c:url value="/admin-resources/developer/admin/film-scheduling/film-scheduling.js" />" ></script>

film-scheduling.js
<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
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

</body>

</html>


