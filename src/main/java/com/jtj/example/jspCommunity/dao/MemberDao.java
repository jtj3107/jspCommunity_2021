package com.jtj.example.jspCommunity.dao;

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

	public int join(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", `name` = ?", args.get("name"));
		sql.append(", nickname = ?", args.get("nickname"));
		sql.append(", email = ?", args.get("email"));
		sql.append(", cellPhoneNo = ?", args.get("cellPhoneNo"));
		sql.append(", loginId = ?", args.get("loginId"));
		sql.append(", loginPw = ?", args.get("loginPw"));

		return MysqlUtil.insert(sql);
	}

	public Member getForPrintMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE M.loginId = ?", loginId);

		return MysqlUtil.selectRow(sql, Member.class);
	}

	public Member getMemberById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE M.id = ?", id);

		return MysqlUtil.selectRow(sql, Member.class);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM `member` AS M");
		sql.append("WHERE M.name = ?", name);
		sql.append("AND M.email = ?", email);
		sql.append("ORDER BY id DESC");
		sql.append("LIMIT 1");

		return MysqlUtil.selectRow(sql, Member.class);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE `member`");
		sql.append("SET updateDate = NOW()");
		
		boolean needToUpdate = false;
		
		if(args.get("loginPw") != null) {
			needToUpdate = true;
			sql.append(", loginPw = ?", args.get("loginPw"));
		}
		
		if(args.get("name") != null) {
			needToUpdate = true;
			sql.append(", `name` = ?", args.get("name"));
		}
		
		if(args.get("nickname") != null) {
			needToUpdate = true;
			sql.append(", nickname = ?", args.get("nickname"));
		}
		
		if(args.get("email") != null) {
			needToUpdate = true;
			sql.append(", email = ?", args.get("email"));
		}
		
		if(args.get("cellPhoneNo") != null) {
			needToUpdate = true;
			sql.append(", cellPhoneNo = ?", args.get("cellPhoneNo"));
		}
		
		if(args.get("authLevel") != null) {
			needToUpdate = true;
			sql.append(", authLevel = ?", args.get("authLevel"));
		}
		
		if(needToUpdate == false) {
			return 0;
		}
		
		sql.append("WHERE id = ?", args.get("id"));
		
		return MysqlUtil.update(sql);
	}

}
