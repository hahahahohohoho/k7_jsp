<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 1-1</title>
</head>
<body>

<%
String dir = request.getParameter("val");
if (dir == null) {
    dir = "hor";
}
%>
 <%
 	if(dir.equals("hor")){
		 for(int i=1; i<10; i++){
			 for(int j=2; j<10; j++){
				 out.println(j + " * " + i + " = " + (i*j) + "");
			 }
			 out.println("<br />");
		 }
	}else if(dir.equals("ver")){
		 for(int i=2; i<10; i++){
			 for(int j=1; j<10; j++){
		 		out.println(i +" * " + j + " = " + (i*j) + "<br />");
		 	}
	 	}
	}
 %>
</body>
</html>