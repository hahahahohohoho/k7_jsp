<%@ taglib prefix = "c" uri = "jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var = "number" value ="100"/>

	<h4> choose 태그</h4>
	<c:choose>
		<c:when test="${ number mod 2 eq 0 }">
			${ number } 는 짝수입니다.
		</c:when>
		<c:otherwise>
			${ number }는 홀수입니다.
		</c:otherwise>
	</c:choose>

	<h4> 국영수 평균</h4>
	<form>
		국어 : <input type="text" name = "kor"/><br/>
		영어 : <input type="text" name = "eng"/><br/>
		수학 : <input type="text" name = "math"/><br/>
		<input type="submit" value="학점구하기"/>
	</form>

	<c:if test ="${ not (empty param.kor or empty param.eng or empty param.math) }" >
		<c:set var = "avg" value = "${ (param.kor + param.eng + param.math) / 3 }"/>
		평균점수는 ${ avg }으로
		<c:choose>
			<c:when test = "${ avg>=90 }" >A 학점</c:when>
			<c:when test = "${ avg>=80 }" >B 학점</c:when>
			<c:when test = "${ avg>=70 }" >C 학점</c:when>
			<c:when test = "${ avg>=60 }" >D 학점</c:when>
			<c:otherwise> F학점 </c:otherwise>
		</c:choose>
		입니다.
	</c:if>
</body>
</html>