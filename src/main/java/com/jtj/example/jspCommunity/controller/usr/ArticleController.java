package com.jtj.example.jspCommunity.controller.usr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);
		
		req.setAttribute("articles", articles);
		
		return "usr/article/list";
	}

}
