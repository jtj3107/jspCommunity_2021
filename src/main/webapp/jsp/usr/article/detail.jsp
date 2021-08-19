<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.jtj.example.jspCommunity.dto.Article"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 상세페이지</title>
</head>
<body>
	<h1>게시물 상세페이지</h1>
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
		<br />
		내용 :
		<%=article.getBody()%>
		<hr />
	</div>
</body>
</html> 