package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import com.mycompany.librarymanagement.util.EmailUtility;
import com.mycompany.librarymanagement.util.PasswordHash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String library = request.getParameter("libraryName");
        String address = request.getParameter("address");
        String hashedPassword = null;

        // Clean membership number: ADM20250001 or STD20250001
        String prefix = role.substring(0, 3).toUpperCase();
        String membershipNo = prefix + System.currentTimeMillis() % 1000000;

        try
        {
            hashedPassword = PasswordHash.hashPassword(password);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps;

            if ("Admin".equalsIgnoreCase(role)) {
                ps = con.prepareStatement("INSERT INTO admins(membership_number, name, library_name, address, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, membershipNo);
                ps.setString(2, name);
                ps.setString(3, library);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.setString(6, hashedPassword);
                ps.setString(7, role);
            } else {
                ps = con.prepareStatement("INSERT INTO students(membership_number, name, email, password, role) VALUES (?, ?, ?, ?, ?)");
                ps.setString(1, membershipNo);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                ps.setString(5, role);
            }

            int i = ps.executeUpdate();

            if (i > 0) {
               
                String subject = "Library Registration successfully";
                String msg = "Hi " + name + ",\n\nYour MembershipNo is: " + membershipNo + "\nPassword: " + password + "\n\nThank You";
                EmailUtility.sendEmail(email, subject, msg);
                request.setAttribute("message", "Registered successfully. Membership No: " + membershipNo);
                request.getRequestDispatcher("login.jsp?role=" + role).forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            request.setAttribute("error", "Error: " + e.getMessage());
            out.println(e);
        }
    }
}
