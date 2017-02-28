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
          <h1 class="page-header">Edit Genr</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-6">
          <form id="updateGenreForm">
            <div class="form-group">
              <label>Name</label>
              <input class="form-control" id="genreId" value="${genre.id}" type="hidden" >
              <input class="form-control" id="name" value="${genre.name}">
              <p class="help-block error" id="errorMsg_name"></p>
            </div>

            <br>
            <p class="help-block" id="statusMsg"></p>
            <button class="btn btn-primary" onclick="return submitGenre()">Submit</button>
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
  function submitGenre(){

    $("#statusMsg").html("").hide();
    UnBindErrors("errorMsg_");
    var name =$("#name").val();
    var genreId =$("#genreId").val();

    enableDisableFormElement("updateGenreForm",["input","button"],false);
    $.ajax({
      url: BASEURL+'api/admin/genre/update/'+genreId,
      type: 'POST',
      data: {
        name:name
      },statusCode: {
        401: function (response) {
          console.log("unauthorized");
          console.log(response);
          showLoginModal();
          enableDisableFormElement("updateGenreForm",["input","button"],true);
        },
        422: function (response) {
          console.log(response);
          $("#statusMsg").html("Error found").show();
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
          enableDisableFormElement("updateGenreForm",["input","button"],true);
        }
      },success: function(data){
        $("#statusMsg").html("Genre successfully updated").show();
        setTimeout(function(){
          window.location = BASEURL+"admin/genre/all";
        },2000);


      }
    });
    return false;
  }
</script>
</body>

</html>


