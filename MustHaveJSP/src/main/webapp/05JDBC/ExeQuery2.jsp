<%@page import="java.awt.Taskbar.State"%>
<%@page import="java.sql.Date"%>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "common.JDBConnect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String sel = request.getParameter("sel");
	JDBConnect jdbc = new JDBConnect();
	String sql = "select num, title, content, id, postdate, visitcount from board where id = '" + sel + "'";
	Statement stmt = jdbc.getCon().createStatement();

	ResultSet rs = stmt.executeQuery(sql);
	%>
	<table>
		<tr style =text-align:center>
			<th>num</th>
			<th>tilte</th>
			<th>content</th>
			<th>name</th>
			<th>postdate</th>
			<th>visitcount</th>
		</tr>
		<%
		while(rs.next()){
			int num = rs.getInt(1);
			String title = rs.getString(2);
			String content = rs.getString(3);
			String name = rs.getString(4);
			String postdate = rs.getString(5);
			String visitcount= rs.getString(6);
			out.println("<tr style =text-align:center>");
			out.println("<td>" + num + "</td>");
			out.println("<td>" + title + "</td>");
			out.println("<td>" + content + "</td>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + postdate + "</td>");
			out.println("<td>" + visitcount + "</td>");
			out.println("</tr>");
		}
		%>
	</table>
</body>
</html>