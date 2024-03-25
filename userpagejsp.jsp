<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*,java.io.*,java.util.*,java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="userpagestyle.css" rel="stylesheet">
<script src="userpagejss.js"></script>
</head>
<body>
    }
    <nav class="nav-bar">
    <h1>Welcome to Shivarams Expense Tracker </h1>
    <div class="logout-btn">
    <form action="Logoutjsp.jsp">
    <input type="submit" value="Logout">
    </form>
    </div>
</nav>
    <%float sum = 0; 
    float sum1 = 0;
    LocalDate curDate = LocalDate.now();
    String strCurDate = curDate.toString();
    HashMap<String,Float> hm = new HashMap<>();%>
    <%
    try {
		//Load Drvier
		Class.forName("com.mysql.cj.jdbc.Driver");
		out.println("Driver loaded");
		//Establishing connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker","root","tiger");
		out.println("Connection Established");
		//Create statement
		Statement st = con.createStatement();
		out.println("Statement created");
		String email = (String)session.getAttribute("email");
		out.println(email);
		ResultSet rs = st.executeQuery("select * from expensetable where mailid='"+email+"'");
		//out.println("Hello");
	%>
	<div class="user-expenses">
        <table>
            <tr class="table-row">
            	<th>Id for Expense</th>
                <th>Purpose</th>
                <th>Description</th>
                <th>Paid through</th>
                <th>Cost</th>
            </tr>
            <% while(rs.next()){%>
		<tr class="table-row">
			<%
			String key1 = rs.getString(5);
			if(key1.equals(strCurDate)){
				sum1+=Float.parseFloat(rs.getString(4));
			}
			Float value1 = Float.parseFloat(rs.getString(4));
			String monthYear = key1.substring(0,7);
			if(hm.containsKey(monthYear)){
				Float value2 = hm.get(monthYear);
				value2+=value1;
				hm.put(monthYear,value2);
			}else{
				hm.put(monthYear,value1);
			}
			%>
			<td><%=rs.getString(6) %></td>
			<td><%=rs.getString(2) %></td>
			<td><%=rs.getString(3)%></td>
			<td><%=rs.getString(4)%></td>
			<td><%=rs.getString(5) %></td>
			<%
			sum+=Float.parseFloat(rs.getString(4));
			%>
		</tr>
	<%} %>

	<%}
	catch(Exception e) {
		out.println("Error Occured");
	}
    %>
    </table>
    <div class="add-expense-details">
            <form action="Third" method="post">
                <input type="text" placeholder="Enter Purpose of Expense" name="purpose"><br>
                <input type="text" placeholder="Through which you paid" name="paymenttype"><br>
                <input type="text" placeholder="cost of payment" name="cost"><br>
                <input type="date" placeholder="Date of Payment" name="date"><br>
                <input type="hidden" name="email" value="<%=session.getAttribute("email")%>">
                <input type="submit" value="submit details">
            </form>
    </div>
    <div class="delete-expense-details">
    <form action="Fifth" method="post">
        <input type="text" placeholder="Enter iD generated" name="unid">
        <input type="submit" value="ClickToDelete" id="deleteExpenseButton">
    </form>
</div>
        
        <div class="calculated-Expense">
    <div class="expense total-expenses">
        <%= "total Expenses = " + sum %>
    </div>
    <div class="expense today-expenses">
         <td><%= "Today Expenses=" + sum1 %></td>
    </div>
     <div class="expense monthly-expenses">
         <%
         Set <String> keys = hm.keySet();
         for(String k:keys){
         %>
         <p><%="Date =   "+k+"Amount =  "+hm.get(k) %></p>
         <% }%>
    </div>
</div>
        <input type="submit" onclick="addExpense()" value="Add Expense" id="addExpenseButton">
        <input type="submit" onclick="deleteExpense()" value="Delete Expense" id="addExpenseButton">
    </div>


</body>
</html>