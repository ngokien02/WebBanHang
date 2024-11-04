/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HoaDAO;
import dao.LoaiDAO;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Hoa;

/**
 *
 * @author Admin
 */
@MultipartConfig
@WebServlet(name = "ProductManage", urlPatterns = {"/QuanTriSanPham"})
public class ProductManage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        if(session.getAttribute("username")==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        HoaDAO hoaDao = new HoaDAO();
        LoaiDAO loaiDao = new LoaiDAO();

        String action = "LIST";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        String method = request.getMethod();
        switch (action) {
            case "LIST":
                int pageSize = 5;
                int pageIndex = 1;
                if(request.getParameter("page")!=null){
                    pageIndex = Integer.parseInt(request.getParameter("page"));
                }
                
                int pageCount = (int) Math.ceil((double)hoaDao.getAll().size() / pageSize);
                
                request.setAttribute("dsHoa", hoaDao.getByPage(pageIndex, pageSize));
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("pageIndex", pageIndex);
                
                request.getRequestDispatcher("admin/listProduct.jsp").forward(request, response);
                break;
            case "ADD":
                if (method.equalsIgnoreCase("get")) {
                    request.setAttribute("dsLoai", loaiDao.getAll());
                    request.getRequestDispatcher("admin/addProduct.jsp").forward(request, response);
                } else if (method.equalsIgnoreCase("post")) {
                    String tenHoa = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maLoai = Integer.parseInt(request.getParameter("maloai"));

                    String realPath = request.getServletContext().getRealPath("/assets/images/products");
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    part.write(realPath + "/" + fileName);

                    Hoa objInsert = new Hoa(0, tenHoa, gia, fileName, maLoai, new Date(new java.util.Date().getTime()));
                    if (hoaDao.Insert(objInsert)) {
                        request.setAttribute("success", "Thêm sản phẩm thành công");
                    } else {
                        request.setAttribute("failed", "Thêm sản phẩm thất bại!");
                    }
                    request.getRequestDispatcher("QuanTriSanPham?action=LIST").forward(request, response);
                }
                break;
            case "EDIT":
                if (method.equalsIgnoreCase("get")) {
                    int maHoa = Integer.parseInt(request.getParameter("mahoa"));
                    request.setAttribute("hoa", hoaDao.getById(maHoa));
                    request.setAttribute("dsLoai", loaiDao.getAll());
                    request.getRequestDispatcher("admin/editProduct.jsp").forward(request, response);
                } 
                else if (method.equalsIgnoreCase("post")) {
                    int maHoa = Integer.parseInt(request.getParameter("mahoa"));
                    String tenHoa = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maLoai = Integer.parseInt(request.getParameter("maloai"));

                    String fileName = request.getParameter("OldImg");

                    if (part.getSize() > 0) {
                        String realPath = request.getServletContext().getRealPath("/assets/images/products");
                        fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                        part.write(realPath + "/" + fileName);
                    }

                    Hoa objUpdate = new Hoa(maHoa, tenHoa, gia, fileName, maLoai, new Date(new java.util.Date().getTime()));

                    if (hoaDao.Update(objUpdate)) {
                        request.setAttribute("success", "Cập nhật sản phẩm thành công!");
                    } 
                    else {
                        request.setAttribute("failed", "Cập nhật sản phẩm thất bại!");
                    }
                    request.getRequestDispatcher("QuanTriSanPham?action=LIST").forward(request, response);
                }
                request.getRequestDispatcher("admin/editProduct.jsp").forward(request, response);
                break;
            case "DELETE":
                int maHoa = Integer.parseInt(request.getParameter("mahoa"));

                if (hoaDao.Delete(maHoa)) {
                    request.setAttribute("success", "Xóa sản phẩm thành công!");
                } else {
                    request.setAttribute("failed", "Xóa sản phẩm thất bại!");
                }
                request.getRequestDispatcher("QuanTriSanPham?action=LIST").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
