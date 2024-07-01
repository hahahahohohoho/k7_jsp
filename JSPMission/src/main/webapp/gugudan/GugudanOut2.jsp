<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>show gugudan</title>
</head>
<body>
	<%
		String sel = request.getParameter("sel");
		String dan = request.getParameter("dan");
		String col = request.getParameter("col");
		String val = request.getParameter("val");
	%>
	<%

		if(sel.equals("Type1")){
			for(int i=1; i<10; i++) {
			 	out.println(dan +" * " + i + " = " + (Integer.parseInt(dan)*i) + "<br />");
			 }
		}
		else if(sel.equals("Type2")){
			if (val.equals("h")){
				for(int i=1; i<10; i++){
					 for(int j=2; j<10; j++){
						 out.println(j + " * " + i + " = " + (i*j) + "");
					 }
					 out.println("<br />");
				 }
			} else{
				for(int i=2; i<10; i++){
					 for(int j=1; j<10; j++){
				 		out.println(i +" * " + j + " = " + (i*j) + "<br />");
				 	}
			 	}
			}
		}
		else {
			for(int k = 0; k<9;k+=Integer.parseInt(col)){
				for(int i=1; i<10; i++) {
					for(int j=2+k; j<=k+Integer.parseInt(col)+1; j++) {
				 		if (j>9) break;
				 		out.println(j +" * " + i + " = " + (i*j) + "&nbsp&nbsp&nbsp");
				 	}
					out.print("<br/>");
				}
			}
		}

	%>
</body>
</html>