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
import java.sql.ResultSet;
import java.sql.Statement;
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
public class ViewBooks extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Book book=null;
        List<Book> books = new ArrayList<>();
        try(Connection con = DBConnection.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from books");
            while(rs.next())
            {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setName(rs.getString("name"));
                book.setQuantity(rs.getInt("quantity"));
                book.setParking_slot(rs.getString("parking_slot"));
                books.add(book);
            }
            request.setAttribute("books",books);
            request.getRequestDispatcher("viewBooks.jsp").forward(request,response);
           
        }catch(Exception e){
             request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }

}
