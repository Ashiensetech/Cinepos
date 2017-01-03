<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
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

<jsp:directive.include file="../layouts/footer.jsp" />

<!-- Date picker -->
</body>

</html>


