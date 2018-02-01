package com.jxl.jcrawler.plugin.kpu;


import java.io.InputStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.jxl.jcrawler.util.common.PropertiesUtil;
import com.jxl.jcrawler.util.common.StringUtil;
import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;

public class MenuCrawler {
	public static void getMenu() {
		String url = "https://data.kpu.go.id/ss8.php";
		HttpHelper httpHelper = new DefaultHttpHelper();
		
		
		String result = httpHelper.goGet(url);
		System.out.println(result);
	}
	
	
	public static JsonArray loadWilayah() {
		String defaule_value = "[]";
		InputStream stream = PropertiesUtil.class.getResourceAsStream("/csv/wilayah.json");
		defaule_value = StringUtil.inputStream2String(stream);
		JsonArray array = new JsonParser().parse(defaule_value).getAsJsonArray();
		return array;
	}
}
