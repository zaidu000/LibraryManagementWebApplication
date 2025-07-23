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
public class AddBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String parking_slot = request.getParameter("parking_slot");
        
        try(Connection con = DBConnection.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("insert into books(name,author,edition,quantity,parking_slot)");
            ps.setString(1,name);
            ps.setString(2,author);
            ps.setString(3,edition);
            ps.setInt(4,quantity);
            ps.setString(5,parking_slot);
            ps.executeUpdate();
            response.sendRedirect("ViewBooks.jsp");
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            out.println(e);
        }
        
    }
}
