<%-- 
    Document   : list_product
    Created on : Oct 22, 2024, 2:11:45 PM
    Author     : ADMIN
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../shared/header.jsp" />

<jsp:include page="../shared/nav.jsp" />


<div class="container">
    <h2> Danh sách sản phẩm</h2>
    <div class="mb-2 text-end">
        <a href="QuanTriSanPham?action=ADD" class="btn btn-success"> <i class="bi bi-plus-circle"></i> Thêm mới</a>
    </div>

    <table class="table table-bordered table-striped">
        <tr>
            <th>Tên hoa</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Loại</th>
            <th>Action</th>
        </tr>   
        <%
            DecimalFormat df = new DecimalFormat("#,##0");
            ArrayList<Hoa> dsHoa = (ArrayList<Hoa>) request.getAttribute("dsHoa");
            for (Hoa h : dsHoa) {
        %>
        <tr>
            <td><%=h.getTenhoa()%></td>
            <td><%=df.format(h.getGia())%></td>
            <td> <img src="assets/images/products/<%=h.getHinh()%>" style="width: 100px"> </td>
            <td><%=h.getMaloai()%></td>
            <td>
                <a href="QuanTriSanPham?action=EDIT&mahoa=<%=h.getMahoa()%>" class="btn btn-secondary"> <i class="bi bi-pencil-square"></i> Sửa</a>
                <a href="QuanTriSanPham?action=DELETE&mahoa=<%=h.getMahoa()%>" class="btn btn-danger"> <i class="bi bi-trash"></i> Xoá</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>

<%
    if (request.getAttribute("success") != null) {
%>
<script>

    Swal.fire({
        title: "Good job!",
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
        title: "Good job!",
        text: "<%=request.getAttribute("failed")%>",
        icon: "error"
    });

</script> <% }%>

<jsp:include page="../shared/footer.jsp" />