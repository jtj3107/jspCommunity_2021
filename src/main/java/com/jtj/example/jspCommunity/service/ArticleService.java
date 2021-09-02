package com.jtj.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.dto.Board;
import com.jtj.example.jspCommunity.dto.Member;
import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.ArticleDao;


public class ArticleService {
	private ArticleDao articleDao;
	private LikeService likeService;

	public ArticleService() {
		articleDao = Container.articleDao;
		likeService = Container.likeService;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
		return articleDao.getForPrintArticlesByBoardId(boardId, limitStart, limitCount, searchKeywordType, searchKeyword);
	}

	public Article getForPrintArticleById(int id) {
		return getForPrintArticleById(id, null);
	}
	
	public Article getForPrintArticleById(int id, Member actor) {
		Article article = articleDao.getForPrintArticleById(id);
		
		if(article == null) {
			return null;
		}
		
		if(actor != null) {
			updateInfoForPrint(article, actor);
		}
		
		return article;
	}

	private void updateInfoForPrint(Article article, Member actor) {
		boolean actorCanLike = likeService.actorCanLike(article, actor);
		boolean actorCanCancelLike = likeService.actorCanCancelLike(article, actor);
		boolean actorCanDisLike = likeService.actorCanDisLike(article, actor);
		boolean actorCanCancelDisLike = likeService.actorCanCancelDisLike(article, actor);
	
		article.setExtra__actorCanLike(actorCanLike);
		article.setExtra__actorCanCancelLike(actorCanCancelLike);
		article.setExtra__actorCanDisLike(actorCanDisLike);
		article.setExtra__actorCanCancelDisLike(actorCanCancelDisLike);
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

	public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
		return articleDao.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
}