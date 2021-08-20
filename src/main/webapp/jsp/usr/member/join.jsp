<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>
<div>
	<form action="doJoin" method="POST">
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>
				<input type="text" name="loginId" maxlength="50" placeholder="로그인 아이디를 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>로그인 비밀번호</div>
			<div>
				<input type="password" name="loginPw" maxlength="50" placeholder="로그인 비밀번호를 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>이름</div>
			<div>
				<input type="text" name="name" maxlength="50" placeholder="이름을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>별명</div>
			<div>
				<input type="text" name="nickname" maxlength="50" placeholder="닉네임을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>이메일</div>
			<div>
				<input type="email" name="email" maxlength="50" placeholder="이메일을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>전화번호</div>
			<div>
				<input type="number" name="cellphoneNo" maxlength="50" placeholder="전화번호를 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>가입</div>
			<div>
				<input type="submit" value="가입" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>
