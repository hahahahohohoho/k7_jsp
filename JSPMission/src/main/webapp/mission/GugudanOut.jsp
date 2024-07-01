<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sel = request.getParameter("sel");
	String url; String key;
	if(sel.equals("Type1")){
		url = "gugudan1.jsp";
		key = "dan";
	}
	else if(sel.equals("Type2")){
		url = "gugudan2.jsp";
		key = "val";
	}
	else {
		url = "gugudan3.jsp";
		key = "col";
	}
	response.sendRedirect(url + "?"+ key + "=" + request.getParameter(key));
%>