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
          <form id="createUserForm">
            <div class="form-group">
              <label>First Name</label>
              <input class="form-control" id="firstName">
              <p class="help-block error" id="errorMsg_firstName"></p>
            </div>
            <div class="form-group">
              <label>Last Name</label>
              <input class="form-control"  id="lastName" >
              <p class="help-block error" id="errorMsg_lastName"></p>
            </div>
            <div class="form-group">
              <label>Phone number</label>
              <input class="form-control" id="phone" >
              <p class="help-block error" id="errorMsg_phone"></p>
            </div>
            <div class="form-group">
              <label>Address</label>
              <textarea class="form-control" id="address" ></textarea>
              <p class="help-block error" id="errorMsg_address"></p>
            </div>
            <div class="form-group">
              <label>Email</label>
              <input class="form-control" id="email" >
              <p class="help-block error" id="errorMsg_email"></p>
            </div>


            <div class="form-group">
              <label>User name</label>
              <input class="form-control" id="user_userName" >
              <p class="help-block error" id="errorMsg_userName"></p>
            </div>
            <div class="form-group">
              <label>Password</label>
              <input class="form-control" id="user_password" type="password" >
              <p class="help-block error" id="errorMsg_password"></p>
            </div>
            <div class="form-group">
              <label>Sex</label>
              <select class="form-control" id="gender" >
                <option>Male</option>
                <option>Female</option>
              </select>
              <p class="help-block error" id="errorMsg_gender"></p>
            </div>
            <div class="form-group">
              <label>Date of Birth</label>
              <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                <input id="dob" type='text' class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text" />

              </div>
              <p class="help-block error" id="errorMsg_dob"></p>
            </div>
            <div class="form-group">
              <label>Select Role</label>
              <select class="form-control" id="roleId" >
                <d:forEach var="userRole" items="${userRoles}" >
                    <option value="${userRole.id}">${userRole.displayName}</option>
                </d:forEach>
              </select>
              <p class="help-block error" id="errorMsg_roleId"></p>

            </div>
            <br>
            <p class="help-block" id="statusMsg"></p>
            <button class="btn btn-primary" onclick="return submitUserData()">Create Admin</button>
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
  function submitUserData(){

    $("#statusMsg").html("").hide();

    var firstName =$("#firstName").val();
    var lastName=$("#lastName").val();
    var email=$("#email").val();
    var phone=$("#phone").val();
    var address=$("#address").val();
    var gender=$("#gender").val();
    var status=$("#status").val();
    var userName=$("#user_userName").val();
    var password=$("#user_password").val();
    var roleId = $("#roleId").val();
    var dob = $("#dob").val();
    enableDisableFormElement("createUserForm",["input","button","select"],false);
    $.ajax({
      url: BASEURL+'api/admin/user/create',
      type: 'POST',
      data: {
        firstName:firstName,
        lastName:lastName,
        email:email,
        phone:phone,
        address:address,
        gender:gender,
        status:status,
        userName:userName,
        password:password,
        roleId: roleId,
        dob:dob
      },statusCode: {
        401: function (response) {
          console.log("unauthorized");
          console.log(response);
          showLoginModal();
          enableDisableFormElement("createUserForm",["input","button","select"],true);
        },
        422: function (response) {
          console.log(response);
          $("#statusMsg").html("Error found").show();
          BindErrorsWithHtml("errorMsg_",response.responseJSON);
          enableDisableFormElement("createUserForm",["input","button","select"],true);
        }
      },success: function(data){
        $("#statusMsg").html("User created successfully").show();
        setTimeout(function(){
          window.location = BASEURL+"admin/user/all";
        },2000);


      }
    });
    return false;
  }
</script>
</body>

</html>


