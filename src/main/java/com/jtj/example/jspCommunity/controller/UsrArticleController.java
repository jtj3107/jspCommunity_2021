package com.jtj.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.dto.Board;
import com.jtj.example.jspCommunity.service.ArticleService;
import com.sbs.example.util.Util;

public class UsrArticleController extends Controller{
	private ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		String searchKeywordType = req.getParameter("searchKeywordType");
		String searchKeyword = req.getParameter("searchKeyword");

		int itemsInAPage = 30;
		int page = Util.getAsInt(req.getParameter("page"), 1);
		int limitStart = (page - 1) * itemsInAPage;

		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		
		if(boardId == 0) {
			return msgAndBack(req, "게시판번호를 입력해주세요.");
		}
		
		Board board = articleService.getBoardById(boardId);

		if(board == null) {
			return msgAndBack(req, "게시판이 존재하지 않습니다.");
		}
		
		int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId, limitStart, itemsInAPage,
				searchKeywordType, searchKeyword);
		
		int totalPage = (int)Math.ceil((double)totalCount / itemsInAPage);

		int pageBoxSize = 5;
		
		// 현재 페이지 박스 시작, 끝 계산
		int previousPageBoxesCount = (page -1) / pageBoxSize;
		int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
		int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;
		
		if(pageBoxEndPage > totalPage) {
			pageBoxEndPage = totalPage;
		}
		
		// 이전버튼 페이지 계산
		int pageBoxStartBeforePage = pageBoxStartPage - 1;
		if(pageBoxStartBeforePage < 1) {
			pageBoxStartBeforePage = 1;
		}
		
		// 다음버튼 페이지 계산
		int pageBoxEndAfterPage = pageBoxEndPage + 1;
		
		if(pageBoxEndAfterPage > totalPage) {
			pageBoxEndAfterPage = totalPage;
		}
		
		// 이전버튼 노출여부 계산
		boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
		// 다음버튼 노출여부 계산
		boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;
		
		req.setAttribute("board", board);
		req.setAttribute("articles", articles);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);

		req.setAttribute("pageBoxStartBeforeBtnNeedToShow", pageBoxStartBeforeBtnNeedToShow);
		req.setAttribute("pageBoxEndAfterBtnNeedToShow", pageBoxEndAfterBtnNeedToShow);
		req.setAttribute("pageBoxStartBeforePage", pageBoxStartBeforePage);
		req.setAttribute("pageBoxEndAfterPage", pageBoxEndAfterPage);
		req.setAttribute("pageBoxStartPage", pageBoxStartPage);
		req.setAttribute("pageBoxEndPage", pageBoxEndPage);
		
		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Util.getAsInt(req.getParameter("id"), 0);

		if(id == 0) {
			return msgAndBack(req, "게시물 번호를 입력해주세요.");
		}
		
		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		req.setAttribute("article", article);

		return "usr/article/detail";
	}

	public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

		if(boardId == 0) {
			return msgAndBack(req, "게시판번호를 입력해주세요.");
		}
		
		Board board = articleService.getBoardById(boardId);
		
		if(board == null) {
			return msgAndBack(req, "게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);

		return "usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = (int) req.getAttribute("loginedMemberId");
		int boardId = Util.getAsInt(req.getParameter("boardId"), 0);
		
		if(boardId == 0) {
			return msgAndBack(req, "게시판 번호를 입력해주세요.");
		}
		
		String title = req.getParameter("title");
		
		if(Util.isEmpty(title)) {
			return msgAndBack(req, "제목을 입력해주세요.");
		}
		
		String body = req.getParameter("body");
		
		if(Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요.");
		}

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberId", memberId);
		writeArgs.put("boardId", boardId);
		writeArgs.put("title", title);
		writeArgs.put("body", body);
		
		int newArticleId = articleService.write(writeArgs);

		return msgAndReplace(req, newArticleId + "번 게시물이 생성되었습니다.", String.format("detail?id=%d", newArticleId));
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Util.getAsInt(req.getParameter("id"), 0);

		if(id == 0) {
			return msgAndBack(req, "id를 입력해주세요.");
		}
		
		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (article.getMemberId() != loginedMemberId) {
			return msgAndBack(req, id + "번 게시물에 권한이 없습니다.");
		}

		articleService.delete(id);

		int boardId = article.getBoardId();

		return msgAndReplace(req, id + "번 게시물이 삭제되었습니다.", String.format("list?boardId=%d", boardId));
	}

	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Util.getAsInt(req.getParameter("id"), 0);

		if(id == 0) {
			return msgAndBack(req, "id를 입력해주세요.");
		}
		
		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (article.getMemberId() != loginedMemberId) {
			return msgAndBack(req, id + "번 게시물에 권한이 없습니다.");
		}

		Board board = articleService.getBoardById(article.getBoardId());

		req.setAttribute("board", board);
		req.setAttribute("article", article);

		return "usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Util.getAsInt(req.getParameter("id"), 0);
		
		if(id == 0) {
			return msgAndBack(req, "id를 입력해주세요.");
		}

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
		}

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		if (article.getMemberId() != loginedMemberId) {
			return msgAndBack(req, id + "번 게시물의 수정 권한이 없습니다.");
		}

		String title = req.getParameter("title");
		
		if(Util.isEmpty(title)) {
			return msgAndBack(req, "제목을 입력해주세요.");
		}
		
		String body = req.getParameter("body");

		if(Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요.");
		}
		
		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("id", id);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		articleService.modify(modifyArgs);

		return msgAndReplace(req, id + "번 게시물이 수정되었습니다.", String.format("detail?id=%d", id));
	}

}
