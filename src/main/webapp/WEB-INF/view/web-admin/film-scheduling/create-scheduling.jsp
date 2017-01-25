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
                        <div class="well">
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
<div class="modal fade" id="changeFilmTimeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Schedule</h4>
            </div>
            <div class="modal-body">
                <form accept-charset="utf-8">
                    <div class="form-group">
                        <label>Sample input</label>
                        <input type="text" class="form-control" name="">
                    </div>
                    <div class="form-group">
                        <label>Select Film</label>
                        <select  class="form-control">
                            <d:forEach var="film" items="${films}" >
                                <option value="${film.id}" data-details='{"id":${film.id},"name":"${film.name}"}'>${film.name}</option>
                            </d:forEach>
                        </select>
                    </div>
                    <div class="form-group">
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
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>

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
<script>
    var filmScheduler = [];
    function initializeFilmScheduler(data){
        filmScheduler = [];
        $('#scheduleDateSelector').find("option").remove();
        for(var i in data){
            var tmpFilmSchedule = data[i];
            var filmSchedule = new FilmSchedule();
            var scheduleDateStr = getDateInDisplayFormat(tmpFilmSchedule.date);
            filmSchedule.id="filmCell"+tmpFilmSchedule.id;
            filmSchedule.date = scheduleDateStr;
            filmScheduler.push(filmSchedule);
            $('#scheduleDateSelector').append($('<option>', {id:filmSchedule.id,value:filmSchedule.date, text:filmSchedule.date}));

            for(var ftIndex in tmpFilmSchedule.filmTimes){
               var filmTime = tmpFilmSchedule.filmTimes[ftIndex];
                addFilmToSchedule({
                    scheduleDateStr : filmSchedule.date,
                    startTime : filmTime.startTime,
                    endTime :filmTime.endTime,
                    film : {
                        id : filmTime.film.id,
                        name : filmTime.film.name
                    }
                });
            }

        }


    }
    function createScheduling(){
        var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
        var endDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
        var screenId = $("#screenSelector").val();
      //  var dates = getDates(startDate,endDate);


        $.ajax({
            url: BASEURL+'api/app/film-scheduling/get-all-in-date-range/'+screenId,
            type: 'POST',
            data: {"startDate":startDate,"endDate":endDate},
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                   // showLoginModal();
                    //enableDisableFormElement("createFilmForm",["input","button","select"],true);
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();
                  //  BindErrorsWithHtml("errorMsg_",response.responseJSON);
                  //  enableDisableFormElement("createFilmForm",["input","button","select"],true);
                }
            },success: function(data){
                //$("#statusMsg").html("Film Rental created successfully").show();
                console.log(data);
                initializeFilmScheduler(data);
                drawTimeTable();

            }
        });

    }
    function drawTimeTable(){



        var timetable = new Timetable();

        timetable.setScope(9,3);

        var dates = [];
        for(var r in filmScheduler){
            dates.push(filmScheduler[r].date);
        }
        timetable.addLocations(dates);
        for(var r in filmScheduler){
            var date = filmScheduler[r].date;
            var scheduleDate = filmScheduler[r].date;;
            var filmTimes = filmScheduler[r].filmTime;
            for(var c in filmTimes){
                filmTimes[c].options = {id:"film_"+filmTimes[c].filmId,
                    data:{
                        //camelCase does not work
                        id:filmTimes[c].filmId,
                        filmname:filmTimes[c].filmName,
                        starttime:moment(filmTimes[c].filmStartTime).format('H:mm'),
                        endtme:moment(filmTimes[c].endTime).format('H:mm'),
                        screenname:$("#screenSelector option:selected").text(),
                        scheduledate:scheduleDate
                    },
                    onclick:function(elem){
                        console.log($(elem));
                        var filmTimeDetails = $(elem).data();

                        $("#filmTimeDetails").show();
                        $("#screenName").html(filmTimeDetails.screenname);
                        $("#scheduleDate").html(filmTimeDetails.scheduledate);
                        $("#filmName").html(filmTimeDetails.filmname);
                        $("#startTimeSpan").html(filmTimeDetails.starttime);
                        $("#endTimeSpan").html(filmTimeDetails.endtme);

                    }
                };
                timetable.addEvent(filmTimes[c].filmName, date,
                        filmTimes[c].startTime,
                        filmTimes[c].endTime,
                        filmTimes[c].options);
            }

        }

        var renderer = new Timetable.Renderer(timetable);
        renderer.draw('.timetable');
    }
    function getDateInDisplayFormat(date) {
        return moment(date).format(DISPLAY_DATE_FORMAT);
    }
    function getDates(startDate, stopDate) {
        //        date Format for startDate and stopDate 'YYYY-MM-DD'
        var dateArray = [];
        var currentDate = moment(startDate);
        var endDate = moment(stopDate);
        while (currentDate <= endDate   ) {
            dateArray.push( moment(currentDate).format(DISPLAY_DATE_FORMAT));
            currentDate = moment(currentDate).add(1, 'days');
        }
        return dateArray;
    }



    var FilmSchedule = function(){
        this.id = "";
        this.date = "";
        this.filmTime = [];
    };
    var FilmTime=function(){
        this.filmId=0;
        this.filmName="";
        this.startTime = new Date();
        this.endTime = new Date();
        this.options={

        };
    };
    var DISPLAY_DATE_FORMAT = "MMMM Do YYYY";


    function addFilmToSchedule(filmTimeData){
       
        if(filmTimeData==undefined){
            filmTimeData ={
                 scheduleDateStr : $("#scheduleDateSelector").val(),
                 startTime : $("#startTimePicker").val(),
                 endTime : $("#endTimePicker").val(),
                film : $("#filmSelector option:selected").data("details")
            };
        }
        var scheduleDateStr =filmTimeData.scheduleDateStr;
        var startTime = filmTimeData.startTime;
        var endTime = filmTimeData.endTime;

        var film = {};

        /* Schedule time initilizing variables */
        var year = 0;
        var month = 0;
        var day = 0;
        var startHour=0;
        var startMin =0;
        var endHour =0;
        var endMin =0;
        var scheduleDate = moment(scheduleDateStr,DISPLAY_DATE_FORMAT);

        try{
            film = filmTimeData.film;


            var tmpStartTimeArray = startTime.split(":");
            var tmpEndTimeArray = endTime.split(":");
            var tmpMin = parseInt(tmpStartTimeArray[1]);

            year = parseInt(scheduleDate.format("YYYY"));
            month = parseInt(scheduleDate.format("M"));
            day = parseInt(scheduleDate.format("d"));
            startHour = parseInt(tmpStartTimeArray[0]);
            startMin = (tmpMin>=0||tmpMin<=9)?"0"+tmpMin:tmpMin; // Two digits required ..why?? ..... Crazy JS knows!!!
            endHour = parseInt(tmpEndTimeArray[0]);
            endMin = parseInt(tmpEndTimeArray[1]);

            if(typeof film == "string"){
                film = JSON.parse(film);
            }
        }catch(ex){
            console.log(ex);
            return;
        }

        var filmTime = new FilmTime();
        filmTime.filmId = film.id;
        filmTime.filmName = film.name;



        filmTime.startTime = new Date(year,month,day,startHour,startMin);
        filmTime.endTime = new Date(year,month,day,endHour,endMin);

        var index = getFilmScheduleRowBydate(scheduleDateStr);
        if(index>=0){
            if(!hasCollusion(index,filmTime)){
                filmScheduler[index].filmTime.push(filmTime);
                drawTimeTable();
            }
        }
    }

    function getFilmScheduleRowBydate(date){
        for(var index in filmScheduler){
            if(filmScheduler[index].date === date){
                return index;
            }
        }
        return -1;
    }
    function hasCollusion(index,filmTime){
        for(var i in filmScheduler[index].filmTime ){
            var currentFilmTime = filmScheduler[index].filmTime[i];
            if(filmTime.startTime < currentFilmTime.startTime
                    && filmTime.endTime <  currentFilmTime.startTime){
                continue;
            }else if(filmTime.startTime > currentFilmTime.endTime
                    && filmTime.endTime >  currentFilmTime.endTime){
                continue;
            }else{
                alert("cONFLICT");
                return true;
            }
        }
        return false;
    }

    function triggerSubmitCreateSchedule(){

        /*$("#statusMsg").html("").hide();

        var filmId =$("#filmId").val();
        var startDate =$("#startDate").val();
        var endDate =$("#endDate").val();
        var weekName =$("#weekName").val();
        enableDisableFormElement("createFilmForm",["input","button","select"],false);
        var postData={
            filmId:filmId,
            weekName:weekName
        };

        if(startDate)
            postData['startDate'] = startDate;
        if(endDate)
            postData['endDate'] = endDate;*/
        var postDataArray = getPostData();
        submitCreateSchedule(postDataArray);

    }

    function submitCreateSchedule(postDataArray){
        var postData;
        if(postDataArray.length > 0){
            postData = postDataArray.pop();
        }else{
            return;
        }

        var screenId = $("#screenSelector").val();
        $.ajax({
            url: BASEURL+'api/admin/film-scheduling/create-merge',
            type: 'POST',
            data: {
                screenId:screenId,
                scheduleJson:JSON.stringify(postData)
            } ,
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                    enableDisableFormElement("createFilmForm",["input","button","select"],true);
                },
                422: function (response) {
                    console.log(response);
                    $("#statusMsg").html("Error found").show();
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    enableDisableFormElement("createFilmForm",["input","button","select"],true);
                }
            },success: function(data){
                //$("#statusMsg").html("Film Rental created successfully").show();
                submitCreateSchedule(postDataArray);
                console.log(data);
            }
        });
        return false;
    }
    function getPostData(){
        var tmpFilmScheduler =JSON.parse(JSON.stringify(filmScheduler));
        for(var i in tmpFilmScheduler){
           var filmSchedule =  tmpFilmScheduler[i];
            filmSchedule.date = moment(filmSchedule.date,DISPLAY_DATE_FORMAT).format("YYYY-MM-DD");
            for(var j in filmSchedule.filmTime){
                var tmpFilmtime =  filmSchedule.filmTime[j];
                tmpFilmtime.startTime = moment(tmpFilmtime.startTime).format("HH:mm:ss");
                tmpFilmtime.endTime = moment(tmpFilmtime.endTime).format("HH:mm:ss");
            }
        }

        return tmpFilmScheduler;
    }
</script>
</body>

</html>


