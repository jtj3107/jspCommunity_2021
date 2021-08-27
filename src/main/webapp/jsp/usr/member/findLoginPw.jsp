<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="로그인비밀번호 찾기" />

<%@ include file="../../part/head.jspf"%>

<div class="title-bar padding-0-10 con-min-width">
	<h1 class="con">
		<span>
			<i class="far fa-user-circle"></i>
		</span>
		<span>${pageTitle}</span>
	</h1>
</div>
<div class="find-login-Pw-form-box form-box padding-0-10 con-min-width">
	<script>
		let DoFindLoginPwForm__submited = false;
		function DoFindLoginPwForm__submit(form) {
			if (DoFindLoginPwForm__submited) {
				alert('처리중입니다.');
				return;
			}

			form.loginId.value = form.loginId.value.trim();

			if (form.loginId.value.length == 0) {
				alert('로그인아이디를 입력해주세요.');
				form.loginId.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}

			form.submit();
			DoFindLoginPwForm__submited = true;
		}
	</script>

	<form action="doFindLoginPw" method="POST" onsubmit="DoFindLoginPwForm__submit(this); return false;">
		<table>
			<colgroup>
				<col width="150">
			</colgroup>
			<tbody>
				<tr>
					<th>
						<span>로그인아이디</span>
					</th>
					<td>
						<div>
							<input type="text" name="loginId" maxlength="50" placeholder="로그인아이디를 입력해주세요." />
						</div>
					</td>
				</tr>

				<tr>
					<th>
						<span>이메일</span>
					</th>
					<td>
						<div>
							<input type="email" name="email" maxlength="50" placeholder="가입시 입력한 이메일을 입력해주세요." />
						</div>
					</td>
				</tr>

				<tr>
					<th>
						<span>로그인비밀번호 찾기</span>
					</th>
					<td>
						<div>
							<div class="btn-wrap">
								<input class="btn btn-primary" type="submit" value="로그인비밀번호 찾기" />
								<button class="btn btn-info" type="button" onclick="history.back();">뒤로가기</button>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>
