<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인아이디 찾기" />

<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle}</h1>
<div>
	<script>
		let DoFindLoginIdForm__submited = false;
		function DoFindLoginIdForm__submit(form) {
			if (DoFindLoginIdForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.name.value = form.name.value.trim();

			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}

			form.submit();
			DoFindLoginIdForm__submited = true;
		}
	</script>

	<form action="doFindLoginId" method="POST" onsubmit="DoFindLoginIdForm__submit(this); return false;">
		<hr />
		<div>

			<div>이름</div>
			<div>
				<input type="text" name="name" maxlength="50" placeholder="이름을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>이메일</div>
			<div>
				<input type="email" name="email" maxlength="50" placeholder="회원의 이메일주소를 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>
				<input type="submit" value="로그인아이디 찾기" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>
