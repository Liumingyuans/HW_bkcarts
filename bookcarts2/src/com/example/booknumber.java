package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class booknumber
 */
@WebServlet("/booknumber")
public class booknumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(" <tr>");
		out.print(" <th>"+"<b>"+"購買的書本"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"數量"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"金額"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"Live It"+"</b>"+"</th>");
		out.print(" </tr>");
		String[] books = (String[]) request.getSession().getAttribute("books");
		String[] numbers = (String[]) request.getSession().getAttribute("numbers");
		String[] prices = (String[]) request.getSession().getAttribute("prices");
		for(int x=0;x<books.length;x++){
			out.print("<tr>");
			out.print("<td>" +  books[x]+"</td>");
			out.print("<td> " + numbers[x]+"</td>");
			out.print("<td> " + prices[x]+"</td>");
			out.print("<td> ");
			out.print("<img src=\"images/"+books[x]+".jpg\" width=\"90px\">");
			out.print("</td> ");
			out.print("</tr>");
		}	
		out.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
