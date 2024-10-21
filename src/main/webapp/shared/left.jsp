<%-- 
    Document   : Left
    Created on : Sep 28, 2023, 12:08:09 PM
    Author     : KHOACNTT
--%>

<%@page import="model.Loai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.LoaiDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    LoaiDAO loaiDAO = new LoaiDAO();
    ArrayList<Loai> dsLoai = loaiDAO.getAll();
    %>

<div class="card mb-3">
    <h3 class="card-header">Category</h3>  
        <ul class="list-group list-group-flush">
            <%
                for(Loai l : dsLoai){
                %>
                <li class="list-group-item">
                    <a style="text-decoration: none" href="product.jsp?maloai=<%=l.getMaloai() %>">
                        <%=l.getTenloai() %>
                    </a>
                </li>
            <% } %>
        </ul>   
</div>
