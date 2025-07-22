/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
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
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String library = request.getParameter("libraryName");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        
        String membershipNo = role.substring(0,3).toUpperCase()+System.currentTimeMillis()+(int)(Math.random()*1000);
        
        try(Connection con = DBConnection.getConnection())
        {
            PreparedStatement ps;
            if("Admin".equals(role))
            {
                ps=con.prepareStatement("insert into admins(membership_number,name, library_name,address,email,password,role) values(?,?,?,?,?,?,?)");
                ps.setString(1,membershipNo);
                ps.setString(2,name);
                ps.setString(3,library);
                ps.setString(4,address);
                ps.setString(5,email);
                ps.setString(6,password);
                ps.setString(7,role);
                
            }else{
                
                ps = con.prepareStatement("insert into students(membership_number,name,email,password,role) values(?,?,?,?,?)");
                ps.setString(1,membershipNo);
                ps.setString(2,name);
                ps.setString(3,email);
                ps.setString(4,password);
                ps.setString(5,role);
           
            }
            int i = ps.executeUpdate();
            if(i>0){
                request.setAttribute("message","Registered successfully with email: "+email);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                request.setAttribute("error","Registration failed");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        
        }catch(Exception e){
                request.setAttribute("error","Error: "+e.getMessage());
                request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
