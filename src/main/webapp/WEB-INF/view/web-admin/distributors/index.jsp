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
                    <h1 class="page-header">Distributor List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Email1</th>
                            <th>Email 2</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Desc</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="odd gradeC">
                            <td>Trident</td>
                            <td>demo@admin.com</td>
                            <td>demo@admin2.com</td>
                            <td>+880145754</td>
                            <td>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt, augue et euismod tristique, felis diam aliquam velit, eget egestas felis elit sed augue.
                            </td>
                            <td>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam tincidunt, augue et euismod tristique, felis diam aliquam velit, eget egestas felis elit sed augue.
                            </td>
                            <td>
                                <button type="" class="btn btn-primary"  data-toggle="modal" data-target="#editDistributor">Edit</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
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


