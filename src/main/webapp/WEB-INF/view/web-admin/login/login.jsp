<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cinepos</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/admin-resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/admin-resources/metisMenu/metisMenu.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/admin-resources/dist/css/sb-admin-2.css" />" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/admin-resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" onsubmit="return doLogin()" id="loginForm">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="User name" id="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" id="password" type="password" value="">
                                </div>
                                <%--<div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>--%>
                                <!-- Change this to a button or input when using this as a form -->
                                <input id="loginSubmit" type="submit" href="javascript:void(0)" class="btn btn-primary btn-block" value="Login" />
                                <p class="help-block" id="progressStatus"></p>
                                <p class="help-block error" id="loginError"></p>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="<c:url value="/admin-resources/jquery/jquery.min.js" />"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/admin-resources/bootstrap/js/bootstrap.min.js" />"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/admin-resources/metisMenu/metisMenu.min.js" />"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/admin-resources/dist/js/sb-admin-2.js" />"></script>

    <%--Developer Custom Js--%>
    <script type="text/javascript" src="<c:url value="/resources/js/helper/ErrorMessaging.js"/>"></script>
    <script>
        var BASEURL = "<c:message code="base.uri" />";
        function doLogin(){
            $("#loginError").html("").hide();
            $("#progressStatus").html("Processing...").show();
            $("#loginSubmit").attr("disabled","disabled");
            var userName = $('#username').val();
            var password = $('#password').val();

            enableDisableFormElement("loginForm",["input"],false);

//          console.log(email, password);
            $.ajax({
                url: BASEURL+'/auth/admin/do-auth',
                type: 'POST',
                data: {
                    userName: userName,
                    password: password
                },statusCode: {
                    401: function (response) {
                        console.log("unauthorized");
                        console.log(response);
                        $("#loginError").html(response.responseJSON.msg).show();
                        $("#loginSubmit").removeAttr("disabled");
                        $("#progressStatus").hide().html();
                        enableDisableFormElement("loginForm",["input"],true);
                    }
                },
                success: function (data) {
                    console.log("SUCCESS");
                    $("#progressStatus").html("Login Success");
                    $("#loginSubmit").removeAttr("disabled");
                    enableDisableFormElement("loginForm",["input"],true);
                    window.location = BASEURL+"/admin/user/all";
                }
            });
            return false;
        }
    </script>
</body>

</html>
