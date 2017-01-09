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
          <h1 class="page-header">Add Seat Type</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-6">
          <form id="createSeatTypeForm">
            <div class="form-group">
              <label>Name</label>
              <input class="form-control" id="name">
              <p class="help-block error" id="errorMsg_name"></p>
            </div>
            <div class="form-group">
              <label>Adult Price</label>
              <input class="form-control"  id="adultPrice" >
              <p class="help-block error" id="errorMsg_adultPrice"></p>
            </div>
            <div class="form-group">
              <label>Adult Price</label>
              <input class="form-control"  id="childPrice" >
              <p class="help-block error" id="errorMsg_childPrice"></p>
            </div>

            <br>
            <p class="help-block" id="statusMsg"></p>
            <button class="btn btn-primary" onclick="return submitSeatType()">Create Seat Type</button>
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
  function submitSeatType(){

    $("#statusMsg").html("").hide();

    var name =$("#name").val();
    var adultPrice =$("#adultPrice").val();
    var childPrice =$("#childPrice").val();
    enableDisableFormElement("createSeatTypeForm",["input","button","select"],false);
    $.ajax({
      url: BASEURL+'api/admin/seat-type/create',
      type: 'POST',
      data: {
        name:name,
        adultPrice:adultPrice,
        childPrice:childPrice
      },statusCode: {
        401: function (response) {
          console.log("unauthorized");
          console.log(response);
          showLoginModal();
          enableDisableFormElement("createSeatTypeForm",["input","button","select"],true);
        },
        422: function (response) {
          console.log(response);
          $("#statusMsg").html("Error found").show();
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
          enableDisableFormElement("createSeatTypeForm",["input","button","select"],true);
        }
      },success: function(data){
        $("#statusMsg").html("Seat Type created successfully").show();
        setTimeout(function(){
          window.location = BASEURL+"admin/seat-type/all";
        },2000);


      }
    });
    return false;
  }
</script>
</body>

</html>


