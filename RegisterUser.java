package P1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;
/**
 * Servlet implementation class RegisterUser
 */
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String firstName = request.getParameter("FirstName");
		String lastName = request.getParameter("LastName");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		int i = 1;
		//Load Drvier
		try {
			//Load Drvier
			Class.forName("com.mysql.cj.jdbc.Driver");
			pw.println("Driver loaded");
			//Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker","root","tiger");
			pw.println("Connection Established");
			//Create statement
			Statement st = con.createStatement();
			st.executeUpdate("insert into customer_details values('"+firstName+"','"+lastName+"','"+email+"','"+password+"')");
			//pw.println("Registered");
			
		}
		catch(Exception e) {
			pw.println("Error Occured");
		}
		
	}

}
