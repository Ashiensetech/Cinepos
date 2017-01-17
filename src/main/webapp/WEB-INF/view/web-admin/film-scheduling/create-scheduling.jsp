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
                            <select class="form-control">
                                <option>Screen 1</option>
                                <option>Screen 2</option>
                            </select>
                        </div>
                        <button type="" class="btn btn-primary">Submit</button>
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
                        <div class="well">
                            <div class="form-group">
                                <label>Select date</label>
                                <select id="scheduleDateSelector" class="form-control">
                                    <option value=""></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Select Film</label>
                                <select class="form-control">
                                    <d:forEach var="film" items="${films}" >
                                        <option value="${film.id}">${film.name}</option>
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

                            <button type="" class="btn btn-primary">Add film to schedule</button>
                        </div>
                    </div>

                    <div class="col-lg-6 schedule-show ">
                        <div class="well text-center">
                            <h4>Film Schedule</h4>
                            <p>Screen 1</p>
                            <p>24 Dec 2016, Saturday</p>
                            <p class="f-name">X-MEN</p>
                            <p>11:30 AM-1:00PM</p>
                            <hr>
                            <button type="" class="btn btn-danger">Remove this film</button>
                            <button type="" class="btn btn-success">Change timetable</button>
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

<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- dropzone JavaScript -->

<!-- Timetable -->
<%--<script src="<c:url value="/admin-resources/timetable/timetable.js" />"></script>--%>
<!-- Timepicker @Overridden-->
<script src="<c:url value="/admin-resources/developer/custom/vendor/timetable/timetable.js" />" ></script>
file:///home/mi/Projects/j2ee/cinepos/src/main
<!-- Timepicker -->
<script src="<c:url value="/admin-resources/timepicker/bootstrap-timepicker.js" />" ></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/admin-resources/dist/js/sb-admin-2.js" />"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript" src="<c:url value="/admin-resources/bootstrap/js/bootstrap-datepicker.1.4.1.min.js" />" ></script>

<%--Delveper Helper [ Vendor ]--%>
<script type="text/javascript" src="<c:url value="/admin-resources/moment/moment.min.js" />" ></script>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>

<!-- Date picker -->

<!-- Time picker -->
<script type="text/javascript">
    $('#startTimePicker').timepicker();
    $('#endTimePicker').timepicker();
</script>

<!-- Time picker -->

<!-- Time table -->
<script>
    var filmScheduler = [];
    function initTimeTable(){

        var filmSchedule = new FilmSchedule();
        filmSchedule.rowName="A";
        var filmTime = new FilmTime();

        filmTime.filmName = "Beautiful Mind";
        filmTime.filmStartTime = new Date(2015,7,17,9,00);
        filmTime.filmEndTime = new Date(2015,7,17,11,30);



        filmTime.options = {
            url: 'javascript:void(0)',
            class: 'vip',
            onclick:function(elem){
                console.log(elem);
            },
            data: {
                id: 0,
                ticketType: 'VIP'
            }
        };
        filmSchedule.filmTime.push(filmTime);
        filmScheduler.push(filmSchedule);


    }
    function createScheduling(){
        var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
        var stopDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");

        var dates = getDates(startDate,stopDate);

        filmScheduler = [];
        $('#scheduleDateSelector').find("option").remove();
        for(var i in dates){
            var filmSchedule = new FilmSchedule();
            filmSchedule.rowName = dates[i];
            filmScheduler.push(filmSchedule);
            $('#scheduleDateSelector').append($('<option>', {value:filmSchedule.rowName, text:filmSchedule.rowName}));
        }
        drawTimeTable();
    }
    function drawTimeTable(){



        var timetable = new Timetable();

        timetable.setScope(9,3);

        var rowNames = [];
        for(var r in filmScheduler){
            rowNames.push(filmScheduler[r].rowName);
        }
        timetable.addLocations(rowNames);
        for(var r in filmScheduler){
            var rowName = filmScheduler[r].rowName;
            var filmTimes = filmScheduler[r].filmTime;
            for(var c in filmTimes){
                timetable.addEvent(filmTimes[c].filmName, rowName,
                        filmTimes[c].filmStartTime,
                        filmTimes[c].filmEndTime,
                        filmTimes[c].options);
            }

        }

        var renderer = new Timetable.Renderer(timetable);
        renderer.draw('.timetable');
    }

    function getDates(startDate, stopDate) {
        //        date Format for startDate and stopDate 'YYYY-MM-DD'
        var dateArray = [];
        var currentDate = moment(startDate);
        var endDate = moment(stopDate);
        while (currentDate <= endDate   ) {
            dateArray.push( moment(currentDate).format('MMMM Do YYYY'));
            currentDate = moment(currentDate).add(1, 'days');
        }
        return dateArray;
    }
    function removeScheduleRow(elem){
        console.log(elem);
    }




    var FilmSchedule = function(){
        this.id = 0;
        this.rowName = "";
        this.filmTime = [];
    };
    var FilmTime=function(){
      this.filmId=0;
        this.filmName="";
        this.filmStartTime = new Date();
        this.filmEndTime = new Date();
        this.options={

        };
    };

    initTimeTable();
</script>
</body>

</html>


