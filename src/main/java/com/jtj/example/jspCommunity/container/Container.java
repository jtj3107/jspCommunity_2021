package com.jtj.example.jspCommunity.container;

import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.jtj.example.jspCommunity.service.ArticleService;

public class Container {
	public static ArticleService articleService;
	public static ArticleDao articleDao;

	static {
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}
}