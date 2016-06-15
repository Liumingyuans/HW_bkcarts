package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class book1
 */
@WebServlet("/book1")
public class book1 extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * �ϥ�books�o��T�ӭp�����
		 */
		String[] books = (String[]) request.getSession().getAttribute("books");
		String[] numbers =new String[books.length];   //�ѥ��ƶq
		for(int x=0;x<numbers.length;x++){
			int y=x+1;
			numbers[x]=request.getParameter("number_"+y);
		}
		request.getSession().setAttribute("numbers",numbers);
		
		String[] prices =new String[books.length];   //�ѥ����B
		for(int x=0;x<prices.length;x++){
			int y=x+1;
			prices[x]=request.getParameter("total_"+y);
		}
		request.getSession().setAttribute("prices",prices);
		
		String totalMoney = request.getParameter("totalMoney"); //�ѥ�total���B
		request.getSession().setAttribute("totalMoney",totalMoney);
		
		response.sendRedirect("booknumber.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
