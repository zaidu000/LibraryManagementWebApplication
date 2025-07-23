package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueBooks extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        String membershipNumber = request.getParameter("membership_number");

        // Default due date: 7 days from now
        LocalDate dueDate = LocalDate.now().plusDays(7);

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO issued_books (book_id, membership_number, due_date) VALUES (?, ?, ?)"
            );
            ps.setInt(1, bookId);
            ps.setString(2, membershipNumber);
            ps.setDate(3, java.sql.Date.valueOf(dueDate));
            ps.executeUpdate();

            response.sendRedirect("adm-viewIssuedBooks.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("Error: " + e.getMessage());
        }
    }
}
