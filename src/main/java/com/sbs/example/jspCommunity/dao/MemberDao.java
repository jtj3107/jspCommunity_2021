package com.sbs.example.jspCommunity.dao;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.dto.Member;
import com.sbs.mysqliutil.MysqlUtil;
import com.sbs.mysqliutil.SecSql;

public class MemberDao {
	public List<Member> getForPrintMembers() {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("ORDER BY M.id DESC");

		return MysqlUtil.selectRows(sql, Member.class);
	}

	public void join(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", `name` = ?", args.get("name"));
		sql.append(", nickname = ?", args.get("nickname"));
		sql.append(", email = ?", args.get("email"));
		sql.append(", loginId = ?", args.get("loginId"));
		sql.append(", loginPw = ?", args.get("loginPw"));

		MysqlUtil.insert(sql);
	}

}
