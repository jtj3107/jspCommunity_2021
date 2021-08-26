package com.jtj.example.jspCommunity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtj.example.jspCommunity.container.Container;

public class UsrHomeController {

	public String showMain(HttpServletRequest req, HttpServletResponse resp) {
		return "usr/home/main";
	}

}
