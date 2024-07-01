<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - Response</title>
</head>
<body>
<%
String id = request.getParameter("user_id");
String pwd = request.getParameter("user_pwd");
if(id.equalsIgnoreCase("must")&& pwd.equalsIgnoreCase("1234"))
	response.sendRedirect("ResponseWelcome.jsp");
else
	request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
		.forward(request, response);
//sendRedirect는 요청하고 결과를 받고 그  결과에 맞는 웹페이지를 호출한다
// -> 즉, 서버와 브라우저 간의 응답이 한차례 더 필요함
%>
</body>
</html>