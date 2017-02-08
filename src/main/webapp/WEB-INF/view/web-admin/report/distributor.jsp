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
                    <h1 class="page-header">EDI REPORTS</h1>
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
                            <input type='text' class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text" />

                        </div>
                    </div>
                </div>
                <div class="col-md-6" style="margin-top: 25px;">
                    <button type="" class="btn btn-primary" >Print</button>
                    <button type="" class="btn btn-primary" >Export</button>
                    <button type="" class="btn btn-primary" >Email</button>
                    <button type="" class="btn btn-success" >Save</button>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Start Date</label>
                        <div class='input-group date'>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            <input type='text' class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text" />

                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <label>End Date</label>
                    <div class='input-group date'>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        <input type='text' class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text" />

                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h4 class="title">
                                <span>Distributor report</span>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <label>Printed By: Operator name</label><br>
                                    <label>Date printed: now</label><br>
                                    <label>Time printed: now</label><br>
                                    <label>Start date: 12-10-2017</label><br>
                                    <label>End date: 12-10-2017</label>
                                </div>
                                <div class="col-md-6">
                                    Seq Digital
                                </div>
                            </div>
                            <hr>
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>Film Name</th>
                                    <th>Distributor</th>
                                    <th>No of Shows</th>
                                    <th>Gross</th>
                                    <th>Net</th>
                                    <th>Distributor %</th>
                                    <th>Amount Due</th>
                                    <th>Status</th>
                                </tr>
                                </thead>
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
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
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
        })
    })
</script>

<!-- Date picker -->
</body>


</html>


