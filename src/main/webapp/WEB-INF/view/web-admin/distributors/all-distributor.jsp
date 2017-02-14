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
                            <th>Email1</th>
                            <th>Email 2</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Desc</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        <d:choose>
                            <d:when test="${not empty distributors}">
                                <d:forEach var="distributorValue" items="${distributors}">
                                    <d:set var="count" value="${count+1}" />
                                    <tr class="odd gradeC distributorRowCls" data-distributor="${distributorValue.id}" id="distributorRow${distributorValue.id}">
                                        <td>${count}</td>
                                        <td>${distributorValue.name}</td>
                                        <td>${distributorValue.primaryEmail}</td>
                                        <td>${distributorValue.secondaryEmail}</td>
                                        <td>${distributorValue.phone}</td>
                                        <td>${distributorValue.address}</td>
                                        <td>${distributorValue.description}</td>
                                        <td id="statusTd${distributorValue.id}">${(distributorValue.status==1)?"Active":"Inactive"}</td>
                                        <td>
                                            <button id="statusChangeBtn${distributorValue.id}"
                                                    data-status="${distributorValue.status}"
                                                    onclick="statusUpdateDistributorData('distributorRow${distributorValue.id}',
                                                            'statusMsg${distributorValue.id}',
                                                            'statusChangeBtn${distributorValue.id}',
                                                            'statusTd${distributorValue.id}',
                                                        ${distributorValue.id})"
                                                    class="btn btn-outline btn-primary" >
                                                <d:if test="${distributorValue.status==1}">
                                                    Deactivate
                                                </d:if>
                                                <d:if test="${distributorValue.status==0}">
                                                    Active
                                                </d:if>
                                            </button>
                                            <a href="<c:url value="/admin/distributor/edit/${distributorValue.id}" />"
                                               type="button"
                                               class="btn btn-outline btn-primary" >Edit</a>
                                            <p id="statusMsg${distributorValue.id}"></p>
                                        </td>
                                    </tr>
                                </d:forEach>
                            </d:when>
                            <d:otherwise>
                                 <p>Distributor's Empty</p>
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
                var statusTdText = (data.status)?"Active":"Inactive";

                $("#"+elementId).html(btnText);
                $("#"+elementId).data("status",data.status);
                if($("#"+statusTd).length >0){
                    $("#"+statusTd).text(statusTdText);
                }else{
                    $("#"+parentElementId).next().find("span.dtr-title").each(function(){
                        if($(this).text().trim()=="Status:"){
                            $(this).next().html(statusTdText);
                        }
                    });
                }


                //$("#"+distributorId).next("tr").find(".dtr-data:eq( 1 )").text(statusTdText);

                console.log(statusTd);


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


