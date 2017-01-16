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
                                <d:forEach var="distributor" items="${distributors}">
                                    <option value="${distributor.id}">${distributor.name}</option>
                                </d:forEach>
                            </select>
                            <p class="help-block error" id="errorMsg_distributorId"></p>
                        </div>
                        <div class="form-group">
                            <label>Select Film type</label>
                            <select id="genreIds" class="form-control">
                                <d:forEach var="genre" items="${genres}" >
                                    <option value="${genre.id}">${genre.name}</option>
                                </d:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Ratings</label>
                            <input id="rating" class="form-control" type="number" min="0">
                            <p class="help-block error" id="errorMsg_rating"></p>
                        </div>
                        <div class="form-group">
                            <label>Duration Hour</label>
                            <input  id="duration_hour" class="form-control" min="0" type="number" >
                            <p class="help-block error" id="errorMsg_durationHour" ></p>
                            <br>
                            <label>Min</label>
                            <input  id="duration_min" class="form-control"  min="0" type="number" >
                            <p class="help-block error" id="errorMsg_durationMin"></p>
                        </div>
                        <div class="form-group">
                            <label>Start date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="startDate" name="date"
                                       placeholder="MM/DD/YYY" type="text"/>
                            </div>
                            <p class="help-block error" id="errorMsg_startDate"></p>
                        </div>
                        <div class="form-group">
                            <label>End date</label>
                            <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                <input type='text' class="form-control" id="endDate" name="date" placeholder="MM/DD/YYY"
                                       type="text"/>
                            </div>
                            <p class="help-block error" id="errorMsg_endDate"></p>
                        </div>
                        <div class="form-group">
                            <label>Trailer link</label>
                            <input id="trailer" type="text" class="form-control">
                            <p class="help-block error" id="errorMsg_trailer"></p>
                        </div>
                        <div class="form-group">
                            <label>Film view Type</label><br>
                            <d:forEach var="screenDimension" items="${screenDimensions}" >
                                <label class="checkbox-inline">
                                    <input class="screenDimensionChk" value="${screenDimension.id}" type="checkbox">${screenDimension.name}
                                </label>
                            </d:forEach>
                            <p class="help-block error" ></p>
                        </div>

                        <div class="form-group">
                            <label>Is PriceShift</label><br>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="isPriceShift">Yes
                            </label>
                            <p class="help-block error" id="errorMsg_isPriceShift"></p>
                        </div>

                        <br>
                        <p class="help-block" id="statusMsg"></p>
                        <button class="btn btn-primary" onclick="return submitFilm()">SAVE</button>

                    </div>
                    <div class="col-lg-7">
                        <div class="row clearfix">
                            <label style="padding-left: 15px;font-size: 16px;">Choose Banner Image</label>
                            <div class="col-md-12">

                                <div id="bannerImg" >
                                    <%--<div class="fallback">
                                        <input name="filmImage" type="file" multiple />
                                    </div>--%>
                                        <div class="dz-default dz-message">
                                            <span>Add banner image</span>
                                        </div>
                                </div>
                                <p class="help-block error" id="errorMsg_bannerImageToken"></p>
                            </div>
                            <div class="col-md-12">
                                <label style="padding-left: 15px;font-size: 16px;">Choose Other Images</label>
                                <div id="otherImg"  >
                                    <div class="dz-default dz-message">
                                        <span>Add other images</span>
                                    </div>
                                </div>
                                <p class="help-block error" id="errorMsg_otherImagesToken"></p>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
    <%------------------------------------------------------------------%>
    <div class="table table-striped" class="files" id="previews">

        <div id="template" class="file-row">
            <!-- This is used as the file preview template -->
            <div>
                <span class="preview"><img data-dz-thumbnail /></span>
            </div>
            <div>
                <strong class="error text-danger" data-dz-errormessage></strong>
            </div>
        </div>
    </div>
    <%------------------------------------------------------------------------------------%>
</div>

<jsp:directive.include file="../layouts/footer.jsp"/>
<!-- dropzone JavaScript -->

<script src="<c:url value="/admin-resources/dropzone/dropzone.js"/>"></script>

<script>
    Dropzone.autoDiscover = false;
    var bannerImageToken = 0;
    var otherImagesToken = [];

    $(function() {
        var bannerImgDropzone = new Dropzone("div#bannerImg",
                {
                    url: BASEURL+"api/admin/file-upload/film",
                    method:"post",
                    paramName:"filmImage",
                    maxFilesize: 2,
                    maxFiles:1,
                    addRemoveLinks: true,
                    init:function(){
                        $("div#bannerImg").addClass("dropzone");
                    },
                    removedfile:function(file){
                        bannerImageToken = 0;
                        var _ref;
                        if(file.token == undefined){
                            return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
                        }
                        removeImageByToken(file.token);
                        return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;

                    },
                    success:function(file,response){
                        bannerImageToken = response;
                        file.token = response;
                        console.log(response);
                    }
                }
        );

        var otherImgDropzone = new Dropzone("div#otherImg",
                {
                    url: BASEURL+"api/admin/file-upload/film",
                    method:"post",
                    paramName:"filmImage",
                    maxFilesize: 2,
                    maxFiles:5,
                    addRemoveLinks: true,
                    init:function(){
                        $("div#otherImg").addClass("dropzone");
                    },
                    removedfile:function(file){
                        console.log(file);
                        var _ref;
                        if(file.token == undefined){
                            return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
                        }
                        var index = otherImagesToken.indexOf(file.token);
                        if(index>0){
                            otherImagesToken.splice(index,1);
                        }
                        removeImageByToken(file.token);
                        return (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;

                    },
                    success:function(file,response){
                        otherImagesToken.push(response);
                        file.token = response;
                        console.log(response);
                    }
                }
        );

    });


    function removeImageByToken(token){
        $.ajax({
            url: BASEURL + 'api/admin/file-upload/delete/temp-file',
            type: 'POST',
            data: {token:token},
            statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                    showLoginModal();
                },
                422: function (response) {
                    console.log(response);
                    enableDisableFormElement("createFilmForm", ["input", "button", "select"], true);
                    BindErrorsWithHtml("errorMsg_", response.responseJSON);
                }
            }, success: function (data) {
            }
        });
    }


    function submitFilm() {

        $("#statusMsg").html("").hide();

        var name = $("#name").val();
        var distributorId = $("#distributorId").val();
        var rating = $("#rating").val();
        var trailer = $("#trailer").val();
        var isPriceShift = $("#isPriceShift").prop("checked");
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var durationHour = $("#duration_hour").val();
        var durationMin = $("#duration_min").val();
        var screenDimensions = [];
        var genreIds = [parseInt($("#genreIds").val())];

        $("input.screenDimensionChk:checked").each(function(){
            screenDimensions.push(parseInt($(this).val()));
        });
        enableDisableFormElement("createFilmForm", ["input", "button", "select"], false);

        var postData = {
            name: name,
            distributorId: distributorId,
            rating: rating,
            trailer:trailer,
            isPriceShift: isPriceShift,
            otherImagesToken : JSON.stringify(otherImagesToken),
            screenDimensions: JSON.stringify(screenDimensions),
            genreIds : JSON.stringify(genreIds)
        };
        if(bannerImageToken>0){
            postData["bannerImageToken"] = bannerImageToken;
        }
        if(durationHour){
            postData['durationHour'] = durationHour;
        }
        if(durationMin){
            postData['durationMin'] = durationMin;
        }

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


