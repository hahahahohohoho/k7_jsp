<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model1.board.BoardDTO" %>
<%@ page import="model1.board.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
BoardDAO dao = new BoardDAO(application);

Map<String, Object> param = new HashMap<>();
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if(searchWord != null){
	param.put("searchField", searchField);
	param.put("searchWord", searchWord);
}

int totalCount = dao.selectCount(param);
List<BoardDTO> boardLists = dao.selectList(param);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
	<h2>목록보기(List)</h2>

	<!-- 검색 폼 -->
	<form method = "get">
		<table border = "1" width="90%">
		<tr>
			<td align="center">
				<select name="searchField">
					<option value="title">제목</option>
					<option value="content">내용</option>
				</select>
				<input type="text" name = "esarchWord"/>
				<input type="submit" value="검색하기" />
			</td>
		</tr>
		</table>
	</form>

	<!-- 게시물 목록 테이블 -->
	<table border ="1" width="90%">
		<tr>
			<td width="10%">번호</td>
			<td width="50%">제목</td>
			<td width="15%">작성자</td>
			<td width="10%">조회수</td>
			<td width="15%">작성일</td>
		</tr>
	<%
	if(boardLists.isEmpty()){
	%>
		<tr>
			<td colspan="5" align="center">
			등록된 게시물이 없습니다.
			</td>
		</tr>
	<%
	}
	else{
		int virualNum = 0;
		for(BoardDTO dto : boardLists){
			virualNum = totalCount--;
	%>
		<tr align = "center">
			<td><%= virualNum %></td>
			<td align="left">
				<a href="Vuew.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a>
			</td>
			<td align="center"><%= dto.getId() %></td>
			<td align="center"><%= dto.getVisitcount() %></td>
			<td align="center"><%= dto.getPostdate() %></td>
		</tr>
	<%
		}
	}
	%>
	</table>
	<!-- 목록하단 글쓰기 버튼 -->
</body>
</html>