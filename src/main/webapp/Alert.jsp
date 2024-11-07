<%-- 
    Document   : Notification
    Created on : Nov 7, 2024, 7:40:38 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if (request.getAttribute("success") != null) {
%>

<script>

    Swal.fire({
        title: "Success!",
        text: "<%=request.getAttribute("success")%>",
        icon: "success"
    });

</script>
<%
    }
%>

<%
    if (request.getAttribute("failed") != null) {
%>
<script>

    Swal.fire({
        title: "Oh no!",
        text: "<%=request.getAttribute("failed")%>",
        icon: "error"
    });

</script> <% } %>
