package com.jtj.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.dto.Board;
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

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public int write(Map<String, Object> args) {
		return articleDao.write(args);
	}

	public int delete(int id) {
		return articleDao.delete(id);
	}

	public int modify(Map<String, Object> args) {
		return articleDao.modify(args);
	}
}