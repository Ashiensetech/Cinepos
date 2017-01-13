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
                    <h1 class="page-header">New film List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Film Name</th>
                            <th>Distributor</th>
                            <th>Film type</th>
                            <th>Ratings</th>
                            <th>Duration</th>
                            <th>Start date</th>
                            <th>End date</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <d:set var="count" value="${0}" />
                        <d:forEach var="film" items="${films}" >
                            <d:set var="count" value="${count+1}" />
                            <tr>
                                <td>${count}</td>
                                <td>${film.name} </td>
                                <td>${film.distributor.name}</td>
                                <td>
                                    <d:forEach var="genre" items="${film.filmGenre}" >
                                        <span class="info">${genre.name}</span>
                                    </d:forEach>
                                </td>
                                <td>${film.rating}</td>
                                <td>${film.durationHour} Hour ${film.durationMin} Min </td>
                                <td>${film.startDate}</td>
                                <td>${film.endDate}</td>
                                <td><a type="button" href="<c:url value="/admin/film/edit/${film.id}" />" class="btn btn-outline btn-primary">Edit</a> </td>

                            </tr>
                        </d:forEach>
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

    function fnSample(parentElementId,statusMsgElemId,elementId,statusTd,distributorId){

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
<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
</script>
</body>


</html>


