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
                    <h1 class="page-header">List OF admins</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">

                    <button class="btn btn-primary">Add Circuit Info</button>
                    <hr>
                    <div class="table-responsive table-bordered">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Site Code</th>
                                <th>Site Name</th>
                                <th>Address</th>
                                <th>City</th>
                                <th>Country</th>
                                <th>Website</th>
                                <th>Phone Number</th>
                                <th>Number Of Screen</th>
                                <th>Booking Cancellation Time</th>
                                <th>Refund Cancellation Time</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>Jonathan</td>
                                <td>Harper</td>
                                <td>dummy@gmail.com</td>
                                <td>017177674854</td>
                                <td>Male</td>
                                <td>12 January 1980</td>
                                <td>Admin</td>
                                <td>Admin</td>
                                <td>Admin</td>
                                <td>Admin</td>
                                <td>
                                    <button class="btn btn-sm btn-success">Enabled</button>
                                    <button class="btn btn-sm btn-primary">Edit</button>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
            </div>
        </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<!-- Date picker -->
</body>

</html>


