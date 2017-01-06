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
                            <label>Site Code</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Site Name</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <div class="form-group">
                            <label>City</label>
                            <input class="form-control">
                            <p class="help-block error">Validation Error</p>
                        </div>
                        <br>
                        <button class="btn btn-primary">ADD</button>
                    </form>
                </div>
            </div>
        </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<!-- Date picker -->
</body>

</html>


