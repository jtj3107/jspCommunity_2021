package com.jtj.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Member;
import com.sbs.example.jspCommunity.dao.MemberDao;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public void join(Map<String, Object> args) {
		memberDao.join(args);
	}

	public Member getForPrintMemberByLoginId(String loginId) {
		return memberDao.getForPrintMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

}
