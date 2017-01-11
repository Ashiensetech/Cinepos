<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:directive.include file="../layouts/header.jsp"/>
    <!-- Dropzone CSS -->
    <link href="<c:url value="/admin-resources/dropzone/dropzone.css" />" rel="stylesheet">
</head>


<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <jsp:directive.include file="../layouts/navbar-top.jsp"/>
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Add New Film</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <form id="createFilmForm">
                    <div class="col-lg-5">

                        <div class="form-group">
                            <label>Film Name</label>
                            <input id="name" class="form-control">
                            <p class="help-block error" id="errorMsg_name"></p>
                        </div>
                        <div class="form-group">
                            <label>Distributor</label>
                            <select id="distributorId" class="form-control">
                                <option></option>
                                <d:forEach var="distributor" items="${distributors}">
                                    <option value="${distributor.id}">${distributor.name}</option>
                                </d:forEach>
                            </select>
                            <p class="help-block error" id="errorMsg_distributorId"></p>
                        </div>
                        <div class="form-group">
                            <label>Select Film type</label>
                            <select class="form-control">
                                <option>Action</option>
                                <option>Drama</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Ratings</label>
                            <input id="rating" class="form-control">
                            <p class="help-block error" id="errorMsg_rating"></p>
                        </div>
                        <div class="form-group">
                            <label>Duration</label>
                            <input name="" id="duration" class="form-control">
                            <p class="help-block error" id="errorMsg_duration"></p>
                        </div>
                        <div class="form-group">
                            <label>Start date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="startDate" name="date"
                                       placeholder="MM/DD/YYY" type="text"/>
                                <p class="help-block error" id="errorMsg_startDate"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>End date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="endDate" name="date" placeholder="MM/DD/YYY"
                                       type="text"/>
                                <p class="help-block error" id="errorMsg_endDate"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Trailer link</label>
                            <input type="text" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Film view Type</label><br>
                            <label class="checkbox-inline">
                                <input type="checkbox">2D
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox">3D
                            </label>
                        </div>

                        <div class="form-group">
                            <label>Is Default</label>
                            <input class="form-control check-box" type="checkbox" id="status">
                            <p class="help-block error" id="errorMsg_status"></p>
                        </div>

                        <div class="form-group">
                            <label>Is PriceShift</label>
                            <input class="form-control check-box" type="checkbox" id="isPriceShift">
                            <p class="help-block error" id="errorMsg_isPriceShift"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button class="btn btn-primary" onclick="return submitFilm()">SAVE</button>

                    </div>
                    <div class="col-lg-7">
                        <div class="row clearfix">
                            <label style="padding-left: 15px;font-size: 16px;">Choose Image</label>
                            <div class="col-md-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="">
                                        Banner
                                    </label>
                                </div>
                                <div action="/file-upload" class="dropzone">
                                    <div class="fallback">
                                        <input name="file" type="file" multiple/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value="">
                                        Other
                                    </label>
                                </div>
                                <div action="/file-upload" class="dropzone">
                                    <div class="fallback">
                                        <input name="file" type="file" multiple/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- dropzone JavaScript -->

<script src="<c:url value="/admin-resources/dropzone/dropzone.js"/>"></script>

<script>
    function submitFilm() {

        $("#statusMsg").html("").hide();

        var name = $("#name").val();
        var distributorId = $("#distributorId").val();
        var duration = $("#duration").val();
        var rating = $("#rating").val();
        var status = $("#status").prop("checked");
        var isPriceShift = $("#isPriceShift").prop("checked");
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();

        enableDisableFormElement("createFilmForm", ["input", "button", "select"], false);

        var postData = {
            name: name,
            distributorId: distributorId,
            duration: duration,
            rating: rating,
            isPriceShift: isPriceShift,
            status: status,
        };
        if(startDate)
            postData['startDate'] = startDate;
        if(endDate)
                postData['endDate'] = endDate;



        $.ajax({
            url: BASEURL + 'api/admin/film/create',
            type: 'POST',
            data: postData,
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                    enableDisableFormElement("createFilmForm", ["input", "button", "select"], true);
                },
                422: function (response) {
                    console.log(response);
                    enableDisableFormElement("createFilmForm", ["input", "button", "select"], true);
                    BindErrorsWithHtml("errorMsg_", response.responseJSON);
                }
            }, success: function (data) {

                $("#statusMsg").html("Film created successfully").show();
                setTimeout(function () {
                    window.location = BASEURL + "admin/film/all";
                }, 2000);
            }
        });
        return false;
    }
</script>
</body>

</html>


