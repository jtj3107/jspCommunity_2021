package com.jtj.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.dto.ResultData;
import com.jtj.example.jspCommunity.service.MemberService;

public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String cellPhoneNo = req.getParameter("cellPhoneNo");

		Member oldMember = memberService.getForPrintMemberByLoginId(loginId);

		if (oldMember != null) {
			req.setAttribute("alertMsg", String.format("`%s`아이디는 이미 사용중 입니다.", loginId));
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("cellPhoneNo", cellPhoneNo);

		memberService.join(joinArgs);

		req.setAttribute("alertMsg", "회원가입이 완료 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		Member member = memberService.getForPrintMemberByLoginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 회원입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 틀렸습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		HttpSession session = req.getSession();

		session.setAttribute("loginedMemberId", member.getId());

		boolean isUsingTempPasword = memberService.getIsUsingTempPassword(member.getId());
		
		String alertMsg = String.format("%s님 환영합니다.", member.getNickname());
		String replaceUrl = "../home/main";
		
		if(isUsingTempPasword) {
			alertMsg = String.format("%s님 현재 임시 비밀번호를 사용중입니다. 변경 후 이용해주세요.", member.getNickname());
			replaceUrl = "../member/modify";
		}
		
		req.setAttribute("alertMsg", alertMsg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";

	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");

		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		Member member = memberService.getForPrintMemberByLoginId(loginId);

		String resultCode = null;
		String msg = null;

		if (member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인아이디 입니다.";
		} else {
			resultCode = "S-1";
			msg = "사용가능한 로그인아이디 입니다.";
		}

		req.setAttribute("data", new ResultData(resultCode, msg, "loginId", loginId));
		return "common/json";
	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 회원입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("alertMsg", String.format("회원님의 아이디는 `%s` 입니다.", member.getLoginId()));
		req.setAttribute("replaceUrl", "../member/login?loginId=" + member.getLoginId());
		return "common/redirect";

	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		Member member = memberService.getForPrintMemberByLoginId(loginId);

		if (member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 회원입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		if (member.getEmail().equals(email) == false) {
			req.setAttribute("alertMsg", "회원님의 이메일과 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
		req.setAttribute("replaceUrl", "../member/login");
		return "common/redirect";
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		String loginPw = (String) req.getParameter("loginPwReal");

		if(loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}
		
		String name = (String) req.getParameter("name");
		String nickname = (String) req.getParameter("nickname");
		String email = (String) req.getParameter("email");
		String cellPhoneNo = (String) req.getParameter("cellPhoneNo");

		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("loginPw", loginPw);
		modifyArgs.put("name", name);
		modifyArgs.put("nickname", nickname);
		modifyArgs.put("email", email);
		modifyArgs.put("cellPhoneNo", cellPhoneNo);
		modifyArgs.put("id", loginedMemberId);

		memberService.modify(modifyArgs);

		if(loginPw != null) {
			memberService.setIsUsingTempPassword(loginedMemberId, false);
		}
		
		req.setAttribute("alertMsg", "회원수정이 완료 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

}
