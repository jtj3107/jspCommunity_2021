package com.sbs.example.jspCommunity.dao;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.dto.Article;
import com.jtj.example.jspCommunity.dto.Board;
import com.sbs.mysqliutil.MysqlUtil;
import com.sbs.mysqliutil.SecSql;

public class ArticleDao {
	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		sql.append("ORDER BY A.id DESC");

		return MysqlUtil.selectRows(sql, Article.class);
	}

	public Article getForPrintArticleById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("WHERE A.id = ?", id);
		
		return MysqlUtil.selectRow(sql, Article.class);
		
	}

	public Board getBoardById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT B.*");
		sql.append("FROM board AS B");
		sql.append("WHERE B.id = ?", id);
		
		return MysqlUtil.selectRow(sql, Board.class);
	}

	public int write(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", boardId = ?", args.get("boardId"));
		sql.append(", memberId = ?", args.get("memberId"));
		sql.append(", title = ?", args.get("title"));
		sql.append(", `body` = ?", args.get("body"));
		
		return MysqlUtil.insert(sql);
	}

	public int delete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.delete(sql);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		
		boolean needToUpdate = false;
		
		if( args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}
		
		if(args.get("body") != null) {
			needToUpdate = true;
			sql.append(", `body` = ?", args.get("body"));
		}
		
		if( needToUpdate == false) {
			return 0;
		}

		sql.append("WHERE id = ?", args.get("id"));
		
		return MysqlUtil.update(sql);
	}
}