package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class user
 * 購買者資訊內容
 */
@WebServlet("/user")
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");  //中文
		PrintWriter out = response.getWriter();
		String userName = (String) request.getSession().getAttribute("userName");
		String userMail = (String) request.getSession().getAttribute("userMail");
		String userTel = (String) request.getSession().getAttribute("userTel");
		String address = (String) request.getSession().getAttribute("address");
		out.println("<ul>");
		out.println("<li>Buy_Name:"+userName+"</li>");
		out.println("<li>Buy_Mail:"+userMail+"</li>");
		out.println("<li>Buy_Tel:"+userTel+"</li>");
		out.println("<li>Buy_address:"+address+"</li>");
		out.println("</ul>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
