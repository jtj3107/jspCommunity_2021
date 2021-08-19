<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.jtj.example.jspCommunity.dto.Article"%>
<%@ page import="com.jtj.example.jspCommunity.dto.Board"%>
<%
Board board = (Board) request.getAttribute("board");
List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=board.getName()%></title>
</head>
<body>
	<h1><%=board.getName()%></h1>
	<%
	for (Article article : articles) {
	%>
	<div>
		번호 :
		<%=article.getId()%>
		<br />
		작성날짜 :
		<%=article.getRegDate()%>
		<br />
		갱신날짜 :
		<%=article.getUpdateDate()%>
		<br />
		작성자 :
		<%=article.getExtra__writer()%>
		<br />
		제목 :
		<%=article.getTitle()%>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html> 