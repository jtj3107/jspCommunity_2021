package com.jtj.example.jspCommunity;

public class App {

	public static String getSite() {
		return "JSP Community";
	}
	
	public static String getContextName() {
		return "jspCommunity_2021";
	}

	public static String getMailUrl() {
		return "http://" + getSitrDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/home/main";
	}
	
	public static String getLoginUrl() {
		return "http://" + getSitrDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/member/login";
	}

	public static int getSitePort() {	
		return 8090;
	}
	
	private static String getSitrDomain() {
		return "localhost";
	}
}
