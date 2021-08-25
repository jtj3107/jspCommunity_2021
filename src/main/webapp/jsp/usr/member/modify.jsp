<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="회원정보수정" />

<%@ include file="../../part/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<h1>${pageTitle}</h1>
<div>
	<script>
		let DoModifyForm__submited = false;

		// 폼 발송전 체크
		function DoModifyForm__submit(form) {
			if (DoModifyForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.loginPw.value = form.loginPw.value.trim();

			if (form.loginPw.value.length > 0) {
				form.loginPwConfirm.value = form.loginPwConfirm.value.trim();

				if (form.loginPwConfirm.value.length == 0) {
					alert('로그인 비밀번호 확인을 입력해주세요.');
					form.loginPwConfirm.focus();

					return;
				}

				if (form.loginPw.value != form.loginPwConfirm.value) {
					alert('로그인 비밀번호가 일치하지 않습니다.');
					form.loginPwConfirm.focus();

					return;
				}
			}

			form.name.value = form.name.value.trim();

			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();

				return;
			}

			form.nickname.value = form.nickname.value.trim();

			if (form.nickname.value.length == 0) {
				alert('별명을 입력해주세요.');
				form.nickname.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}

			form.cellPhoneNo.value = form.cellPhoneNo.value.trim();

			if (form.cellPhoneNo.value.length == 0) {
				alert('전화번호를 입력해주세요.');
				form.cellPhoneNo.focus();

				return;
			}

			if (form.loginPw.value.length > 0) {
				form.loginPwReal.value = sha256(form.loginPw.value);
				form.loginPw.value = "";
				form.loginPwConfirm.value = "";
			}

			form.submit();
			DoModifyForm__submited = true;
		}
	</script>

	<form action="doModify" method="POST" onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="loginPwReal" />
		<hr />
		<div>
			<div>로그인 아이디</div>
			<div>${loginedMember.loginId}</div>
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
			<div>로그인 비밀번호 확인</div>
			<div>
				<input type="password" name="loginPwConfirm" maxlength="50" placeholder="로그인 비밀번호 확인을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>이름</div>
			<div>
				<input type="text" name="name" maxlength="50" placeholder="이름을 입력해주세요." value="${loginedMember.name}" />
			</div>
		</div>
		<hr />
		<div>
			<div>별명</div>
			<div>
				<input type="text" name="nickname" maxlength="50" placeholder="닉네임을 입력해주세요." value="${loginedMember.nickname}"/>
			</div>
		</div>
		<hr />
		<div>
			<div>이메일</div>
			<div>
				<input type="email" name="email" maxlength="50" placeholder="이메일을 입력해주세요." value="${loginedMember.email}"/>
			</div>
		</div>
		<hr />
		<div>
			<div>전화번호</div>
			<div>
				<input type="tel" name="cellPhoneNo" maxlength="50" placeholder="전화번호를 입력해주세요." value="${loginedMember.cellPhoneNo}"/>
			</div>
		</div>
		<hr />
		<div>
			<div>회원정보 수정</div>
			<div>
				<input type="submit" value="수정" />
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>
