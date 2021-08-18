package com.jtj.example.jspCommunity.dao;

import java.util.List;

import com.jtj.example.jspCommunity.dto.Article;
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
}