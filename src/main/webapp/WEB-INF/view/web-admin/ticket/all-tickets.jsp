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
                    <h1 class="page-header">All Tickets</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            All Tickets
                        </div>
                        <div class="notification">
                            <p id="notification"></p>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="ticketTable">
                                <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Seat Type</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Annotation</th>
                                    <th>Vat</th>
                                    <th>Price</th>
                                    <th>is Child</th>
                                    <th>is Adult</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <d:set var="count" value="0" />
                                <d:forEach var="item" items="${tickets}" >
                                    <d:set var="count" value="${count+1}" />
                                    <tr>
                                        <td>${count}</td>
                                        <td>${item.seatType.name}</td>
                                        <td>${item.name}</td>
                                        <td>${item.description}</td>
                                        <td>${item.annotation}</td>
                                        <td>${item.vat.name}</td>
                                        <td>${item.printedPrice}</td>
                                        <td>${item.child}</td>
                                        <td>${item.isAdult}</td>
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
        $('#ticketTable').DataTable({
            responsive: true
        });

    });

</script>
</body>

</html>


