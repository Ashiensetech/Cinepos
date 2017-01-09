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
                    <form id="createDistributorForm">
                        <div class="form-group">
                            <label>Name</label>
                            <input id="name" value="" class="form-control">
                            <p class="help-block error" id="errorMsg_name"></p>
                        </div>
                        <div class="form-group">
                            <label>Primary Email</label>
                            <input id="primary_email" value="" class="form-control">
                            <p class="help-block error" id="errorMsg_primaryEmail"></p>
                        </div>
                        <div class="form-group">
                            <label>Secondary Email</label>
                            <input class="form-control" id="secondary_email" value="">
                            <p class="help-block error" id="errorMsg_secondaryEmail"></p>

                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input class="form-control" id="distributorPhone" value="">
                            <p class="help-block error" id="errorMsg_phone"></p>

                        </div>
                        <div class="form-group">
                            <label>Address</label>
                            <textarea name="" class="form-control" id="distributorAddress"></textarea>
                            <p class="help-block error" id="errorMsg_address"></p>
                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="" class="form-control" id="description"></textarea>
                            <p class="help-block error" id="errorMsg_description"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
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
               var phone=$("#distributorPhone").val();
               var address=$("#distributorAddress").val();
               var description=$("#description").val();

                enableDisableFormElement("createDistributorForm",["input","button","select","textarea"],false);


                $.ajax({
                    url: '/api/admin/distributor/create',
                    type: 'POST',
                    data: {
                        name:name,
                        primaryEmail:primary_email,
                        secondaryEmail:secondary_email,
                        secondary_email:secondary_email,
                        phone:phone,
                        address:address,
                        description:description,
                    },
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("createDistributorForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("createDistributorForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html("Distributor created successfully").show();
                        setTimeout(function(){
                            window.location = "/admin/distributor/all";
                        },2000);
                    }
                });
            });
        });
    </script>

<!-- Date picker -->
</body>

</html>


