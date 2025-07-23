package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.model.Book;
import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class GetBookForEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE id = ?");
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setQuantity(rs.getInt("quantity"));
                book.setParking_slot(rs.getString("parking_slot"));

                request.setAttribute("book", book);
                request.getRequestDispatcher("editBook.jsp").forward(request, response);
            } else {
                response.sendRedirect("ViewBooks"); // if not found
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}