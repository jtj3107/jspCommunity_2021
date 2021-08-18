package com.jtj.example.jspCommunity.container;

import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.jtj.example.jspCommunity.controller.usr.ArticleController;
import com.jtj.example.jspCommunity.controller.usr.MemberController;
import com.jtj.example.jspCommunity.dao.MemberDao;
import com.jtj.example.jspCommunity.service.ArticleService;
import com.jtj.example.jspCommunity.service.MemberService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static ArticleController articleController;
		
	public static MemberDao memberDao;
	public static MemberService memberService;
	public static MemberController memberController;

	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();

		memberService = new MemberService();
		articleService = new ArticleService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}