/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DbContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Account> getAll() {
        ArrayList<Account> ds = new ArrayList<>();
        String sql = "select * from Account";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(new Account(rs.getString(1), rs.getString(2)));
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return ds;
    }

    public boolean Regist(Account newAcc) {
        String sql = "insert into account values (?,?)";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, newAcc.getUserName());
            ps.setString(2, newAcc.getPassWord());
            int kq = ps.executeUpdate();
            if (kq > 0) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }

    public boolean checkLogin(String username, String password) {
        String sql = "select * from account where username=? and password=?";
        conn = DbContext.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Loi:" + ex.toString());
        }
        return false;
    }

    public static void main(String[] args) {

        AccountDAO ac = new AccountDAO();
//        ArrayList<Account> ds = ac.getAll();
//
//        System.out.println("Danh sach tai khoan hien co");
//        for (Account acc : ds) {
//            System.out.println(acc);
//        }
//
//        Account newAcc = new Account("kienvip1", "kienvip1");
//        System.out.println(ac.Regist(newAcc));
//        ac.getAll();
//        System.out.println("Insert new account");
//        ac.Regist(newAcc);
//        for (Account acc : ds) {
//            System.out.println(acc);
//        }
        System.out.println(
        ac.checkLogin("kienvip1", "kienvip1")
        );
        
        
        
    }

}
