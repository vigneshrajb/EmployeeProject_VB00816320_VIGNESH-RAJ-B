package com.vignesh.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/deleteServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","1234");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
       
  

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("delete from emp  WHERE id='"+id+"'");
			PrintWriter out = response.getWriter();
			if(result>0) {
			out.print("<H1>USER DELETED</H1><br><br>");
			out.print("<a href=home.html>HOME</a>");
			}
			else {
				out.print("<H1>INVALID USER</H1><br><br>");
				out.print("<a href=home.html>HOME</a>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
