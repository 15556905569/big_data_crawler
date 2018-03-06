package com.jxl.jcrawler.hupuMonitor;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.jxl.jcrawler.config.SpringHolder;
import com.jxl.jcrawler.model.hupupost.Count;
import com.jxl.jcrawler.model.hupupost.HupuDateRepository;
import com.jxl.jcrawler.model.hupupost.HupuPost;
import com.jxl.jcrawler.plugin.weibo.SinaWeiBo;
import com.jxl.jcrawler.util.common.DateUtil;
import com.jxl.jcrawler.util.common.RegexUtil;
import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;
import com.jxl.jcrawler.util.parse.Tool_Html;

public class HupuCrawler {
	private static Logger LOGGER = LoggerFactory.getLogger(HupuCrawler.class);
	private static HttpHelper httphelper = new DefaultHttpHelper();
	private static Tool_Html tool_html = new Tool_Html();
	private static String lastNewPostTitle = "";//上次刷新最新帖子名称
	private static String NewPostTitle = "";//本次新帖刷新结束帖子名称
	private static String firstPostTitle = "";//开始监控时第一个帖子
	private static boolean nextNew=true;//判断最新帖子是否需要翻页
	private static boolean nextOld=false;//判断旧帖子是否需要翻页
	private static boolean next=true;//判断是否需要翻页(翻页到启动时第一条帖子)
	private static boolean first=true;//判断是否是第一次
	private static boolean isOld = false;
	private static List<HupuPost> hupuNewList = new ArrayList<>();
	private static HupuDateRepository dataRepository = SpringHolder.getBean(HupuDateRepository.class);
	
	static {
		httphelper.addCookieString("_dacevid3=1b42908f.2c52.5828.15fd.d4295637c34f; __gads=ID=dc6e6bc8c849dade:T=1519961144:S=ALNI_MYevxCiKSULNOjeCPfgzI4MkKnhjA; _HUPUSSOID=f51caa6b-8f1f-4a22-9603-40dcecb45b78; _fmdata=ws%2BNZwwX1BhGREcffKVAxS3gua5L3FLIiZdTWSwzsLVQDfRDwIaomgrFlLVtggnGZdkMx1GAqhsTImX62%2FLr2VTG05jbppCzPPLJjjL91MM%3D; _cnzz_CV30020080=buzi_cookie%7C1b42908f.2c52.5828.15fd.d4295637c34f%7C-1; _CLT=00376064be821b71351c003dda774e37; ua=25336902; u=34160459|R2FsZW5IdWE=|1aeb|f16c3f0d53c539a26163b6475a82f03b|53c539a26163b647|aHVwdV9kYzliZDE5MjQ5ZDIyYTRj; us=222975e6be0e3d222c72dcd0caf8cc1eb1cbb7356e0ec374fc5ecab64360a96b64800985435841a07f823dbba7e11dc584a60b02949dd158866bfa9258f3dc4d; __dacevst=b99916e0.624ca5b4|1520215960548; Hm_lvt_39fc58a7ab8a311f2f6ca4dc1222a96e=1519961141,1519961419,1520214020,1520214161; Hm_lpvt_39fc58a7ab8a311f2f6ca4dc1222a96e=1520214161");
		String result = httphelper.goGet("https://bbs.hupu.com/my_forums");
		System.out.println(result);
		LOGGER.info("程序启动，加载Cookie");
	}
	//爬取虎扑某板块所有新发布帖子
	public static void getAllPost(String plate) {
		try {
			plate = "湿乎乎的话题";
			String url = "https://bbs.hupu.com/";
			String result = httphelper.goGet(url);
			String uri = RegexUtil.getValue("<a href=\"(.*?)\" target=\"_blank\">"+plate+"</a>", result, 1);
			uri = "https://bbs.hupu.com"+uri;
			result = httphelper.goGet(url);
			int i = 1;
			do {
				url = uri+"-postdate-"+i;
				result = httphelper.goGet(url);
				if (nextNew) {
				pasePagePostNew(result);
				}else {
				pasePagePostOld(result);
				}
				if(nextNew||nextOld) {
				 i++;
				}
			} while (next);
		
		} finally {
			if (hupuNewList.size()>0) {
				saveNewList();
				hupuNewList.clear();
			}
			next=true;
			nextNew=true;
			nextOld=false;
			isOld = false;
			System.out.println();
		}
	}
	
