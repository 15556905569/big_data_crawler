package com.jxl.jcrawler.plugin.test;

import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;

public class TestBpjs {
	public static void main(String[] args) {
		//String url = "https://sso.bpjsketenagakerjaan.go.id/login.bpjs";
		String url = "https://www.lazada.co.id/customer/account/";
		HttpHelper httpHelper = new DefaultHttpHelper();
		httpHelper.proxy="103.103.69.251:17777";
		httpHelper.addCookieString("PHPSESSID_f4467fd86a1d258ab0ca45f8ffed213a=15rbuka8r7fbdnfkebotvk5sr3;");
		String result = httpHelper.goGetString(url);
		System.out.println(result);
	}
}
