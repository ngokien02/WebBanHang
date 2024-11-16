<%-- 
    Document   : ChangePass
    Created on : Nov 15, 2024, 7:18:33 AM
    Author     : Admin
--%>
<jsp:include page="shared/header.jsp" />
<jsp:include page="shared/nav.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="container">
        <form action="ChangePassServlet" method="post">
            <div>
                <label>Old password</label>
                <input type="password" name="oldPass" value="" class="form-control" />
            </div>
            <div>
                <label>New password</label>
                <input type="password" name="newPass" value="" class="form-control" />
            </div>
            <div>
                <label>Confirm new password</label>
                <input type="password" name="newPassConfirm" value="" class="form-control" />
            </div>
            <div class="mt-2 mb-2">
                <button type="submit" class="btn btn-primary">Change password</button>
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
</html>
