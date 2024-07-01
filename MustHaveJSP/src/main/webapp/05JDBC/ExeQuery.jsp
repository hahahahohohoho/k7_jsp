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
<title>JDBC</title>
</head>
<body>
	<h2>회원목록 조회 테스트(executeQuery() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect();

	String sql = "select * from member";
	Statement stmt = jdbc.getCon().createStatement();

	ResultSet rs =stmt.executeQuery(sql);

	while(rs.next()){
		String id = rs.getString(1);
		String pw = rs.getString(2);
		String name = rs.getString("name");
		Date regidate = rs.getDate("regidate");

		out.print(String.format("%s %s %s %s", id, pw, name, regidate) + "<br/>");
	}
	%>
</body>
</html>