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
import com.sbs.example.util.Util;

public class UsrMemberController extends Controller{
	private MemberService memberService;

	public UsrMemberController() {
		memberService = Container.memberService;
	}

	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/join";
	}

	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		if(Util.isEmpty(loginId)) {
			return msgAndBack(req, "loginId를 입력해주세요.");
		}
		
		String loginPw = req.getParameter("loginPwReal");
		
		if(Util.isEmpty(loginPw)) {
			return msgAndBack(req, "loginPw를 입력해주세요.");
		}
		
		String name = req.getParameter("name");
		
		if(Util.isEmpty(name)) {
			return msgAndBack(req, "name를 입력해주세요.");
		}
		
		String nickname = req.getParameter("nickname");
		
		if(Util.isEmpty(nickname)) {
			return msgAndBack(req, "nickname를 입력해주세요.");
		}
		
		String email = req.getParameter("email");
		
		if(Util.isEmpty(email)) {
			return msgAndBack(req, "email를 입력해주세요.");
		}
		
		String cellPhoneNo = req.getParameter("cellPhoneNo");

		if(Util.isEmpty(cellPhoneNo)) {
			return msgAndBack(req, "cellPhoneNo를 입력해주세요.");
		}
		
		Member oldMember = memberService.getForPrintMemberByLoginId(loginId);

		if (oldMember != null) {
			return msgAndBack(req, String.format("`%s`아이디는 이미 사용중 입니다.", loginId));
		}

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("cellPhoneNo", cellPhoneNo);

		memberService.join(joinArgs);

		return msgAndReplace(req, "회원가입이 완료 되었습니다.", "../home/main");
	}

	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/login";
	}

	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		if(Util.isEmpty(loginId)) {
			return msgAndBack(req, "loginId를 입력해주세요.");
		}
		
		String loginPw = req.getParameter("loginPwReal");

		if(Util.isEmpty(loginPw)) {
			return msgAndBack(req, "loginPw를 입력해주세요.");
		}
		
		Member member = memberService.getForPrintMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "존재하지 않는 회원입니다.");
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return msgAndBack(req, "비밀번호가 틀렸습니다.");
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
		
		return msgAndReplace(req, alertMsg, replaceUrl);
	}

	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");

		return msgAndReplace(req, "로그아웃 되었습니다.", "../home/main");
	}

	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		if(Util.isEmpty(loginId)) {
			return msgAndBack(req, "loginId를 입력해주세요.");
		}
		
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

		return json(req, new ResultData(resultCode, msg, "loginId", loginId));
	}

	public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginId";
	}

	public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		
		if(Util.isEmpty(name)) {
			return msgAndBack(req, "name를 입력해주세요.");
		}
		
		String email = req.getParameter("email");

		if(Util.isEmpty(email)) {
			return msgAndBack(req, "email를 입력해주세요.");
		}
		
		Member member = memberService.getMemberByNameAndEmail(name, email);

		if (member == null) {
			return msgAndBack(req, "존재하지 않는 회원입니다.");
		}

		return msgAndReplace(req, String.format("회원님의 아이디는 `%s` 입니다.", member.getLoginId()), "../member/login?loginId=" + member.getLoginId());
	}

	public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/findLoginPw";
	}

	public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		
		if(Util.isEmpty(loginId)) {
			return msgAndBack(req, "loginId를 입력해주세요.");
		}
		
		String email = req.getParameter("email");
		
		if(Util.isEmpty(email)) {
			return msgAndBack(req, "email를 입력해주세요.");
		}

		Member member = memberService.getForPrintMemberByLoginId(loginId);

		if (member == null) {
			return msgAndBack(req, "존재하지 않는 회원입니다.");
		}

		if (member.getEmail().equals(email) == false) {
			return msgAndBack(req, "회원님의 이메일과 일치하지 않습니다.");
		}

		ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

		if (sendTempLoginPwToEmailRs.isFail()) {
			return msgAndBack(req, sendTempLoginPwToEmailRs.getMsg());
		}

		return msgAndReplace(req, sendTempLoginPwToEmailRs.getMsg(), "../member/login");
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/member/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int loginedMemberId = (int)req.getAttribute("loginedMemberId");
		String loginPw = req.getParameter("loginPwReal");

		if(loginPw != null && loginPw.length() == 0) {
			loginPw = null;
		}
		
		String name = req.getParameter("name");
		
		if(Util.isEmpty(name)) {
			return msgAndBack(req, "name를 입력해주세요.");
		}
		
		String nickname = req.getParameter("nickname");
		
		if(Util.isEmpty(nickname)) {
			return msgAndBack(req, "nickname를 입력해주세요.");
		}
		
		String email = req.getParameter("email");
		
		if(Util.isEmpty(email)) {
			return msgAndBack(req, "email를 입력해주세요.");
		}
		
		String cellPhoneNo = req.getParameter("cellPhoneNo");
		
		if(Util.isEmpty(cellPhoneNo)) {
			return msgAndBack(req, "cellPhoneNo를 입력해주세요.");
		}

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
		
		return msgAndReplace(req, "회원수정이 완료 되었습니다.", "../home/main");
	}
}
