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
                            <th>Annotation</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Unit</th>
                            <th>Selling Price</th>
                            <th>Buying Price</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <d:choose>
                            <d:when test="${not empty concessionProductList}">
                                <d:forEach var="concessionProductValue" items="${concessionProductList}">
                                    <d:set var="count" value="${count+1}" />
                                    <tr class="odd gradeC" id="distributorRow${concessionProductValue.id}">
                                        <td>${count}</td>
                                        <td>${concessionProductValue.name}</td>
                                        <td>${concessionProductValue.annotation}</td>
                                        <td>${concessionProductValue.description}</td>
                                        <td>${concessionProductValue.concessionProductCategory.name}</td>
                                        <td>${concessionProductValue.unit}</td>
                                        <td>${concessionProductValue.sellingPrice}</td>
                                        <td>${concessionProductValue.buyingPrice}</td>
                                        <td id="statusTd${concessionProductValue.id}">${(concessionProductValue.status==1)?"Active":"Deactive"}</td>
                                        <td>
                                            <button id="statusChangeBtn${concessionProductValue.id}"
                                                    data-status="${concessionProductValue.status}"
                                                    onclick="statusUpdateDistributorData('distributorRow${concessionProductValue.id}',
                                                            'statusMsg${concessionProductValue.id}',
                                                            'statusChangeBtn${concessionProductValue.id}',
                                                            'statusTd${concessionProductValue.id}',
                                                        ${concessionProductValue.id})"
                                                    class="btn btn-outline btn-primary" >
                                                <d:if test="${concessionProductValue.status==1}">
                                                    Deactivate
                                                </d:if>
                                                <d:if test="${concessionProductValue.status==0}">
                                                    Active
                                                </d:if>
                                            </button>
                                            <a href="<c:url value="/admin/concession-product/edit/${concessionProductValue.id}" />"
                                               type="button"
                                               class="btn btn-outline btn-primary" >Edit</a>
                                            <p id="statusMsg${concessionProductValue.id}"></p>
                                        </td>
                                    </tr>
                                </d:forEach>
                            </d:when>
                            <d:otherwise>
                                 <p>distributorValue Empty</p>
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

    function statusUpdateDistributorData(parentElementId,statusMsgElemId,elementId,statusTd,distributorId){

        $("#"+statusMsgElemId).html("").hide();

        var activationStatus =$("#"+elementId).data("status");
        var activationType =(activationStatus)?"deactivate":"activate";
        enableDisableFormElement(parentElementId,["input","button","select","a"],false);

        $.ajax({
            url: BASEURL+'api/admin/distributor/active-inactive/'+distributorId+'/'+activationType,
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


