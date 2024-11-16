/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
public class ChangePassServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        AccountDAO adao = new AccountDAO();

        String username = session.getAttribute("username").toString();
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String confirm = request.getParameter("newPassConfirm");

        Account acc = new Account(username, oldPass);

        if (adao.checkLogin(username, oldPass)) {
            if (!newPass.trim().equals(confirm)) {
                request.setAttribute("error", "Đổi mật khẩu thất bại, mật khẩu xác nhận không trùng khớp!");
                request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            }
            else if (newPass.trim().equals("") || confirm.trim().equals("")) {
                request.setAttribute("error", "Đổi mật khẩu thất bại, mật khẩu không được để trống!");
                request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
            }
            else {
                adao.ChangePass(acc, newPass);
                request.setAttribute("success", "Đổi mật khẩu thành công!");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Đổi mật khẩu thất bại, mật khẩu cũ không hợp lệ!");
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
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
