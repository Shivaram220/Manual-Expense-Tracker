package P1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			int id1 = Integer.parseInt(request.getParameter("unid"));
			st.executeUpdate("delete from expensetable where unid="+id1+"");
			response.sendRedirect("userpagejsp.jsp");
			
		}
		catch(Exception e) {
			//pw.println(e);
			//pw.println("Error Occured");
		}
	}

}
