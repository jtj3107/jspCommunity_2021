package com.jtj.example.jspCommunity.controller.adm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.service.MemberService;

public class MemberController {
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();

		req.setAttribute("members", members);

		return "adm/member/list";
	}

}
