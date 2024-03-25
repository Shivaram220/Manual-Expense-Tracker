package P1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
/**
 * Servlet implementation class AddExpenses
 */
public class AddExpenses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpenses() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		String purpose = request.getParameter("purpose");
		String paymenttype = request.getParameter("paymenttype");
		float cost = Float.parseFloat(request.getParameter("cost"));
		String date1 = request.getParameter("date");
		String email = request.getParameter("email");
//		Home hm = new Home();
		try {
			//Load Drvier
			Class.forName("com.mysql.cj.jdbc.Driver");
			//pw.println("Driver loaded");
			//Establishing connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker","root","tiger");
			
			//pw.println("Connection Established");
			//Create statement
			Statement st = con.createStatement();
//			pw.println(email);
			ResultSet rs = st.executeQuery("select * from expensetable");
			int sum = 0;
			while(rs.next()) {
				sum+=rs.getInt(6);
			}
			
			if(email!=null) {
				st.executeUpdate("insert into expensetable values('"+email+"','"+purpose+"','"+paymenttype+"',"+cost+",'"+date1+"',"+(sum+1)+")");
				pw.println("<tr>"+
				"<td>"+email+"</td"+
				"<td>"+purpose+"</td"+
				"<td>"+paymenttype+"</td"+
				"<td>"+cost+"</td"+
				"<td>"+date1+"</td>"+"</tr>");
				response.sendRedirect("userpagejsp.jsp");
				pw.println("data inserted");
			}else {
				response.sendRedirect("userpagehtml.html");
			}
			
		}
		catch(Exception e) {
			pw.println(Home.st+" "+Home.con);
			pw.println(e);
			pw.println("Error Occured");
		}
	}

}
