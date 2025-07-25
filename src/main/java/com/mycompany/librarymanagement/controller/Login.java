/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import com.mycompany.librarymanagement.util.PasswordHash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Webkorps
 */
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String membershipNo = request.getParameter("membershipNo");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        try (Connection con = DBConnection.getConnection()) {
            String hashPassword = PasswordHash.hashPassword(password);
            String table = "admin".equals(role) ? "admins" : "students";
            String query = "select * from " + table + " where membership_number=? and password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, membershipNo);
            ps.setString(2, hashPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("memebershipNo", membershipNo);
                session.setAttribute("role", role);
                session.setAttribute("name", rs.getString("name"));
                if ("admin".equals(role)) {
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    response.sendRedirect("studentDashboard.jsp");
                }
            } else {
                request.setAttribute("error", "Login failed");
                out.println("Login Failed");
                //request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
           // request.getRequestDispatcher("login.jsp").forward(request, response);
            out.println(e);
        }

    }

}
