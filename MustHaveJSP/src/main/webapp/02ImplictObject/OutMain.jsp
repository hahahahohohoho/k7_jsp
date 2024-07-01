<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 -Out</title>
</head>
<body>
	<%
	//버퍼 내용 삭제하기
	out.print("출력되지 않는 텍스트"); //qjvjdp wjwkd
	out.clearBuffer(); //버퍼를 비움

	out.print("<h2>out 내장 객체</h2>");

	//버퍼 크기정보 확인
	out.print("출력 버퍼의 크기 :" + out.getBufferSize() + "<br />");
	out.print("남은 버퍼의 크기 :" + out.getRemaining() + "<br />");

	out.flush(); //버퍼 내용 출력
	out.print("flush 후 버퍼의 크기 : " +out.getRemaining() + "<br />");

	//다양한 타입의 값 출력
	out.print(1+ "<br />");
	out.print(false+ "<br />");
	out.print('가'+ "<br />");
	%>
</body>
</html>