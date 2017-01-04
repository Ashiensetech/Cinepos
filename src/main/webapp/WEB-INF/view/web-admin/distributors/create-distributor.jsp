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
                    <h1 class="page-header">Add Distributor</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form>
                        <div class="form-group">
                            <label>Name</label>
                            <input id="name" value="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Primary Email</label>
                            <input id="primary_email" value="" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Secondary Email</label>
                            <input class="form-control" id="secondary_email" value="">
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input class="form-control" id="phone" value="">
                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea name="" class="form-control" id="address"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="" class="form-control" id="description"></textarea>
                        </div>

                        <br>
                        <button type="button" id="distributorBtn" class="btn btn-primary">SAVE</button>
                    </form>
                </div>
            </div>
        </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp"/>

    <script type="application/javascript">
        $(document).ready(function () {
            $('#distributorBtn').click(function () {
               var name=$("#name").val();
               var primary_email=$("#primary_email").val();
               var secondary_email=$("#secondary_email").val();
               var phone=$("#phone").val();
               var address=$("#address").val();
               var description=$("#description").val();

                $.ajax({
                    url: '/api/admin/distributor/create',
                    type: 'POST',
                    data: {
                        name:name,
                        primary_email:primary_email,
                        secondary_email:secondary_email,
                        secondary_email:secondary_email,
                        phone:phone,
                        address:address,
                        description:description,
                    },
                    success: function(data){

                    }
                });
            });
        });
    </script>

<!-- Date picker -->
</body>

</html>


