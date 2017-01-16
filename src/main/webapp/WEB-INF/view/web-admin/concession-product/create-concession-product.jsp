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
                    <h1 class="page-header">Add New Concession Product</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <form>
                    <div class="col-lg-5">
                        <div class="form-group">
                            <label>Annotation</label>
                            <textarea name="" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Unit</label>
                            <input class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Select Category</label>
                            <select class="form-control">
                                <option>Action</option>
                                <option>Drama</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Selling Price</label>
                            <input class="form-control">
                        </div>
                        <div class="form-group clearfix">
                            <label class="pull-left">Remote Print</label>
                            <div class="col-md-6">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch" checked>
                                    <label class="onoffswitch-label" for="myonoffswitch">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>

                            </div>
                        </div>

                    </div>

                    <div class="col-lg-7">
                        <div class="row clearfix">
                            <label style="padding-left: 15px;font-size: 16px;">Choose Image</label>
                            <div class="col-md-12">
                                <div class="checkbox">
                                </div>
                                <div action="/file-upload" class="dropzone">
                                    <div class="fallback">
                                        <input name="file" type="file" multiple />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="col-md-12 nopadding">
                        <button class="btn btn-primary">SAVE</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.container-fluid -->
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
                    url: BASEURL+'api/admin/distributor/create',
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
                            window.location = BASEURL+"admin/distributor/all";
                        },2000);
                    }
                });
            });
        });
    </script>

<!-- Date picker -->
</body>

</html>


