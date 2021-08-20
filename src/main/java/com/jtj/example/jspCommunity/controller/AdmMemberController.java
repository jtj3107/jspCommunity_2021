package com.jtj.example.jspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.service.MemberService;

public class AdmMemberController {
	private MemberService memberService;

	public AdmMemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인후 사용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		List<Member> members = memberService.getForPrintMembers();

		req.setAttribute("members", members);

		return "adm/member/list";
	}

}
