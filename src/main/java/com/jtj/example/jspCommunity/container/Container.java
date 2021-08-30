package com.jtj.example.jspCommunity.container;

import com.jtj.example.jspCommunity.controller.AdmMemberController;
import com.jtj.example.jspCommunity.controller.UsrArticleController;
import com.jtj.example.jspCommunity.controller.UsrHomeController;
import com.jtj.example.jspCommunity.controller.UsrLikeController;
import com.jtj.example.jspCommunity.controller.UsrMemberController;
import com.jtj.example.jspCommunity.dao.AttrDao;
import com.jtj.example.jspCommunity.dao.LikeDao;
import com.jtj.example.jspCommunity.service.ArticleService;
import com.jtj.example.jspCommunity.service.AttrService;
import com.jtj.example.jspCommunity.service.EmailService;
import com.jtj.example.jspCommunity.service.LikeService;
import com.jtj.example.jspCommunity.service.MemberService;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dao.MemberDao;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static AttrDao attrDao;
	public static LikeDao likeDao;
	public static MemberDao memberDao;
	
	public static LikeService likeService;
	public static MemberService memberService;
	public static EmailService emailService;
	public static AttrService attrService;
	
	public static UsrLikeController usrLikeController;
	public static UsrArticleController usrArticleController;
	public static UsrMemberController usrMemberController;
	public static AdmMemberController admMemberController;
	public static UsrHomeController usrHomeController;
	
	static {
		attrDao = new AttrDao();
		likeDao = new LikeDao();
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		
		attrService = new AttrService();
		likeService = new LikeService();
		emailService = new EmailService();
		memberService = new MemberService();
		articleService = new ArticleService();

		usrLikeController = new UsrLikeController();
		usrMemberController = new UsrMemberController();
		admMemberController = new AdmMemberController();
		usrArticleController = new UsrArticleController();
		usrHomeController = new UsrHomeController();
	}
}