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
          <h1 class="page-header">All Seat Price Shifts</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              Admin users
            </div>
              <div class="notification">
                  <p id="notification"></p>
              </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
              <table width="100%" class="table table-striped table-bordered table-hover" id="priceShiftTable">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <d:set var="count" value="0" />
                <d:forEach var="item" items="${seatPriceShifts}" >
                    <d:set var="count" value="${count+1}" />
                  <tr>
                    <td>${count}</td>
                    <td>${item.seatType.name}</td>
                    <td>${item.price}</td>
                    <td>${item.startDate} </td>
                    <td>${item.endDate}</td>
                    <td>${item.status}</td>
                    <td >
                      <a href="<c:url value="/admin/seat-price-shift/edit/${item.id}" />"
                         type="button"
                         class="btn btn-outline btn-primary" >Edit</a>
                      <button data-price-shift-id="${item.id}"
                              class="btn btn-outline btn-primary delete-price-shift" >
                        Delete
                      </button>

                    </td>

                  </tr>
                </d:forEach>

                </tbody>
              </table>
            </div>
            <!-- /.panel-body -->
          </div>
          <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
      </div>
    </div>
    <!-- /.container-fluid -->
  </div>
  <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<script>
  $(document).ready(function() {
    $('#priceShiftTable').DataTable({
      responsive: true
    });

      $(".delete-price-shift").click(function(){
          var priceShiftId = $(this).attr('data-price-shift-id');
          console.log(priceShiftId);
          var crntRow= $(this).parent().parent();

          $.ajax({
              url: BASEURL+'api/admin/seat-price-shift/delete/'+priceShiftId,
              type: 'DELETE',
              statusCode: {
                  401: function (response) {
                      console.log("unauthorized");
                      console.log(response);
                  },
                  422: function (response) {
                      console.log(response);
                  }
              },success: function(data){
                  $("#notification").html("Price Shift deleted successfully").show();
                  crntRow.remove();
              }
          });

      });

  });

</script>
</body>

</html>


