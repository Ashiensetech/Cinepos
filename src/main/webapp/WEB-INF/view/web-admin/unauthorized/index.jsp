<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 1/2/17
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/json" language="java"  isErrorPage="true"  %>
<%
   // Set error code and reason.
   response.sendError(407, "Need authentication!!!" );
%>