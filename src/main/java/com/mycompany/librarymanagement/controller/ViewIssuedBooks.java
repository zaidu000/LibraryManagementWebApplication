/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.librarymanagement.controller;

import com.mycompany.librarymanagement.util.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewIssuedBooks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Map<String,String>> issuedBooks = new ArrayList<>();
        try(Connection con = DBConnection.getConnection())
        {
            String query = "select b.name as BookName,s.name as StudentName, ib.issue_date as IssueDate from issued_book JOIN book ib.book_id = b.id JOIN student ib.membershipNo = s.membershipNo";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
            
                Map<String,String> record = new HashMap<>();
                record.put("bookName",rs.getString("name"));
                record.put("studentName",rs.getString("studentName"));
                record.put("issueDate",rs.getString("issueDate"));
                issuedBooks.add(record);
            }
            request.setAttribute("issuedBooks", issuedBooks);
            request.getRequestDispatcher("viewIssuedBook.jsp").forward(request, response);
                
        }catch(Exception e)
        {
            PrintWriter out = response.getWriter();
            out.println(e);
        }
        
    }
}
