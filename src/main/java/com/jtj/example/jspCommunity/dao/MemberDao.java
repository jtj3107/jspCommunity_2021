package com.jtj.example.jspCommunity.dao;

import java.util.List;

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

}
