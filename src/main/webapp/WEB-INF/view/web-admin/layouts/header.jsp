<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cinepos</title>

    !-- Bootstrap Core CSS -->
    <link href="<c:url value="/admin-resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/admin-resources/metisMenu/metisMenu.min.css" /> " rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/admin-resources/dist/css/sb-admin-2.css" /> " rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Titillium+Web:300,400,600,700,900" rel="stylesheet">
    <link href="<c:url value="/admin-resources/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Cinepos</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="index.html">Dashboard</a>
                    </li>
                    <li>
                        <a href="#">Admin<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="create.html">Create Admin</a>
                            </li>
                            <li>
                                <a href="edit.html">Edit Admin</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
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
                    <form>
                        <div class="form-group">
                            <label>First Name</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Phone number</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Sex</label>
                            <select class="form-control">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Date of Birth</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label>Select Role</label>
                            <select class="form-control">
                                <option>Admin</option>
                                <option>Super Admin</option>
                            </select>
                        </div>
                        <br>
                        <button class="btn btn-primary">Create Admin</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<%--
<script src="<c:url value="/admin-resources/jquery/jquery.min.js">"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/admin-resources/bootstrap/js/bootstrap.min.js">"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/admin-resources/metisMenu/metisMenu.min.js">"></script>

<!-- Custom Theme JavaScript -->
<<<<<<< HEAD
<script src="../dist/js/sb-admin-2.js"></script>
=======
<script src="<c:url value="../dist/js/sb-admin-2.js">"></script>
--%>
>>>>>>> 3232dec16c4a99a91f79f0135aff21615f9d3a6d

<!-- Include Date Range Picker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>

<!-- Date picker -->
</body>

</html>