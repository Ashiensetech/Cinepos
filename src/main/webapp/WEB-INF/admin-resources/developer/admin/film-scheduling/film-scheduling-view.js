
function initializeFilmSchedulerView(data){
  var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  var endDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  filmScheduler = [];



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
}
function showScheduling(){

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
      if(xhr.status == 204){
        $('#scheduleViewer').html("No schedule found");
      }else{
        initializeFilmSchedulerView(data);
      }

      drawTimeTable();
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],true);
    }
  });

}


function showSchedulingToGenerateTicket(){

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


  $("#screenSeats").html("");

  $.ajax({
    url: BASEURL+'api/app/film-scheduling/get-all-in-date-range/'+screenId,
    type: 'POST',
    data: {"startDate":startDate,"endDate":endDate},
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
      if(xhr.status == 204){
        $('#scheduleViewer').html("No schedule found");
      }else{
        initializeFilmSchedulerViewForTicketGenerate(data);
      }

      drawTimeTable(function(elem){
        console.log("OVERRIDDEN");
        var filmTimeInf = $(elem).data();
        var screenId = $("#screenSelector").val();

        getScreenSeatsByFilmTimeId(filmTimeInf.id);
        var filmTimeDetails = $(elem).data();
        $("#currentFilmTimeId").val($(elem).attr("id"));
        $("#filmTimeDetails").show();
        $("#screenName").html(filmTimeDetails.screenname);
        $("#scheduleDate").html(filmTimeDetails.scheduledate);
        $("#filmName").html(filmTimeDetails.filmname);
        $("#startTimeSpan").html(filmTimeDetails.starttime);
        $("#endTimeSpan").html(filmTimeDetails.endtme);

      });
      enableDisableFormElement("filmTimingAddForm",["input","button","select"],true);
    }
  });

}

function initializeFilmSchedulerViewForTicketGenerate(data){
  var startDate = $("#startDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  var endDate =$("#endDate").data('datepicker').getFormattedDate("yyyy-mm-dd");
  filmScheduler = [];



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
}

function getScreenSeatsByFilmTimeId(filmTimeId){
    console.log("Getting Seats");
    $.ajax({
        url: BASEURL+'admin/ticket/seat/partial/'+filmTimeId,
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
            $("#screenSeats").html(data);

        }
    });
}