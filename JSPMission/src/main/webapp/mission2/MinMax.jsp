<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!

	static int gdcT(int max, int min){
		int rem = max % min;
		if(rem==0){
			return min;
		}else{
			gdcT(min, rem);
		}
		return 0;
	}

	static int lcmT(int max, int min){
		int rem = max % min;
		if(rem==0){
			return max / gdcT(max, min);
		}else{
			lcmT(min, rem);
		}
		return 0;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));

	int gdc = gdcT(num1, num2);
	int lcm = lcmT(num1, num2);

%>
<body>
	<form action="Min">
		<table>
			<tr>
				<td>숫자 1 : </td>
				<td><input type="text" name=num1 value = <%= num1 %> /></td>
			</tr>
			<tr>
				<td>숫자 2 : </td>
				<td><input type="text" name=num2 value = <%= num2 %> /></td>
			</tr>
			<tr>
				<td>최대공약수 :</td>
				<td><input type="text" name=num2 readonly="readonly" value = <%= lcm %> /></td>
			</tr>
			<tr>
				<td>최소공배수:</td>
				<td><input type="text" name=num2 readonly="readonly" value = <%= gdc %> /></td>
			</tr>
			<tr><td colspan="2" style= "margin 0 auto; text-align: center;">
				<input style = "width:100%;"type="submit" value = "실행"/>
			</td></tr>
		</table>
	</form>
</body>
</html>