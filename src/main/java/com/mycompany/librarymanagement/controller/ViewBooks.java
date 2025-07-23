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
        List<Book> books = new ArrayList<>();
        try(Connection con = DBConnection.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from books");
            while(rs.next())
            {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setName(rs.getString("name"));
                book.setQuantity(rs.getString("quantity"));
                book.setParking_slot(rs.getString("parking_slot"));
            }
            request.setAttribute("books",books);
            request.getRequestDispatcher("ViewBooks.jsp").forward(request,response);
           
        }catch(Exception e){
            PrintWriter out = response.getWriter();
            out.println(e);
        }
    }

}
