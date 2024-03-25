package P1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class LoginUser
 */
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String email = request.getParameter("Email");
		String password  = request.getParameter("Password");
		try {
			//Load Drvier
			Class.forName("com.mysql.cj.jdbc.Driver");
			//pw.println("Driver loaded");
			//Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker","root","tiger");
			//pw.println("Connection Established");
			//Create statement
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from customer_details where mailid='"+email+"' and password='"+password+"'");
			if(rs.next()) {
//				//pw.println("Login Successful");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("userpagejsp.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("email",email);
				response.sendRedirect("userpagejsp.jsp");
//				dispatcher.forward(request, response);
			}else {
				//pw.println("Invalid credentials");
				response.sendRedirect("Home.html");
			}
			
			pw.println("Registered");
			
		}
		catch(Exception e) {
			pw.println("Error Occured");
		}
	}

}
