package com.jtj.example.jspCommunity.service;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.LikeDao;
import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.dto.Member;

public class LikeService {

	private LikeDao likeDao;

	public LikeService() {
		likeDao = Container.likeDao;
	}

	public boolean actorCanLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) > 0;
	}

	public boolean actorCanDisLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
	}

	public boolean actorCanCancelDisLike(Article article, Member actor) {
		return likeDao.getPoint("article", article.getId(), actor.getId()) < 0;
	}
}
