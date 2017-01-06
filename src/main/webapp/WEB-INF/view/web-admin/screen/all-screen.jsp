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
          <h1 class="page-header">All users</h1>
        </div>
        <!-- /.col-lg-12 -->
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="panel panel-default">
            <div class="panel-heading">
              Admin users
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
              <table width="100%" class="table table-striped table-bordered table-hover" id="adminUsers">
                <thead>
                <tr>
                    <th>No</th>
                    <th>Name</th>
                    <th>No of seat</th>
                    <th>Screen type</th>
                    <th>Row count</th>
                    <th>Column count</th>
                    <th>Opening time</th>
                    <th>Closing time</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <d:set var="count" value="0" />
                <d:forEach var="screen" items="${screens}" >
                    <d:set var="count" value="${count+1}" />
                  <tr id="screenRow${screen.id}">
                    <td>${count}</td>
                    <td>${screen.name}</td>
                    <td>${screen.noOfSeat}</td>
                    <td>${screen.screenDimension.name} </td>
                    <td>${screen.rowCount}</td>
                    <td>${screen.columnCount}</td>
                    <td>${screen.openingTime}</td>
                    <td>${screen.closingTime}</td>
                    <td id="statusTd${screen.id}" >
                      <d:if test="${screen.active}">
                        Active
                      </d:if>
                      <d:if test="${!screen.active}">
                        Inactive
                      </d:if>
                    </td>
                    <td>

                      <button id="statusChangeBtn${screen.id}"
                              data-status="${screen.active}"
                              onclick="statusUpdateScreenData('screenRow${screen.id}',
                                                              'statusMsg${screen.id}',
                                                              'statusChangeBtn${screen.id}',
                                                              'statusTd${screen.id}',
                                                              ${screen.id})"
                              class="btn btn-outline btn-primary" >
                                  <d:if test="${screen.active}">
                                    Deactivate
                                  </d:if>
                                  <d:if test="${!screen.active}">
                                     Active
                                  </d:if>
                      </button>


                      <a href="<c:message code="base.uri" />/admin/screen/edit/${screen.id}"
                           type="button"
                           class="btn btn-outline btn-primary" >Edit</a>
                      <p id="statusMsg${screen.id}"></p>
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
    $('#adminUsers').DataTable({
      responsive: true
    });
  });

  function statusUpdateScreenData(parentElementId,statusMsgElemId,elementId,statusTd,screenId){

    $("#"+statusMsgElemId).html("").hide();

    var activationStatus =$("#"+elementId).data("status");
    var activationType =(activationStatus)?"deactivate":"activate";
    enableDisableFormElement(parentElementId,["input","button","select","a"],false);

    $.ajax({
      url: BASEURL+'/api/admin/screen/active-inactive/'+screenId+'/'+activationType,
      type: 'POST',
      statusCode: {
        401: function (response) {
          showLoginModal();
          enableDisableFormElement(parentElementId,["input","button","select","a"],true);
        },
        422: function (response) {
          enableDisableFormElement(parentElementId,["input","button","select","a"],true);
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
          $("#"+statusMsgElemId).html("Server error").fadeIn(1000,function(){
            $(this).fadeOut(1000,function(){
              $(this).html("");
            });
          });


        }
      },success: function(data){

        var btnText = (data.active)?"Deactivate":"Activate";
        var statusTdText = (data.active)?"Activate":"Inactivate";

        $("#"+elementId).html(btnText);
        $("#"+elementId).data("status",data.active);
        $("#"+statusTd).html(statusTdText);
        enableDisableFormElement(parentElementId,["input","button","select","a"],true);
        $("#"+statusMsgElemId).html("Status updated").fadeIn(1000,function(){
          $(this).fadeOut(1000,function(){
            $(this).html("");
          });
        });
      }
    });
    return false;
  }



</script>
</body>

</html>


