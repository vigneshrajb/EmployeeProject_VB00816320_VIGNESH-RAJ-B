package com.vignesh.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/addServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	PreparedStatement statement;
       
public void init()
{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","1234");
		statement = connection.prepareStatement("insert into emp values(?,?,?,?)");
		System.out.println(connection);
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name  = request.getParameter("name");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String designation = request.getParameter("designation");
		
		try  {
					statement.setInt(1, id);
					statement.setString(2, name);
					statement.setInt(3, salary);
					statement.setString(4, designation);
					
					statement.executeUpdate();
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.print("<b>USER CREATED</b><br><br>");
					out.print("<a href=home.html>HOME</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
	}
public void destroy() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
