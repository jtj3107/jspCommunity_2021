package com.jtj.example.jspCommunity.service;

import java.util.List;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.MemberDao;
import com.jtj.example.jspCommunity.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

}
