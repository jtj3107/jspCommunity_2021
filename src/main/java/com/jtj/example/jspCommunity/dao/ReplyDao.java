package com.jtj.example.jspCommunity.dao;

import java.util.Map;

import com.sbs.mysqliutil.MysqlUtil;
import com.sbs.mysqliutil.SecSql;

public class ReplyDao {

	public int write(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO reply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", relTypeCode = ?", args.get("relTypeCode"));
		sql.append(", relId = ?", args.get("relId"));
		sql.append(", memberId = ?", args.get("memberId"));
		sql.append(", `body` = ?", args.get("body"));

		return MysqlUtil.insert(sql);
	}

}
