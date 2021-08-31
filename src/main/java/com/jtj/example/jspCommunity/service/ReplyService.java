package com.jtj.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.jtj.example.jspCommunity.container.Container;
import com.jtj.example.jspCommunity.dao.ReplyDao;
import com.jtj.example.jspCommunity.dto.Reply;

public class ReplyService {

	private ReplyDao replyDao;

	public ReplyService() {
		replyDao = Container.replyDao;
	}
	
	public int write(Map<String, Object> args) {
		return replyDao.write(args);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}

}
