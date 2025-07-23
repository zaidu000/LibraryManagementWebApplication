/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.model.Book;
import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Webkorps
 */
public class SearchBook extends HttpServlet {    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("keyword");
        List<Book> searchResults = new ArrayList<>();
        try(Connection con = DBConnection.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("select * from books where name like ? or author like ?");
            String keyword = "%"+searchTerm+"%";
            ps.setString(1,keyword);
            ps.setString(2,keyword);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setQuantity(rs.getInt("quantity"));
                book.setParking_slot(rs.getString("parking_slot"));
                searchResults.add(book);
            }
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("searchResult.jsp").forward(request, response);
        }catch(Exception e){
             request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
        
    }

}