	/**
	 * 解析帖子集合页面，判断有无新帖出现
	 * 
	 * 
	 * 解析单页面，每次定时爬取时，当解析帖子与上次最新帖子名称相同或未第一次爬取时，停止爬取。
	 * @param html
	 */
	public static void pasePagePostNew(String html) {
		long selectTime = System.currentTimeMillis();
		List<String> titleList = new ArrayList<>();
		Document document = tool_html.get_html(html);
		Element element = tool_html.elements("获取页面帖子集合", document, "-select form#ajaxtable").get(0);
		Elements elements = tool_html.elements("获取单条帖子信息", element, "-select li");
		for (Element postElement : elements) {
			if (postElement.toString().contains("[置顶]")) {
				continue;
		}
			String postTitle = tool_html.value("获取帖子名称", postElement, "-select a.truetit -text", "");
			titleList.add(postTitle);
			if (lastNewPostTitle.equals(postTitle)) {
				NewPostTitle = titleList.get(0);
				nextNew =false;
				LOGGER.info("爬取到上次监控结束帖，结束新帖解析");
				break;
			}
			List<HupuPost> list = null;
			list  = dataRepository.findByPostTitle(postTitle);
			if(list.size()>0) {
				NewPostTitle = titleList.get(0);
				nextNew =false;
				LOGGER.info("数据库监控到 爬取到上次监控结束帖，结束新帖解析");
				break;
			}
			//
			if (lastNewPostTitle.equals("")) {
				NewPostTitle = postTitle;
				if (first) {
				firstPostTitle = postTitle;
				first = false;
				}
				nextNew =false;
				LOGGER.info("程序启动，此时暂无新帖！！！"+"\""+firstPostTitle+"\""+"为第一条帖子");
				break;
			}
				String count = tool_html.value("获取帖子回复浏览次数", postElement, "-select span.ansour.box -text", "");
				HupuPost post = new HupuPost();
				Count countObj = new Count();
				List<Count> counts = new ArrayList<>();
				post.setPostTitle(postTitle);
				String authorName = tool_html.value("获取帖子发布人", postElement, "-select div.author.box -select a -index 0 -text", "");
				post.setAuthorName(authorName);
				String startDate = tool_html.value("获取帖子发布日期", postElement, "-select div.author.box -select a -index 1 -text", "");
				post.setStartDate(startDate);
				if (count.contains("/")) {
					countObj.setReplyCount(count.split("/")[0]);
					countObj.setScanCount(count.split("/")[1]);
					countObj.setTime(DateUtil.getFromatDate(selectTime, "yyyy-MM-dd HH:mm"));
					counts.add(countObj);
					post.setCounts(counts);
				}
				String url  = tool_html.value("获取帖子链接", postElement, "-select a.truetit -attr href", "");
				post.setUrl(url);
				hupuNewList.add(post);
				//dataRepository.save(post);
				LOGGER.info("添加新帖子："+postTitle+"  到待保存集合成功");
		}
	}
	
	/**
	 * 爬取解析已监控帖子
	 * @param html
	 */
	public static void pasePagePostOld(String html) {
		nextOld=true;
		long selectTime = System.currentTimeMillis();
		Document document = tool_html.get_html(html);
		Element element = tool_html.elements("获取页面帖子集合", document, "-select form#ajaxtable").get(0);
		Elements elements = tool_html.elements("获取单条帖子信息", element, "-select li");
		for (Element postElement : elements) {
			if (postElement.toString().contains("[置顶]")) {
				continue;
		}
		String postTitle = tool_html.value("获取帖子名称", postElement, "-select a.truetit -text", "");
		if (lastNewPostTitle.equals("")) {
			lastNewPostTitle = NewPostTitle;
			LOGGER.info("此时暂无旧帖，将"+lastNewPostTitle+"设置为旧帖");
			next = false;
			break;
		}
		if (lastNewPostTitle.equals(postTitle)) {
			lastNewPostTitle = NewPostTitle;
			LOGGER.info("解析到上次结束点，开始更新旧帖");
			isOld = true;
		}
		List<HupuPost> list = null;
		list  = dataRepository.findByPostTitle(postTitle);
		if (!isOld) {
			if(list.size()>0) {
				lastNewPostTitle = NewPostTitle;
				LOGGER.info("数据库校验到上次结束点，更新旧帖");
				isOld = true;
			}
		}
		if (isOld) {
			if (firstPostTitle.equals(postTitle)) {
				next = false;
				LOGGER.info("解析到程序启动时第一条帖子，旧帖解析完成，停止翻页");
				break;
			}
			String count = tool_html.value("获取帖子回复浏览次数", postElement, "-select span.ansour.box -text", "");
			String replyCount;
			String scanCount;
			if (count.contains("/")) {
				replyCount=count.split("/")[0];
				scanCount=count.split("/")[1];
				Count countObj = new Count();
				countObj.setReplyCount(replyCount);
				countObj.setScanCount(scanCount);
				countObj.setTime(DateUtil.getFromatDate(selectTime, "yyyy-MM-dd HH:mm"));
				if(list.size()>0) {
					HupuPost hupuPost = list.get(0);
					hupuPost.getCounts().add(countObj);
					dataRepository.save(hupuPost);
					LOGGER.info("旧帖："+postTitle+"更新完成");
				}
			}
		}
		}
	}
	public static void saveNewList() {
		for (HupuPost hupuPost : hupuNewList) {
			dataRepository.save(hupuPost);
			LOGGER.info("保存新帖："+hupuPost.getPostTitle()+"   成功！");
		}
	}
	public static void main(String[] args) {
		getAllPost("");
	}
}
