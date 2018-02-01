package com.jxl.jcrawler.plugin.test;

import com.jxl.jcrawler.enums.VCodeType;
import com.jxl.jcrawler.util.common.RegexUtil;
import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;
import com.jxl.jcrawler.util.image.ImageRecognizeUtil;
import com.jxl.jcrawler.util.image.ImageResponse;

public class BPJSH {
	//https://daftar.bpjs-kesehatan.go.id/bpjs-checking/ 
	//0001454326918
	//13/03/1989
	
	
	public static void main(String[] args) {
		String account = "0001454326918";
		String birth = "13/03/1989";
		String url = "https://daftar.bpjs-kesehatan.go.id/bpjs-checking/";
		HttpHelper httpHelper = new DefaultHttpHelper();
		httpHelper.proxy="103.103.69.251:17777";
		String result = httpHelper.goGet(url);
		String jsessionid = RegexUtil.getValue("jsessionid=(.*?)'", result, 1);
//		String params = "dtid=z_n8b&cmd_0=onClick&uuid_0=t08Qv&data_0={\"pageX\":144,\"pageY\":271,\"which\":1,\"x\":23,\"y\":15.015625}";
//		url = "https://daftar.bpjs-kesehatan.go.id/bpjs-checking/zkau;jsessionid="+jsessionid;
//		httpHelper.config.addHeader("ZK-SID", "5869");
//		httpHelper.config.addHeader("Accept", "*/*");
//		httpHelper.config.addHeader("Origin", "https://daftar.bpjs-kesehatan.go.id");
//		httpHelper.config.addHeader("Referer", "https://daftar.bpjs-kesehatan.go.id/bpjs-checking/");
//		result = httpHelper.goPost(url, params);
		String src = RegexUtil.getValue("src:'/bpjs-checking/zkau/view/(.*?);", result, 1);
		url = "https://daftar.bpjs-kesehatan.go.id/bpjs-checking/zkau/view/"+src;
		ImageResponse image = ImageRecognizeUtil.recognizeWithRetry(url, VCodeType.NUM14_COMPLEX, httpHelper);
		String vcode = image.getMessage();
		url= "https://daftar.bpjs-kesehatan.go.id/bpjs-checking/zkau;jsessionid=aXM9vjUPym+ptcwdiYSxt-Iz.undefined";
		String params = "dtid=z_n8b&cmd_0=onChange&uuid_0=t08Ql&data_0={\"value\":\"0001454326918\",\"start\":13}&cmd_1=onChange&uuid_1=t08Qp&data_1={\"value\":\"$z!t#d:1989.3.13.0.0.0.0\",\"start\":10}&cmd_2=onChange&uuid_2=t08Qz&data_2={\"value\":\""+vcode+"\",\"start\":6}&cmd_3=onClick&uuid_3=t08Q30&data_3={\"pageX\":151,\"pageY\":350,\"which\":1,\"x\":30,\"y\":6.609375}";
		result = httpHelper.goPost(url, params);
		System.out.println(result);
		
	}

}
