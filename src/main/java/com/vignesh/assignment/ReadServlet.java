package com.vignesh.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/readServlet")

public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	
	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","1234");
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("select * from emp");
			PrintWriter out = response.getWriter();
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.println("id");
			out.print("</th>");
			out.print("<th>");
			out.print("Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Salary");
			out.print("</th>");
			out.print("<th>");
			out.println("Designation");
			out.print("</th>");
			out.print("</tr>");
			while(set.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.print(set.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.print(set.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.print(set.getString(3));
				out.println("</td>");
				out.println("<td>");
				out.print(set.getString(4));
				out.println("</td>");
				out.println("</tr>");

			}
			out.print("</table><br><br>");
			out.print("<a href=home.html>HOME</a>");

			
			
			
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
