package com.jtj.example.jspCommunity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private String relTypeCode;
	private int relId;
	private String body;
	private int memberId;

	private boolean extra__actorCanLike;
	private boolean extra__actorCanCancelLike;
	private boolean extra__actorCanDisLike;
	private boolean extra__actorCanCancelDisLike;

	private String extra__writer;
	private int extra__likePoint;
	private int extra__likeOnlyPoint;
	private int extra__dislikeOnlyPoint;
}
