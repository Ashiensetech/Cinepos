<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<c:url value="/admin-resources/jquery/jquery.min.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/admin-resources/bootstrap/js/bootstrap.min.js"/>"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/admin-resources/metisMenu/metisMenu.min.js"/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/admin-resources//dist/js/sb-admin-2.js"/>"></script>

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
    });


    function doLogout(){

        $.ajax({
            url: '/auth/admin/do-logout',
            type: 'GET',
            data: {
                userName: userName,
                password: password
            },statusCode: {
                401: function (response) {
                    console.log("unauthorized");
                    console.log(response);
                }
            },
            success: function (data) {
                console.log("SUCCESS");
                console.log(data);
                window.location = "/admin/user/create";
            }
        });
        return false;
    }

    var BASEURL = "<spring:message code="base.uri" />";
</script>

