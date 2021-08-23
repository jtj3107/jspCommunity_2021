package com.jtj.example.jspCommunity.container;

import com.jtj.example.jspCommunity.controller.AdmMemberController;
import com.jtj.example.jspCommunity.controller.UsrArticleController;
import com.jtj.example.jspCommunity.controller.UsrHomeController;
import com.jtj.example.jspCommunity.controller.UsrMemberController;

import com.jtj.example.jspCommunity.service.ArticleService;
import com.jtj.example.jspCommunity.service.EmailService;
import com.jtj.example.jspCommunity.service.MemberService;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static UsrArticleController articleController;

	public static MemberDao memberDao;
	public static MemberService memberService;
	public static EmailService emailService;
	
	public static UsrMemberController memberController;
	public static AdmMemberController admMemberController;
	public static UsrHomeController homeController;

	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		emailService = new EmailService();
		memberService = new MemberService();
		articleService = new ArticleService();

		memberController = new UsrMemberController();
		admMemberController = new AdmMemberController();
		articleController = new UsrArticleController();
		homeController = new UsrHomeController();
	}
}