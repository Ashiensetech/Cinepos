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
          <h1 class="page-header">Seat plan</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-12">
          <%--<div class="col-md-5 center-block text-center">
            <div class="form-group">
              <label>Select Screen</label>
              <select class="form-control">
                <option>Select secreen name</option>
              </select>
            </div>
          </div>--%>
          <div id="content-8" class="content seat-assemble">
            <div class="seats-container">
              <table class="seat-table">
                <tbody>

                <d:forEach begin="0" end="${screen.rowCount}" varStatus="row">
                        <tr>
                            <d:forEach begin="${row.index*screen.columnCount}" end="${( screen.columnCount*(row.index+1) ) -1}" var="seat" items="${screen.seats}" varStatus="col">
                                <td>
                                    <div class="seat-single">
                                        <a href="#" data-toggle="modal" data-target="#seatDetail">${seat.name}</a>
                                    </div>
                                </td>
                            </d:forEach>
                        </tr>
                </d:forEach>

                </tbody>


              </table>
            </div>
          </div>

          <br>
          <div class="col-md-12 text-center">
            <button type="button" class="btn btn-lg btn-primary">Submit</button>
          </div>
        </div>
      </div>
      <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

  </div>
  <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<script>
  function updateScreenData(){
    $("#statusMsg").html("").hide();

    var screenId =$("#screenId").val();
    var name =$("#name").val();
    var rowCount=$("#rowCount").val();
    var seatCount=$("#seatCount").val();
    var screenTypeId=$("#screenTypeId").val();
    var columnCount = $("#columnCount").val();
    var openingTime=$("#openingTime").val();
    var closingTime = $("#closingTime").val();
    openingTime = (openingTime=="")?null:openingTime;
    closingTime = (closingTime=="")?null:closingTime;

    enableDisableFormElement("updateScreenForm",["input","button","select"],false);


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
      url: BASEURL+'api/admin/screen/edit/'+screenId,
      type: 'POST',
      data:postData,
      statusCode: {
        401: function (response) {
          console.log("unauthorized");
          console.log(response);
          enableDisableFormElement("updateScreenForm",["input","button","select"],true);
        },
        422: function (response) {
          console.log(response);
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
          enableDisableFormElement("updateScreenForm",["input","button","select"],true);
        }
      },success: function(data){

        $("#statusMsg").html("Screen update successfully").show();
        setTimeout(function(){
          window.location = BASEURL+"admin/screen/all";
        },2000);

      }
    });
    return false;
  }
</script>
</body>

</html>


