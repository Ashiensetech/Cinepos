<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
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
                                        <td>${terminalValue.ip_address}</td>
                                        <td>${terminalValue.type}</td>
                                        <td id="statusTd${terminalValue.id}">${(terminalValue.status==1)?"Active":"Deactive"}</td>
                                        <td>
                                            <button id="statusChangeBtn${terminalValue.id}"
                                                    data-status="${terminalValue.status}"
                                                    onclick="statusUpdateDistributorData('terminalRow${terminalValue.id}',
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
                                            <a href="<c:message code="base.uri" />/admin/terminal/edit/${terminalValue.id}"
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

    <!-- Date picker -->
</body>

</html>


