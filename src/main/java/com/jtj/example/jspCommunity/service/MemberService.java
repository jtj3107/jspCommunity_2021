package com.jtj.example.jspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.App;
import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.MemberDao;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.dto.ResultData;
import com.sbs.example.util.Util;

public class MemberService {
	private MemberDao memberDao;
	private EmailService emailService;
	private AttrService attrService;

	public MemberService() {
		memberDao = Container.memberDao;
		emailService = Container.emailService;
		attrService = Container.attrService;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public int join(Map<String, Object> args) {
		int id = memberDao.join(args);
		
		setLoginPwModifiedNow(id);
		
		return id;
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

	public ResultData sendTempLoginPwToEmail(Member actor) {
		// 메일 제목과 내용 만들기
		String siteName = App.getSiteName();
		String SiteLoginUrl = App.getLoginUrl();
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.getTempPassword(6);
		String body = "<h1>임시 패스워드 :" + tempPassword + "</h1>";
		body += "<a href=\"" + SiteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";

		// 메일 발송
		int sendRs = emailService.send(actor.getEmail(), title, body);

		if (sendRs != 1) {
			return new ResultData("F-1", "메일발송에 실패하였습니다.");
		}

		// 고객의 패스워드를 방금 생성한 임시패스워드로 변경
		setTempPassword(actor, tempPassword);

		String resultMsg = String.format("회원님의 임시비밀번호가 `%s` (으)로 발송 되었습니다.", actor.getEmail());

		return new ResultData("S-1", resultMsg, "email", actor.getEmail());
	}

	private void setTempPassword(Member actor, String tempPassword) {
		Map<String, Object> modifyParam = new HashMap<>();
		modifyParam.put("id", actor.getId());
		modifyParam.put("loginPw", Util.sha256(tempPassword));
		modify(modifyParam);

		setIsUsingTempPassword(actor.getId(), true);
	}

	public void setIsUsingTempPassword(int actorId, boolean use) {
		attrService.setValue("member__" + actorId + "__extra__isUsingTempPassword", use, null);
	}
	
	public boolean isUsingTempPassword(int actorId) {
		return attrService.getValueASBoolean("member__" + actorId + "__extra__isUsingTempPassword");
	}

	public void modify(Map<String, Object> param) {
		if(param.get("loginPw") != null) {
			setLoginPwModifiedNow((int) param.get("id"));
		}
		
		memberDao.modify(param);
	}
	
	private void setLoginPwModifiedNow(int actorId) {
		attrService.setValue("member__" + actorId + "__extra__loginPwModifiedDate", Util.getNowDateStr(), null);
	}
	
	public int getOldPasswordDays() {
		return 90;
	}

	public boolean isNeedtoModifyOldLoginPw(int actorId) {
		String date = attrService.getValue("member__" + actorId + "__extra__loginPwModifiedDate");
		
		if(Util.isEmpty(date)) {
			return false;
		}
		
		int pass = Util.getPassedSecondsFrom(date);
		
		int oldPasswordDays = getOldPasswordDays();
		
		if( pass > oldPasswordDays * 60 * 60 * 24) {
			return true;
		}
		
		return false;
	}

}
