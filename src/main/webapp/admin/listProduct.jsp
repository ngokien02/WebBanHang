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

    <%
        int pageCount = (int) request.getAttribute("pageCount");
        int pageIndex = (int) request.getAttribute("pageIndex");
    %>

    <table class="table table-bordered table-striped">
        <tr>
            <th>Tên hoa</th>
            <th>Giá</th>
            <th>Hình ảnh</th>
            <th>Loại</th>
            <th>Action</th>
        </tr>   
        <%
            DecimalFormat df = new DecimalFormat("#,##0 vnđ");
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
        <% }%>
    </table>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item <%=pageIndex == 1 ? "disabled" : ""%>">
                <a class="page-link" href="QuanTriSanPham?page=1">First</a>
            </li>
            <li class="page-item <%=pageIndex == 1 ? "disabled" : ""%>">
                <a class="page-link" href="QuanTriSanPham?page=<%=(pageIndex > 1) ? (pageIndex - 1) : 1%>">Previous</a>
            </li>
            <%
                for (int i = 1; i <= pageCount; i++) {
            %>
            <li class="page-item <%=(i == pageIndex) ? "active" : ""%>">
                <a class="page-link" href="QuanTriSanPham?page=<%=i%>"><%=i%></a>
            </li>
            <% }%>
            <li class="page-item <%=pageIndex == pageCount ? "disabled" : ""%>">
                <a class="page-link" href="QuanTriSanPham?page=<%=(pageIndex < pageCount) ? (pageIndex + 1) : pageIndex%>">Next</a>
            </li>
            <li class="page-item <%=pageIndex == pageCount ? "disabled" : ""%>">
                <a class="page-link" href="QuanTriSanPham?page=<%=pageCount%>">Last</a>
            </li>
        </ul>
    </nav>

</div>

<jsp:include page="../Alert.jsp" />        
<jsp:include page="../shared/footer.jsp" />