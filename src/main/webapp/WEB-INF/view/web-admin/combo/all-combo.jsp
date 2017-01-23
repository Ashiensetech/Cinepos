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
                    <h1 class="page-header">Distributor List</h1>
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
                            <th>Details</th>
                            <th>Type</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Price</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <d:choose>
                            <d:when test="${not empty comboList}">
                                <d:forEach var="comboValue" items="${comboList}">
                                    <d:set var="count" value="${count+1}" />
                                    <tr class="odd gradeC" id="comboRow${comboValue.id}">
                                        <td>${count}</td>
                                        <td>${comboValue.comboName}</td>
                                        <td>${comboValue.details}</td>
                                        <td>${comboValue.comboType}</td>
                                        <td>${comboValue.startDate}</td>
                                        <td>${comboValue.endDate}</td>
                                        <td>${comboValue.price}</td>
                                        <td id="statusTd${comboValue.id}">${(comboValue.status==1)?"Active":"Deactive"}</td>
                                        <td>
                                            <button id="statusChangeBtn${comboValue.id}"
                                                    data-status="${comboValue.status}"
                                                    onclick="statusUpdateComboData('distributorRow${comboValue.id}',
                                                            'statusMsg${comboValue.id}',
                                                            'statusChangeBtn${comboValue.id}',
                                                            'statusTd${comboValue.id}',
                                                        ${comboValue.id})"
                                                    class="btn btn-outline btn-primary" >
                                                <d:if test="${comboValue.status==1}">
                                                    Deactivate
                                                </d:if>
                                                <d:if test="${comboValue.status==0}">
                                                    Active
                                                </d:if>
                                            </button>
                                            <a href="<c:url value="/admin/combo/edit/${comboValue.id}" />"
                                               type="button"
                                               class="btn btn-outline btn-primary" >Edit</a>

                                            <p id="statusMsg${comboValue.id}"></p>
                                        </td>
                                    </tr>
                                </d:forEach>
                            </d:when>
                            <d:otherwise>
                                <p>Combo's Empty</p>
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

<!-- Date picker -->

<script type="application/javascript">

    function statusUpdateComboData(parentElementId,statusMsgElemId,elementId,statusTd,comboId){

        $("#"+statusMsgElemId).html("").hide();

        var activationStatus =$("#"+elementId).data("status");
        var activationType =(activationStatus)?"deactivate":"activate";
        enableDisableFormElement(parentElementId,["input","button","select","a"],false);

        $.ajax({
            url: BASEURL+'api/admin/combo/active-inactive/'+comboId+'/'+activationType,
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
</body>


</html>


