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
                            <button type="" class="btn btn-danger" onclick="removeFilmTime()">Remove this film</button>
                            <button onclick="showFilmTimeModal()" type="" class="btn btn-success">Change timetable</button>
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
    function addSchedule(id,dateStr){
        var filmSchedule = new FilmSchedule();
        filmSchedule.id=id;
        filmSchedule.date = dateStr;
        filmScheduler.push(filmSchedule);
        $('#scheduleDateSelector,#modalScheduleDateSelector').append($('<option>', {value:filmSchedule.id, text:filmSchedule.date}));
    }
    function initializeFilmScheduler(data){
        var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
        var endDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
        filmScheduler = [];
        $('#scheduleDateSelector,#modalScheduleDateSelector').find("option").remove();
        var firstDate = null;
        var lastDate = null;
        if(data.length >0){
            firstDate = moment(data[0].date);
            lastDate = moment(data[data.length-1].date);
        }

        var generatedDates = getDates(startDate,endDate);

        if(firstDate == null && lastDate == null){
            for(var i in generatedDates){
                var generatedDate = generatedDates[i];
                addSchedule(0,getDateInDisplayFormat(generatedDate));
            }
            return;
        }
        var gi=0;
        for(;gi < generatedDates.length;gi++){
            var generatedDate = generatedDates[gi];

            if(generatedDate < firstDate){
                addSchedule(0,getDateInDisplayFormat(generatedDate));
                continue;
            }
            break;

        }


        for(var i in data){
            var tmpFilmSchedule = data[i];
            var filmSchedule = new FilmSchedule();
            var scheduleDateStr = getDateInDisplayFormat(tmpFilmSchedule.date);
            /*Add Schedule*/
            addSchedule(tmpFilmSchedule.id,scheduleDateStr);

            for(var ftIndex in tmpFilmSchedule.filmTimes){
               var filmTime = tmpFilmSchedule.filmTimes[ftIndex];
                /*Add Film to schedule*/
                addFilmToSchedule({
                    id:filmTime.id,
                    scheduleDateStr : scheduleDateStr,
                    startTime : filmTime.startTime,
                    endTime :filmTime.endTime,
                    film : {
                        id : filmTime.film.id,
                        name : filmTime.film.name
                    }
                });
            }

        }
        for(;gi < generatedDates.length;gi++){
            var generatedDate = generatedDates[gi];

            if(generatedDate <= lastDate){
                continue;
            }
            addSchedule(0,getDateInDisplayFormat(generatedDate));
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
                },
                204:function(response){
                    console.log("ASD");
                }
            },success: function(data,textStatus, xhr){
                //$("#statusMsg").html("Film Rental created successfully").show();
                console.log(data,textStatus, xhr.status);
                if(xhr.status == 204){
                    initializeFilmScheduler([]);
                }else{
                    initializeFilmScheduler(data);
                }

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
        var filmTimeHtmId=0;
        var filmTimeHtmIdStr = "filmTime"+filmTimeHtmId++;
        for(var r in filmScheduler){
            var scheduleId = filmScheduler[r].id;
            var date = filmScheduler[r].date;
            var scheduleDate = filmScheduler[r].date;
            var filmTimes = filmScheduler[r].filmTime;
            for(var c in filmTimes){
                filmTimeHtmIdStr = "filmTime"+filmTimeHtmId++;
                filmTimes[c].options = {id:"film_"+filmTimes[c].filmId,
                    data:{
                        //camelCase does not work
                        htmlid:filmTimeHtmIdStr,
                        id:filmTimes[c].id,
                        identifier:filmTimes[c].identifier,
                        filmId:filmTimes[c].filmId,
                        filmname:filmTimes[c].filmName,
                        starttime:moment(filmTimes[c].startTime).format('H:mm'),
                        endtme:moment(filmTimes[c].endTime).format('H:mm'),
                        screenname:$("#screenSelector option:selected").text(),
                        scheduledate:scheduleDate,
                        scheduleid:scheduleId
                    },
                    onclick:function(elem){
                        console.log($(elem));


                        var filmTimeDetails = $(elem).data();
                        $("#currentFilmTimeId").val($(elem).attr("id"));
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
        for (;currentDate <= endDate;currentDate = moment(currentDate).add(1, 'days')) {
            dateArray.push(currentDate);
        }
        return dateArray;
    }



    var FilmSchedule = function(){
        this.id = "";
        this.date = "";
        this.filmTime = [];
    };
    var FilmTime=function(){
        this.id = 0;
        this.identifier = "";
        this.filmId=0;
        this.filmName="";
        this.startTime = new Date();
        this.endTime = new Date();
        this.options={

        };
    };
    var DISPLAY_DATE_FORMAT = "MMMM Do YYYY";

    function changeFilmTimeObj(filmTime,filmTimeData){

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

            year = parseInt(scheduleDate.format("YYYY"));
            month = parseInt(scheduleDate.format("M"));
            day = parseInt(scheduleDate.format("d"));
            startHour = parseInt(tmpStartTimeArray[0]);
            startMin =  parseInt(tmpStartTimeArray[1]);
            endHour = parseInt(tmpEndTimeArray[0]);
            endMin = parseInt(tmpEndTimeArray[1]);

            if(typeof film == "string"){
                film = JSON.parse(film);
            }
        }catch(ex){
            console.log(ex);
            return;
        }

        filmTime.id = filmTimeData.id;
        filmTime.filmId = film.id;
        filmTime.filmName = film.name;



        filmTime.startTime = new Date(year,month,day,startHour,startMin);
        filmTime.endTime = new Date(year,month,day,endHour,endMin);
    }
    function addFilmToSchedule(filmTimeData){
       var isNew = false;
        if(filmTimeData==undefined){
            isNew = true;
            filmTimeData ={
                id:0,
                scheduleDateStr : $("#scheduleDateSelector option:selected").text().trim(),
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

            year = parseInt(scheduleDate.format("YYYY"));
            month = parseInt(scheduleDate.format("M"));
            day = parseInt(scheduleDate.format("d"));
            startHour = parseInt(tmpStartTimeArray[0]);
            startMin =  parseInt(tmpStartTimeArray[1]);
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
        filmTime.id = filmTimeData.id;
        filmTime.filmId = film.id;
        filmTime.filmName = film.name;



        filmTime.startTime = new Date(year,month,day,startHour,startMin);
        filmTime.endTime = new Date(year,month,day,endHour,endMin);

        var index = getFilmScheduleRowBydate(scheduleDateStr);
        if(index>=0){
            if(!hasCollusion(index,filmTime)){
                filmTime.identifier = "ID"+filmScheduler[index].filmTime.length;
                filmScheduler[index].filmTime.push(filmTime);
            }
        }
        if(isNew){
            drawTimeTable();
        }
    }
    function getFilmTimeByScheduleIdAndIdentifier(index,identifier){

        for(var i in filmScheduler[index].filmTime ){
            var filmTime = filmScheduler[index].filmTime[i];
            if(filmTime.identifier == identifier){
                return filmTime;
            }
        }
        return null;
    }
    function deleteFilmTimeByScheduleIdAndIdentifier(index,identifier){

        for(var i in filmScheduler[index].filmTime ){
            var filmTime = filmScheduler[index].filmTime[i];
            if(filmTime.identifier == identifier){
                filmScheduler[index].filmTime.splice(i,1);
                break;
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
        enableDisableFormElement("page-wrapper",["input","button","select"],false);
        $("#submitCreateScheduleMsg").html("Processing... ").show();
        submitCreateSchedule(postDataArray,false);

    }

    function submitCreateSchedule(postDataArray,flag){
        var postData;
        if(postDataArray.length > 0){
            postData = postDataArray.pop();
        }else{
            if(flag){
                createScheduling();
            }


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
                submitCreateSchedule(postDataArray,true);
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
    function showFilmTimeModal(){
        $("#changeFilmTimeModal").modal();
        var elemId = $("#currentFilmTimeId").val();
        var filmTimeDetails =$("#"+elemId).data();

        $("#screenName").html(filmTimeDetails.screenname);
        $("#modalScheduleDateSelector").val(filmTimeDetails.scheduleid);
        $("#modalFilmSelector").val(filmTimeDetails.filmid);
        $("#modalStartTimePicker").val(filmTimeDetails.starttime);
        $("#modalEndTimePicker").val(filmTimeDetails.endtme);
    }
    function updateFilmTime(){

        var filmElemTimeId = $("#currentFilmTimeId").val();
        var filmTimeId =$("#"+filmElemTimeId).data("id");
        var filmTimeIdentifier =$("#"+filmElemTimeId).data("identifier");
        var filmScheduleDate =$("#"+filmElemTimeId).data("scheduledate");
        var scheduleId = $("#modalScheduleDateSelector").val();
        var filmId = $("#modalFilmSelector").val();
        var startTime = $("#modalStartTimePicker").val();
        var endTime = $("#modalEndTimePicker").val();


        scheduleId = parseInt(scheduleId);

        if(scheduleId==0){
            return;
        }

        $.ajax({
            url: BASEURL+'api/admin/film-scheduling/update/film-time/'+filmTimeId,
            type: 'POST',
            data: {
                "scheduleId":scheduleId,
                "sTime":startTime+":00",
                "eTime":endTime+":00",
                "filmId":filmId
            },
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
                },
                204:function(response){
                    console.log("ASD");
                }
            },success: function(data,textStatus, xhr){
                var index = getFilmScheduleRowBydate(filmScheduleDate);
                if(index<0)return;
                var filmTime = getFilmTimeByScheduleIdAndIdentifier(index,filmTimeIdentifier);

                changeFilmTimeObj(filmTime,{
                    id:data.id,
                    scheduleDateStr : filmScheduleDate,
                    startTime : data.startTime,
                    endTime : data.endTime,
                    film : {
                        id : data.film.id,
                        name : data.film.name
                    }
                });

                drawTimeTable();
            }
        });
    }
    function removeFilmTime(){

        var filmElemTimeId = $("#currentFilmTimeId").val();
        var filmTimeId =$("#"+filmElemTimeId).data("id");
        var filmTimeIdentifier =$("#"+filmElemTimeId).data("identifier");
        var filmScheduleDate =$("#"+filmElemTimeId).data("scheduledate");
        var scheduleId = $("#modalScheduleDateSelector").val();
        var filmId = $("#modalFilmSelector").val();
        var startTime = $("#modalStartTimePicker").val();
        var endTime = $("#modalEndTimePicker").val();
        $.ajax({
            url: BASEURL+'api/admin/film-scheduling/delete/film-time/'+filmTimeId,
            type: 'POST',
            data: {
            },
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
                },
                204:function(response){
                    console.log("ASD");
                }
            },success: function(data,textStatus, xhr){
                var index = getFilmScheduleRowBydate(filmScheduleDate);
                if(index<0)return;
                deleteFilmTimeByScheduleIdAndIdentifier(index,filmTimeIdentifier);
                drawTimeTable();
            }
        });
    }
</script>
</body>

</html>


