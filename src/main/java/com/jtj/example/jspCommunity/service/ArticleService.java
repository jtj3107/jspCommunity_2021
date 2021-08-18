package com.jtj.example.jspCommunity.service;

import java.util.List;

import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;


public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);
	}
}