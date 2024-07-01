<%@page import="org.eclipse.jdt.internal.compiler.parser.ParserBasicInformation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int col=3;
try{
	col = Integer.parseInt(request.getParameter("col"));
}
catch(Exception e){}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan3</title>
</head>
<body>
	<h2 style="width:<%=col*100 %>px; text-align:center">구구단3</h2>
<div style="display : grid; grid-template-columns : repeat(<%=col%>, 100px)">
<%
for(int i = 2; i<=9;i+=col){
	for(int k=0; k<=9; k++) {
		for(int j=0; j<col; j++) {
%>
	<div>
<%
	 		if(9<i+j)
	 			out.print("");
	 		else{
				if(k==0)
					out.print("<h3>" + (i+j) + "단" + "</h3>");
				else
	 				out.print(String.format("%d*%d=%d", i+j, k, (i+j)*k));
	 		}
%>
	</div>
<%
		 	}
		}
	}
%>
</div>
</body>
</html>