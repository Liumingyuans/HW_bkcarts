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
 * Servlet implementation class sql
 */
@WebServlet("/sql")
public class sql extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2221676361420736958L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");  //中文
		PrintWriter out = response.getWriter();
		String[] books = (String[]) request.getSession().getAttribute("books");
		out.print(" <tr>");
		out.print(" <th>"+"<b>"+"Book Name"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"Book Price"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"First Writer"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"Buy Number"+"</b>"+"</th>");
		out.print(" <th>"+"<b>"+"Buy Money"+"</b>"+"</th>");
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
			int userByID=x+1;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(qryStmt);
			while(rs.next()) {
				out.print("<tr>");
				out.print("<td>" +  rs.getString("bkname")+"</td>");
				out.print("<td> " + rs.getString("bkprice")+"</td>");
				out.print("<td> " + rs.getString("bkwriter")+"</td>");
				out.print("<td> ");
				out.print("<select name=\"number_"+userByID+"\"id=\"number_"+userByID+"\" onchange=\"useJquery()\">");
					out.print("<option value=\"1\">"+1+"</option>");
					out.print("<option value=\"2\">"+2+"</option>");
					out.print("<option value=\"3\">"+3+"</option>");
					out.print("<option value=\"4\">"+4+"</option>");
					out.print("<option value=\"5\">"+5+"</option>");
				out.print("</select>");
				out.print("</td> ");
				out.print("<td> ");
				out.print("<input name=\"total_"+userByID+"\"id=\"total_"+userByID+"\"type=\"text\"    value=\"0\"/>");
				out.print("</td> ");
				out.print("<td> ");
				out.print("<img src=\"images/"+books[x]+".jpg\" width=\"90px\">");
				out.print("</td> ");
				out.print("</tr> ");
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
	 * html <bookhome> type=checkbox name=books的資訊
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");  //中文
		String[] books = request.getParameterValues("books");
		request.getSession().setAttribute("books",books);
		response.sendRedirect("bookcarts.html");
		
	}
}
