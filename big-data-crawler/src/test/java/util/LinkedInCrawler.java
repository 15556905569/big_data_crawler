package util;

import org.junit.Test;

import com.jxl.jcrawler.enums.ContentTypes;
import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;

public class LinkedInCrawler {
	HttpHelper httpHelper = new DefaultHttpHelper();
	@Test
	public void crawler() {
		httpHelper.config.setContentType(ContentTypes.JSON);
		for (int i = 0; i < 1000; i++) {
			String result = crawlerLinkedIn();
			System.out.println(result+i);
		}
		
	}
	public String crawlerLinkedIn() {
		String url = "http://192.168.100.63:7002/message/front/setUser?impl=JAVA";
		String params = "{\n" + 
				"  \"account\": \"xuzhilin@lianzhisoft.com\",\n" + 
				"  \"email\": \"string\",\n" + 
				"  \"errMsg\": \"string\",\n" + 
				"  \"idCardNum\": \"string\",\n" + 
				"  \"idCardType\": \"string\",\n" + 
				"  \"name\": \"string\",\n" + 
				"  \"phone\": \"string\",\n" + 
				"  \"pwd\": \"Juxinli2017\",\n" + 
				"  \"pwd2\": \"string\",\n" + 
				"  \"remark\": {},\n" + 
				"  \"reportTaskToken\": \"string\",\n" + 
				"  \"token\": \"1738test\",\n" + 
				"  \"website\": \"linkedin\"\n" + 
				"}";
		String result = httpHelper.goPost(url, params);
		return result;
	}
	

}
