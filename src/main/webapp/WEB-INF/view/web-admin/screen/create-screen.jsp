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
          <h1 class="page-header">Add admin</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-6">
          <form id="createScreenForm">
            <div class="form-group">
              <label>Screen Name</label>
              <input class="form-control" id="name">
              <p class="help-block error" id="errorMsg_name"></p>
            </div>
            <div class="form-group">
              <label>No of Seat</label>
              <input class="form-control" id="seatCount" type="number" min="1" >
              <p class="help-block error" id="errorMsg_seatCount"></p>
            </div>
            <div class="form-group">
              <label>Screen Type</label>
                <select class="form-control" id="screenTypeId">
                    <d:forEach var="screenDimension" items="${screenDimensions}" >
                        <option value="${screenDimension.id}">
                                ${screenDimension.name}
                        </option>
                    </d:forEach>
                </select>
              <p class="help-block error" id="errorMsg_screenTypeId"></p>
            </div>
            <div class="form-group">
              <label>Row Count</label>
              <input class="form-control" id="rowCount" type="number" min="1" >
              <p class="help-block error" id="errorMsg_rowCount"></p>
            </div>
            <div class="form-group">
              <label>Column Count</label>
              <input class="form-control" id="columnCount" type="number" min="1" >
              <p class="help-block error" id="errorMsg_columnCount"></p>
            </div>

            <div class="form-group">
              <label>Opening Time</label>
              <input class="form-control" type="time" id="openingTime">
              <p class="help-block error" id="errorMsg_openingTime"></p>
            </div>
            <div class="form-group">
              <label>Closing Time</label>
              <input class="form-control" type="time" id="closingTime">
              <p class="help-block error" id="errorMsg_closingTime"></p>
            </div>

            <br>
            <p class="help-block" id="statusMsg"></p>
            <button class="btn btn-primary" onclick="return submitScreenData()">ADD</button>
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
  function submitScreenData(){

    $("#statusMsg").html("").hide();


    var name =$("#name").val();
    var rowCount=$("#rowCount").val();
    var seatCount=$("#seatCount").val();
    var screenTypeId=$("#screenTypeId").val();
    var columnCount = $("#columnCount").val();
    var openingTime=$("#openingTime").val();
    var closingTime = $("#closingTime").val();
    openingTime = (openingTime=="")?null:openingTime+":00";
    closingTime = (closingTime=="")?null:closingTime+":00";


    enableDisableFormElement("createScreenForm",["input","button","select"],false);

    var postData =  {
      name:name,
      rowCount:rowCount,
      seatCount:seatCount,
      screenTypeId:screenTypeId,
      columnCount:columnCount
    };
    if(openingTime != null){
      postData["openingTime"] = openingTime;
    }
    if(closingTime != null){
      postData["closingTime"] = closingTime;
    }


    $.ajax({
      url: BASEURL+'/api/admin/screen/create',
      type: 'POST',
      data:postData,
      statusCode: {
        401: function (response) {
          console.log("unauthorized");
          console.log(response);
          showLoginModal();
          enableDisableFormElement("createScreenForm",["input","button","select"],true);
        },
        422: function (response) {
          console.log(response);
          enableDisableFormElement("createScreenForm",["input","button","select"],true);
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
        }
      },success: function(data){

        $("#statusMsg").html("Screen created successfully").show();
        setTimeout(function(){
          window.location = "/admin/screen/all";
        },2000);
      }
    });
    return false;
  }
</script>
</body>

</html>


