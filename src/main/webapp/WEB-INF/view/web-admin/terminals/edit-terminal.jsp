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
                    <h1 class="page-header">Edit Terminal</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <form id="editTerminalForm">
                        <div class="form-group">
                            <input class="form-control" type="hidden" id="terminalId" value="${terminal.id}">

                            <label>name</label>
                            <input class="form-control" value="${terminal.name}" id="name">
                            <p class="help-block error" id="errorMsg_name"></p>

                        </div>
                        <div class="form-group">
                            <label>IP Address</label>
                            <input class="form-control" value="${terminal.ipAddress}" id="ip_address">
                            <p class="help-block error" id="errorMsg_ipAddress"></p>

                        </div>
                        <div class="form-group">
                            <label>Select Terminal Type</label>
                            <select class="form-control" id="terminal_type">
                                <option value="">Select Terminal Type</option>
                                <option value="pos" ${(terminal.type=="pos")?"selected":""}>POS</option>
                                <option value="kiosk" ${(terminal.type=="kiosk")?"selected":""}>KIOSK</option>
                            </select>
                            <p class="help-block error" id="errorMsg_type"></p>
                        </div>
                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button class="btn btn-primary" type="button" id="terminalBtn">edit</button>
                    </form>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>

    <jsp:directive.include file="../layouts/footer.jsp" />

    <script type="application/javascript">
        $(document).ready(function () {
            $('#terminalBtn').click(function () {
                var terminalId=$("#terminalId").val();
                var name=$("#name").val();
                var ip_address=$("#ip_address").val();
                var terminal_type=$("#terminal_type").val();

                enableDisableFormElement("editTerminalForm",["input","button","select","textarea"],false);


                $.ajax({
                    url: BASEURL+'/api/admin/terminal/edit/'+terminalId,
                    type: 'POST',
                    data: {
                        name:name,
                        ipAddress:ip_address,
                        type:terminal_type,
                    },
                    statusCode: {
                        401: function (response) {
                            console.log("unauthorized");
                            console.log(response);
                            enableDisableFormElement("editTerminalForm",["input","button","select","textarea"],true);

                        },
                        422: function (response) {
                            console.log(response);
                            enableDisableFormElement("editTerminalForm",["input","button","select","textarea"],true);
                            BindErrorsWithHtml("errorMsg_",response.responseJSON);
                        }
                    },
                    success: function(data){
                        $("#statusMsg").html("Terminal updated successfully").show();
                        setTimeout(function(){
                            window.location = "/admin/terminal/all";
                        },2000);
                    }
                });
            });
        });
    </script>

    <!-- Date picker -->
</body>

</html>


