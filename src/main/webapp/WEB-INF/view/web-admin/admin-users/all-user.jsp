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
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <d:set var="count" value="0" />
                <d:forEach var="adminUser" items="${adminUsers}" >
                    <d:set var="count" value="${count+1}" />
                  <tr>
                    <td>${count}</td>
                    <td>${adminUser.userInf.firstName} ${adminUser.userInf.lastName}</td>
                    <td>${adminUser.userName}</td>
                    <td>${adminUser.userInf.email} </td>
                    <td>${adminUser.userRole.displayName}</td>
                    <td id="statusTd${adminUser.id}" >
                      <d:if test="${adminUser.isActivated}">
                        Active
                      </d:if>
                      <d:if test="${!adminUser.isActivated}">
                        Inactive
                      </d:if>
                    </td>
                    <td id="userRow${adminUser.id}">

                      <button id="statusChangeBtn${adminUser.id}"
                              data-status="${adminUser.isActivated}"
                              onclick="adminUserStatusUpdateScreenData('userRow${adminUser.id}',
                                      'statusMsg${adminUser.id}',
                                      'statusChangeBtn${adminUser.id}',
                                      'statusTd${adminUser.id}',
                                      ${adminUser.id})"
                              class="btn btn-outline btn-primary" >
                        <d:if test="${adminUser.isActivated}">
                          Deactivate
                        </d:if>
                        <d:if test="${!adminUser.isActivated}">
                          Active
                        </d:if>
                      </button>


                      <a href="<c:url value="/admin/user/edit/${adminUser.id}" />"
                         type="button"
                         class="btn btn-outline btn-primary" >Edit</a>
                      <p id="statusMsg${adminUser.id}"></p>
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

  function adminUserStatusUpdateScreenData(parentElementId,statusMsgElemId,elementId,statusTd,userCredentialId){

    $("#"+statusMsgElemId).html("").hide();

    var activationStatus =$("#"+elementId).data("status");
    var activationType =(activationStatus)?"deactivate":"activate";
    enableDisableFormElement(parentElementId,["input","button","select","a"],false);

    $.ajax({
      url: BASEURL+'api/admin/user/active-inactive/'+userCredentialId+'/'+activationType,
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
        $("#"+statusMsgElemId).html("Status updated").fadeIn(1000,function(){
          $(this).fadeOut(1000,function(){
            $(this).html("");
            enableDisableFormElement(parentElementId,["input","button","select","a"],true);
          });
        });
      }
    });
    return false;
  }

</script>
</body>

</html>


