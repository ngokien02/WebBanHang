<%-- 
    Document   : edit_product
    Created on : Oct 22, 2024, 2:12:12 PM
    Author     : ADMIN
--%>

<%@page import="model.Loai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Hoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../shared/header.jsp" />

<jsp:include page="../shared/nav.jsp" />

<% 
    Hoa hoa = (Hoa)request.getAttribute("hoa");
    ArrayList<Loai> dsLoai = (ArrayList<Loai>)request.getAttribute("dsLoai");
%>

<div class="container">
    
    <h2>Cập nhật sản phẩm (Hoa)</h2>    
    <form method="post" enctype="multipart/form-data">
        <div class="mb-2">
            <label>Tên hoa</label>
            <input type="text" name="tenhoa" value="<%=hoa.getTenhoa() %>" class="form-control" />
        </div>
        <div class="mb-2">
            <label>Giá</label>
            <input type="number" name="gia" value="<%=hoa.getGia()%>" class="form-control" />
        </div>
        <div class="mb-2">
            <label>Hình ảnh</label>
            <input type="file" name="hinh" value="" class="form-control" />
            <img src="assets/images/products/<%=hoa.getHinh() %>" width="150px">
            <input type="hidden" name="OldImg" value="<%=hoa.getHinh() %>" />
        </div>
         <div class="mb-2">
            <label>Thể loại</label>
            <select name="maloai" class="form-control">      
                <option value="" disabled="">==Chọn thể loại==</option>
                <% 
                    for(Loai l : dsLoai){
                %>
                <option value="<%=l.getMaloai() %>" <%=hoa.getMaloai()==l.getMaloai()? "selected":"" %>><%=l.getTenloai() %></option>
                <% } %>
            </select>
        </div>        
        <button type="submit" class="btn btn-primary">Save</button>
    </form>       
</div>

<jsp:include page="../shared/footer.jsp" />