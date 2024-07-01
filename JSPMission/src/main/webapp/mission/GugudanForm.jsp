<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단 폼</title>
</head>
<body>
	<form action="GugudanOut.jsp" method="post">
	<input type="radio" name="sel" value="Type1"/>Type1<input type="text" name="dan"/>단<br/>
	<input type="radio" name="sel" value="Type2"/>Type2 <input type="radio" name="val" value="hor">horizon <input type="radio" name="val" value="ver">vertical <br/>
	<input type="radio" name="sel" value="Type3"/>Type3 <input type="text" name="col"/>열<br/>
	<input type="submit" value="선택">
	</form>
</body>
</html>