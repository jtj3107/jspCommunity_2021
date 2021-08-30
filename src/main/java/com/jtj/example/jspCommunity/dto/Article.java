package com.jtj.example.jspCommunity.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitsCount;

	private boolean extra__actorCanLike;
	private boolean extra__actorCanCancelLike;
	private boolean extra__actorCanDisLike;
	private boolean extra__actorCanCancelDisLike;
	
	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;
	private int extra__likePoint;
	private int extra__likeOnlyPoint;
	private int extra__dislikeOnlyPoint;

}