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
                    <h1 class="page-header">EDIT Concession Product Category</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="editProductCategoryForm">
                        <input type="hidden" id="productCategoryId" value="${concessionProductCategory.id}">
                        <div class="form-group">
                            <label>Name</label>
                            <input id="name" value="${concessionProductCategory.name}" class="form-control">
                            <p class="help-block error" id="errorMsg_name"></p>
                        </div>
                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button type="button" id="productCategoryBtn" class="btn btn-primary">EDIT</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>

    <jsp:directive.include file="../layouts/footer.jsp"/>

    <script type="application/javascript">
        $(document).ready(function () {
            $('#productCategoryBtn').click(function () {
                var name=$("#name").val();
                var productCategoryId=$("#productCategoryId").val();

                enableDisableFormElement("editProductCategoryForm",["input","button","select","textarea"],false);


                $.ajax({
                    url: BASEURL+'api/admin/product-category/edit/'+productCategoryId,
                    type: 'POST',
                    data: {
                        name:name,
                    },
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("editProductCategoryForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("editProductCategoryForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html("Product Category updated successfully").show();
                        setTimeout(function(){
                            window.location = BASEURL+"admin/product-category/all";
                        },2000);
                    }
                });
            });
        });
    </script>

    <!-- Date picker -->
</body>

</html>


