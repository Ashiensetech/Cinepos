var FilmSchedule = function(){
  this.id = "";
  this.date = "";
  this.filmTime = [];
};
var FilmTime=function(){
    this.id = 0;
    this.htmlId = "";
    this.identifier = "";
    this.filmId=0;
    this.filmName="";
    this.startTime = new Date();
    this.endTime = new Date();
    this.options={

    };
};

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

  enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
  var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  var endDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  var screenId = $("#screenSelector").val();


  if(screenId=="" || parseInt(screenId)<=0){
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
    $("#createScheduleStatusMsg").html("Start date required");
    return;
  }
  if(startDate==""){
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
    $("#createScheduleStatusMsg").html("Start date required");
    return;
  }
  if(endDate==""){
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
    $("#createScheduleStatusMsg").html("End date required");
    return;
  }
  if(moment(endDate).diff(moment(startDate), 'minutes') < 0){
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],false);
    $("#createScheduleStatusMsg").html("Start date is greater then end  date");
    return;
  }

  $.ajax({
    url: BASEURL+'api/app/film-scheduling/get-all-in-date-range/'+screenId,
    type: 'POST',
    data: {"startDate":startDate,"endDate":endDate},
    statusCode: {
      401: function (response) {
        console.log("unauthorized");
        // showLoginModal();
        //enableDisableFormElement("createFilmForm",["input","button","select"],true);
          $("#submitCreateScheduleMsg").html("Please login").show();
      },
      422: function (response) {
        console.log(response);
        $("#statusMsg").html("Error found").show();
        //  BindErrorsWithHtml("errorMsg_",response.responseJSON);
        //  enableDisableFormElement("createFilmForm",["input","button","select"],true);
          $("#submitCreateScheduleMsg").html("Internal server error . Reload the page").show();
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
          enableDisableFormElement("filmTimingAddForm",["input","button","select"],true);
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
  var filmTimeHtmIdPrefix = "filmTime";
  var filmTimeHtmIdStr = filmTimeHtmIdPrefix+filmTimeHtmId++;
  for(var r in filmScheduler){
    var scheduleId = filmScheduler[r].id;
    var date = filmScheduler[r].date;
    var scheduleDate = filmScheduler[r].date;
    var filmTimes = filmScheduler[r].filmTime;
    for(var c in filmTimes){
        if(filmTimes[c].id==0){
            filmTimeHtmIdPrefix ="filmTimeNew";
        }else{
            filmTimeHtmIdPrefix = "filmTime";
        }
      filmTimeHtmIdStr = filmTimeHtmIdPrefix+filmTimeHtmId++;
      filmTimes[c].htmlId = filmTimeHtmIdStr;
      filmTimes[c].options = {id:"film_"+filmTimes[c].filmId,
        data:{
          //camelCase does not work
          htmlid:filmTimes[c].htmlId,
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




var DISPLAY_DATE_FORMAT = "MMMM Do YYYY";
function showFilmTimeModal(){
    $("#changeFilmTimeModal").modal();
    var elemId = $("#currentFilmTimeId").val();
    var filmTimeDetails =$("#"+elemId).data();
    var startTime = filmTimeDetails.starttime;
    startTime = ( startTime.split(":")[0].length==1 )?"0"+startTime:startTime;

    var endTime = filmTimeDetails.endtme;
    endTime = ( endTime.split(":")[0].length==1 )?"0"+endTime:endTime;

    $("#screenName").html(filmTimeDetails.screenname);
    $("#modalScheduleDateSelector").val(filmTimeDetails.scheduleid);
    $("#modalFilmSelector").val(filmTimeDetails.filmid);
    $("#modalStartTimePicker").val(startTime);
    $("#modalEndTimePicker").val(endTime);
}
function hideFilmTimeDetails(){
    $("#filmTimeDetails").fadeOut(200);
}
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
  $("#addFilmToSchedulStatusMsg").html("").hide();
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

  if(moment(endTime).diff(startTime,'minutes')<0){
    $("#addFilmToSchedulStatusMsg").html("Stat time is greater then end date").show();
      return;
  }

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

    if(isNew){
        drawTimeTable();
        var tempFilmTime = getFilmTimeByScheduleIdAndIdentifier(index,filmTime.identifier);
        $("html, body").animate({ scrollTop: $("#"+tempFilmTime.htmlId).offset().top }, "slow");
        $("#"+tempFilmTime.htmlId).removeClass("time-entry").addClass("recent-film-time-entry");
        setTimeout(function(){
            $("#"+tempFilmTime.htmlId).removeClass("recent-film-time-entry").addClass("time-entry");
        },3000);

        $("#addFilmToSchedulStatusMsg").html("").hide();

    }
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
  submitCreateSchedule(postDataArray,false);

}

function submitCreateSchedule(postDataArray,flag){
  var postData;
  if(postDataArray.length > 0){
    postData = postDataArray.pop();
  }else{
    if(flag){
      createScheduling();
      $("#submitCreateScheduleMsg").html("Successful updated").show();
      setTimeout(function(){
        $("#submitCreateScheduleMsg").html("").hide();
        enableDisableFormElement("page-wrapper",["input","button","select"],true);
      },3000);
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

function updateFilmTime(){

  var filmElemTimeId = $("#currentFilmTimeId").val();
  var filmTimeId =$("#"+filmElemTimeId).data("id");
  var filmTimeIdentifier =$("#"+filmElemTimeId).data("identifier");
  var filmScheduleDate =$("#"+filmElemTimeId).data("scheduledate");
  var scheduleId = $("#modalScheduleDateSelector").val();
  var filmId = $("#modalFilmSelector").val();
  var startTime = $("#modalStartTimePicker").val();
  var endTime = $("#modalEndTimePicker").val();

  enableDisableFormElement("changeFilmTimeModal",["input","button","select"],false);

  scheduleId = parseInt(scheduleId);
  if(moment("2017-01-01 "+endTime).diff("2017-01-01 "+startTime,'minutes')<0){
      enableDisableFormElement("changeFilmTimeModal",["input","button","select"],true);
      $("#changeFilmTimeModalStatusMsg").html("Start time is greater then end date");
      return;
  }

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
        enableDisableFormElement("changeFilmTimeModal",["input","button","select"],true);
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



      $("#changeFilmTimeModalStatusMsg").html("Successfully updated").show();
      setTimeout(function(){
        $("#changeFilmTimeModalStatusMsg").hide().html("");
        $("#filmTimeDetails").hide();
        $("#changeFilmTimeModal").modal("hide");
          enableDisableFormElement("changeFilmTimeModal",["input","button","select"],true);
      },3000);
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

  enableDisableFormElement("filmTimeDetails",["input","button","select"],false);
  $("#filmTimeDetailsStatusMsg").html("Processing...").show();
  $.ajax({
    url: BASEURL+'api/admin/film-scheduling/delete/film-time/'+filmTimeId,
    type: 'POST',
    data: {
    },
    statusCode: {
      401: function (response) {
        console.log("unauthorized");
        // showLoginModal();
        enableDisableFormElement("createFilmForm",["input","button","select"],true);
      },
      422: function (response) {
        console.log(response);
        $("#filmTimeDetailsStatusMsg").html("Error found").show();
        enableDisableFormElement("filmTimeDetails",["input","button","select"],true);
      },
      204:function(response){
        console.log("ASD");
      }
    },success: function(data,textStatus, xhr){
      var index = getFilmScheduleRowBydate(filmScheduleDate);
      if(index<0)return;
      deleteFilmTimeByScheduleIdAndIdentifier(index,filmTimeIdentifier);
      drawTimeTable();
      $("#filmTimeDetailsStatusMsg").html("Successfully removed").show();
      setTimeout(function(){
        $("#filmTimeDetailsStatusMsg").hide().html("");
        $("#filmTimeDetails").hide();
          enableDisableFormElement("filmTimeDetails",["input","button","select"],true);
      },3000);


    }
  });
}