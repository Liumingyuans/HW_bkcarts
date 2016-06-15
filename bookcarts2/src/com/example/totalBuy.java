package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;


/**
 * Servlet implementation class totalBuy
 */
@WebServlet("/totalBuy")
public class totalBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");  //中文
		PrintWriter out = response.getWriter();
		String[] books = (String[]) request.getSession().getAttribute("books");
		String[] numbers = (String[]) request.getSession().getAttribute("numbers");
		String[] prices = (String[]) request.getSession().getAttribute("prices");
		out.print(" <tr>");
		out.print(" <th>"+"<b>"+"書名"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"價錢"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"作者"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"購買數量"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"金額"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"Live It"+"</b>"+"</th>");
		out.print(" </tr>");
		for(int x=0;x<books.length;x++){
		try {     
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://localhost:1433;databasename=javasql";
			Connection conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			String a = "SELECT * FROM book where bkname=\'%s\';";
			String b = String.format(a, books[x]);
			String qryStmt = b;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(qryStmt);
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>" + rs.getString("bkname")+"</td>");
				out.print("<td>" + rs.getInt("bkprice")+"</td>");
				out.print("<td>" + rs.getString("bkwriter")+"</td>");
				out.print("<td>" + numbers[x] +"</td>");
				out.print("<td>" + prices[x] +"</td>");
				out.print("<td> ");
				out.print("<img src=\"images/"+books[x]+".jpg\" width=\"90px\">");
				out.print("</td> ");
				out.print("</tr>");
			}		
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//String number = request.getParameter("number");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		/**
		 使用者的資訊:姓名、信件、電話、地址
		 */
		String name = request.getParameter("uesrName");
		String mail = request.getParameter("uesrMail");
		String tel = request.getParameter("userTel");
		String address = request.getParameter("address");
		request.getSession().setAttribute("userName", name);
		request.getSession().setAttribute("userMail", mail);
		request.getSession().setAttribute("userTel", tel);
		request.getSession().setAttribute("address", address);
		response.sendRedirect("totalMoney.html");
	}

}
