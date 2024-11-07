<%-- 
    Document   : register
    Created on : Nov 7, 2024, 8:14:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="shared/header.jsp" />
<jsp:include page="shared/nav.jsp" />
<!DOCTYPE html>

<div class="container">
    <form action="RegisterServlet" method="post">
        <div>
            <label>Username</label>
            <input type="text" name="username" value="" class="form-control" />
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" value="" class="form-control" />
        </div>
        <div>
            <label>Confirm password</label>
            <input type="password" name="passwordConfirm" value="" class="form-control" />
        </div>
        <div class="mt-2 mb-2">
            <button type="submit" class="btn btn-primary">Register</button>
        </div>

        <%
            if (request.getAttribute("error") != null) {
        %>
        <div class="text-danger mb-3">
            <%=request.getAttribute("error")%>
        </div>
        <%
            }
        %>

    </form>
</div>

