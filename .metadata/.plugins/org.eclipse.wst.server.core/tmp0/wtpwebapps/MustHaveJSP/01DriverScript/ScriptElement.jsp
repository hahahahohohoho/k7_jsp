<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
public int add(int num1, int num2){
	return num1 + num2;
}
public int subtraction(int num1, int num2){
	return num1-num2;
}
public int multiple(int num1, int num2){
	return num1*num2;
}
public float division(int num1, int num2){
	return num1/num2;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int result1; int result2;
try{
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	
	result1 = add(num1,num2);	
	result2 = multiple(num1,num2);	
} catch(Exception e){
	result1 = 0;
	result2 = 0;
} 
%>
덧셈결과1 : <%= result1 %><br />
나눗셈결과1 : <%= result2 %><br />
</body>
</html>