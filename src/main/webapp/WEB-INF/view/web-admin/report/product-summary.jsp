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
                    <h1 class="page-header">Product Summary</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row clearfix">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Date</label>
                        <div class='input-group date'>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                    <input type='text' class="form-control" value="<fmt:formatDate value="${fDate}" pattern="yyyy-MM-dd" />"
                                            id="fixed_date"  placeholder="YYYY-MM-DD" type="text" />
                        </div>
                        <p class="help-block error" id="errorMsg_fixed_date"></p>
                    </div>
                    <button class="btn-primary btn" id="btn_fixed">Search</button>
                </div>
                <div class="col-md-6" style="margin-top: 25px;">
                    <button type="" class="btn btn-primary" id="btnPrint">Print</button>
                    <button type="" class="btn btn-primary" id="btnExport">Export</button>
                   <%-- <button type="" class="btn btn-primary" >Email</button>
                    <button type="" class="btn btn-success" >Save</button>--%>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Start Date</label>
                        <div class='input-group date'>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            <input type='text' class="form-control" id="start_date" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd" />"  placeholder="YYYY-MM-DD" type="text" />

                        </div>
                        <p class="help-block error" id="errorMsg_start_date"></p>
                    </div>
                </div>
                <div class="col-md-6">
                    <label>End Date</label>
                    <div class='input-group date'>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        <input type='text' class="form-control" id="end_date" value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd" />"  placeholder="YYYY-MM-DD" type="text" />
                    </div>
                    <p class="help-block error" id="errorMsg_end_date"></p>
                </div>

                <button class="btn-primary btn" id="btn_search">Search</button>

            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h4 class="title">
                                <span>Circuit name</span>
                                <span class="pull-right">EDI Report</span>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <label>Printed By: ${printed_by}</label><br>
                                    <label>Date printed: ${printingDate}</label><br>
                                    <label>Time printed: ${printedTime}</label><br>
                                    <label>Start date: <fmt:formatDate  value="${startDate}" pattern="yyyy-MM-dd" /><fmt:formatDate  value="${fDate}" pattern="yyyy-MM-dd" /></label><br>
                                    <label>End date:<fmt:formatDate  value="${endDate}" pattern="yyyy-MM-dd" /><fmt:formatDate  value="${fDate}" pattern="yyyy-MM-dd" /></label>
                                </div>
                                <div class="col-md-6">
                                    Seq Digital
                                </div>
                            </div>
                            <hr>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>Products</th>
                                    <th>Price</th>
                                    <th>Sale Unit</th>
                                    <th>Sale Value</th>
                                    <th>Date</th>
                                </tr>
                                </thead>
                                <tbody>
                                <d:choose>
                                    <d:when test="${not empty ProductSummaryReportView}">
                                        <d:forEach var="ProductSummaryReportViewValue" items="${ProductSummaryReportView}">
                                            <d:set var="count" value="${count+1}" />
                                            <tr class="odd gradeC">
                                                <td>${ProductSummaryReportViewValue.productName}</td>
                                                <td>${ProductSummaryReportViewValue.salePrice}</td>
                                                <td>${ProductSummaryReportViewValue.saleUnit}</td>
                                                <td>${ProductSummaryReportViewValue.saleValue}</td>
                                                <td>${ProductSummaryReportViewValue.createdAt}</td>
                                            </tr>
                                        </d:forEach>
                                    </d:when>
                                </d:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>

<jsp:directive.include file="../layouts/footer.jsp" />

<!-- Date picker -->

<!-- Custom Theme JavaScript -->


<!-- Include Date Range Picker -->
<script src="<c:url value="/admin-resources/bootstrap/js/bootstrap-datepicker.1.4.1.min.js"/>"></script>

<script>
    $(document).ready(function(){

        var date_input=$('#fixed_date,#start_date,#end_date'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
        });
    });


        $("#btn_fixed").click(function () {
            var fixed_date=$("#fixed_date").val();

            if(fixed_date==""){
                $("#errorMsg_fixed_date").text("Date is requred!");
                return false;
            }
            //window.location = BASEURL + "report/product-summary?startDate="+fixed_date;
            window.location = BASEURL+"admin/report/product-summary?startDate="+fixed_date;
        });

    $("#btn_search").click(function () {

        var start_date=$("#start_date").val();
        var end_date=$("#end_date").val();

        if(start_date==""){
            $("#errorMsg_start_date").text("Start date is requred!");
            return false;
        }

        if(end_date==""){
            $("#errorMsg_end_date").text("End date is requred!");
            return false;
        }

        //window.location = BASEURL + "report/product-summary?startDate="+fixed_date;
        window.location = BASEURL+"admin/report/product-summary?startDate="+start_date+"&endDate="+end_date;


    });


    $("#btnPrint,#btnExport").click(function () {

        var start_date=$("#start_date").val();
        var end_date=$("#end_date").val();
        var fixed_date=$("#fixed_date").val();
        var pdfUrl="";

        if(start_date!="" && end_date!=""){
            pdfUrl = "admin/report-pdf/product-sale-summary/download?startDate="+start_date+"&endDate="+end_date;

        }else if(fixed_date!=""){
            pdfUrl = "admin/report-pdf/product-sale-summary/download?startDate="+fixed_date;

        }else{
            pdfUrl = "admin/report-pdf/product-sale-summary/download";
        }

        window.open(BASEURL+pdfUrl,'_blank');

    });



</script>

<!-- Include Date Range Picker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        });



    }) ;
</script>

<!-- Date picker -->
</body>


</html>


