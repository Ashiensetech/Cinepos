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
                    <h1 class="page-header">${(not empty vatSetting)?"EDIT":"ADD"} Vat Setting</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="createVatSettingForm">
                        <input type="hidden" value="${vatSetting.id}" id="vatSettingId">
                        <div class="form-group">
                            <label>Name</label>
                            <input class="form-control" id="name"  value="${vatSetting.name}">
                            <p class="help-block error" id="errorMsg_name"></p>

                        </div>
                        <div class="form-group">
                            <label>Vat Amount</label>
                            <input type="number" min="0" class="form-control" id="amount" value="${vatSetting.amount}">
                            <p class="help-block error" id="errorMsg_amount"></p>

                        </div>
                        <div class="form-group">
                            <label>Description</label>
                            <textarea name="" class="form-control" id="description">${vatSetting.description}</textarea>
                            <p class="help-block error" id="errorMsg_description"></p>

                        </div>


                        <hr>
                        <div class="col-md-12 nopadding">
                            <p class="help-block" id="statusMsg"></p>
                            <button type="button" class="btn btn-primary" id="vatSettingBtn">${(not empty vatSetting)?"EDIT":"ADD"}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
        <!-- /#page-wrapper -->

    </div>

    <jsp:directive.include file="../layouts/footer.jsp"/>

    <script type="application/javascript">
        $(document).ready(function () {
            $('#vatSettingBtn').click(function () {
                var name=$("#name").val();
                var amount=$("#amount").val();
                var description=$("#description").val();
                var vatSettingId=$("#vatSettingId").val();

                var pageData={
                    name:name,
                    amount:amount,
                    description:description
                };

                if(vatSettingId=="")
                    pageData['id']=null;
                else
                    pageData['id']=vatSettingId;


                enableDisableFormElement("createVatSettingForm",["input","button","select","textarea"],false);

                $.ajax({
                    url: BASEURL+'api/admin/vat-setting/create',
                    type: 'POST',
                    data: pageData,
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("createVatSettingForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("createVatSettingForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html(data.msg).show();
                        setTimeout(function(){
                            window.location = "/admin/vat-setting/index";
                        },2000);
                    }
                });
            });
        });
    </script>

    <!-- Date picker -->
</body>

</html>


