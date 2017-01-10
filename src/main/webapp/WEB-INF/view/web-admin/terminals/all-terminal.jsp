<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
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
                    <h1 class="page-header">Terminal List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables">
                        <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Ip Address</th>
                            <th>Terminal Code</th>
                            <th>Email 2</th>
                            <th>Type</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <d:choose>
                            <d:when test="${not empty terminalList}">
                                <d:forEach var="terminalValue" items="${terminalList}">
                                    <d:set var="count" value="${count+1}" />
                                    <tr class="odd gradeC" id="terminalRow${terminalValue.id}">
                                        <td>${count}</td>
                                        <td>${terminalValue.name}</td>
                                        <td>${terminalValue.ipAddress}</td>
                                        <td>${terminalValue.terminalCode}</td>
                                        <td>${terminalValue.type}</td>
                                        <td id="statusTd${terminalValue.id}">${(terminalValue.status==1)?"Active":"Deactive"}</td>
                                        <td>
                                            <button id="statusChangeBtn${terminalValue.id}"
                                                    data-status="${terminalValue.status}"
                                                    onclick="statusUpdateTerminalData('terminalRow${terminalValue.id}',
                                                            'statusMsg${terminalValue.id}',
                                                            'statusChangeBtn${terminalValue.id}',
                                                            'statusTd${terminalValue.id}',
                                                        ${terminalValue.id})"
                                                    class="btn btn-outline btn-primary" >
                                                <d:if test="${terminalValue.status==1}">
                                                    Deactivate
                                                </d:if>
                                                <d:if test="${terminalValue.status==0}">
                                                    Active
                                                </d:if>
                                            </button>
                                            <a href="<c:url value="/admin/terminal/edit/${terminalValue.id}" />"
                                               type="button"
                                               class="btn btn-outline btn-primary" >Edit</a>
                                            <p id="statusMsg${terminalValue.id}"></p>
                                        </td>
                                    </tr>
                                </d:forEach>
                            </d:when>
                            <d:otherwise>
                                <p>Terminals Empty</p>
                            </d:otherwise>
                        </d:choose>

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

<script type="application/javascript">

    function statusUpdateTerminalData(parentElementId,statusMsgElemId,elementId,statusTd,terminalId){

        $("#"+statusMsgElemId).html("").hide();

        var activationStatus =$("#"+elementId).data("status");
        var activationType =(activationStatus)?"deactivate":"activate";
        enableDisableFormElement(parentElementId,["input","button","select","a"],false);

        $.ajax({
            url: BASEURL+'api/admin/terminal/active-inactive/'+terminalId+'/'+activationType,
            type: 'POST',
            statusCode: {
                401: function (response) {
                    showLoginModal();
                    enableDisableFormElement(parentElementId,["input","button","select","a"],true);
                },
                422: function (response) {
                    enableDisableFormElement(parentElementId,["input","button","select","a"],true);
                    BindErrorsWithHtml("errorMsg_",response.responseJSON);
                    $("#"+statusMsgElemId).html("Server error").fadeIn(1000,function(){
                        $(this).fadeOut(1000,function(){
                            $(this).html("");
                        });
                    });


                }
            },success: function(data){

                var btnText = (data.status)?"Deactivate":"Activate";
                var statusTdText = (data.status)?"Activate":"Inactivate";

                $("#"+elementId).html(btnText);
                $("#"+elementId).data("status",data.status);
                $("#"+statusTd).html(statusTdText);
                enableDisableFormElement(parentElementId,["input","button","select","a"],true);
                $("#"+statusMsgElemId).html("Status updated").fadeIn(1000,function(){
                    $(this).fadeOut(1000,function(){
                        $(this).html("");
                    });
                });
            }
        });
        return false;
    }
</script>

    <!-- Date picker -->
</body>

</html>


