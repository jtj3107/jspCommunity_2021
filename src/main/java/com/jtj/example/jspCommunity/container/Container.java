package com.jtj.example.jspCommunity.container;

import com.jtj.example.jspCommunity.controller.AdmMemberController;
import com.jtj.example.jspCommunity.controller.UsrArticleController;
import com.jtj.example.jspCommunity.controller.UsrMemberController;
import com.jtj.example.jspCommunity.dao.MemberDao;
import com.jtj.example.jspCommunity.service.ArticleService;
import com.jtj.example.jspCommunity.service.MemberService;
import com.sbs.example.jspCommunity.dao.ArticleDao;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UsrArticleController articleController;
		
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static UsrMemberController memberController;
	public static AdmMemberController admMemberController;

	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new UsrMemberController();
		admMemberController = new AdmMemberController();
		articleController = new UsrArticleController();
	}
}