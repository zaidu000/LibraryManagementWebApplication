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

public class IssueBooks extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String membership_number = request.getParameter("membership_number");
        try(Connection con = DBConnection.getConnecton())
        {
         PreparedStatement ps = con.prepareStatement("insert into issued_book(id,membership_number)");
         ps.setInt(1,id);
         ps.setString(2,membership_number);
         ps.executeUpdate();
         response.sendRedirect("ViewBook.jsp");
        
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            out.println(e);
        }
        
    }
}
