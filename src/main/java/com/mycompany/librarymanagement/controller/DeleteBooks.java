/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Webkorps
 */
public class DeleteBooks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from books where id = ? ");
            ps.setInt(1, id);
            ps.executeUpdate();
            response.sendRedirect("ViewBooks");
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }

    }
}
