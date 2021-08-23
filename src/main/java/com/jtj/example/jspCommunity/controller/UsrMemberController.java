package com.jtj.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.service.MemberService;
import com.sbs.example.util.Util;

public class UsrMemberController {
	private MemberService memberService;
	
	public UsrMemberController() {
		memberService = Container.memberService;
	}
	
	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		if ((boolean)req.getAttribute("isLogined")) {
			req.setAttribute("alertMsg", "로그아웃 이후 사용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		if ((boolean)req.getAttribute("isLogined")) {
			req.setAttribute("alertMsg", "로그아웃 이후 사용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String cellPhoneNo = req.getParameter("cellPhoneNo");
		
		Member oldMember = memberService.getForPrintMemberByLoginId(loginId);
		
		if(oldMember != null) {
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
		if ((boolean)req.getAttribute("isLogined")) {
			req.setAttribute("alertMsg", "로그아웃 이후 사용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		return "usr/member/login";
	}
	
	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		if ((boolean)req.getAttribute("isLogined")) {
			req.setAttribute("alertMsg", "로그아웃 이후 사용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPw");
		
		Member member = memberService.getForPrintMemberByLoginId(loginId);
		
		if(member == null) {
			req.setAttribute("alertMsg", "존재하지 않는 회원입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if(member.getLoginPw().equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 틀렸습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		HttpSession session = req.getSession();
		
		session.setAttribute("loginedMemberId", member.getId());
		
		req.setAttribute("alertMsg", String.format("%s님 환영합니다.", member.getNickname()));
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
		
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {	
		if ((boolean)req.getAttribute("isLogined") == false) {
			req.setAttribute("alertMsg", "이미 로그아웃 상태 입니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");
		
		req.setAttribute("alertMsg", "로그아웃 되었습니다.");
		req.setAttribute("replaceUrl", "../home/main");
		return "common/redirect";
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		Member member = memberService.getForPrintMemberByLoginId(loginId);
		
		Map<String, Object> rs = new HashMap<>();
		
		String resultCode = null;
		String msg = null;
		
		if(member != null) {
			resultCode = "F-1";
			msg = "이미 사용중인 로그인아이디 입니다.";
		}
		else {
			resultCode = "S-1";
			msg = "사용가능한 로그인아이디 입니다.";
		}
		
		rs.put("resultCode", resultCode);
		rs.put("msg", msg);
		rs.put("loginId", loginId);
		
		req.setAttribute("data", Util.getJsonText(rs));
		return "common/pure";
	}

}
