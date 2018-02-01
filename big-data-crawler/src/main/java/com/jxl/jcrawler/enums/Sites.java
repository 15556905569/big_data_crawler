package com.jxl.jcrawler.enums;

import com.jxl.jcrawler.util.common.StringUtil;

/**
 * Created by amosli on 11/07/2017.
 */
public enum Sites {
	//MOBILE
	AIS("ais", "ais", SitesType.MOBILE),
	CHINA_UNICOM("chinaunicom", "中国联通", SitesType.MOBILE),
	MOBIFONE("mobifone", "mobifone", SitesType.MOBILE),
	VIETTEL("viettel", "viettel", SitesType.MOBILE),
	VINAPHONE("vinaphone", "vinaphone", SitesType.MOBILE),
	TRICOID("tricoid","tricoid",SitesType.MOBILE),
    XL("xl","xl",SitesType.MOBILE),

	BIMAPLUS("bimaplus","bimaplus",SitesType.MOBILE),
	TELKOMSEL("telkomsel","telkomsel",SitesType.MOBILE),

	//E_BUSINESS
	LAZADAVN("lazadavn", "lazadavn", SitesType.E_BUSINESS),
	QOO10("qoo10", "qoo10", SitesType.E_BUSINESS),
	LAZADA("lazada", "lazada", SitesType.E_BUSINESS),
	ZALORA("zalora", "zalora", SitesType.E_BUSINESS), 
	LAZADAID("lazadaid", "lazadaid", SitesType.E_BUSINESS),
	TOKOPEDIA("tokopedia", "tokopedia", SitesType.E_BUSINESS),
	BUKALAPAK("bukalapak", "bukalapak", SitesType.E_BUSINESS),
	SHOPEE("shopee", "shopee", SitesType.E_BUSINESS),
    TIKI("tiki","tiki",SitesType.E_BUSINESS),
	
	//MUSIC
	NETEASEMUSIC("neteasemusic", "neteasemusic", SitesType.MUSIC),
	QQMUSIC("qqmusic", "qqmusic", SitesType.MUSIC),
    XIAMIMUSIC("xiamimusic", "xiamimusic", SitesType.MUSIC),
    
    //SOCIAL
    ZALO("zalo", "zalo", SitesType.SOCIAL),
    YOGRT("yogrt", "yogrt", SitesType.SOCIAL),
    WAHTSAPP("whatsapp", "whatsapp", SitesType.SOCIAL),
    
    //TAKEOUT
    MEITUAN("meituan", "meituan", SitesType.TAKEOUT),
    ELE("ele", "ele", SitesType.TAKEOUT),
    DIANPING("dianping", "dianping", SitesType.TAKEOUT),
    
    //TAXI
    GOJEK("gojek", "gojek", SitesType.TAXI),
    GRABSDK("grabsdk", "grabsdk", SitesType.TAXI),
    GRAB("grab", "grab", SitesType.TAXI),
    
    //BANK
    VIETINBANK("vietinbank", "vietinbank", SitesType.BANK),
    SCBBANK("scbbank", "scbbank", SitesType.BANK),
    BRIBANK("bribank", "bribank", SitesType.BANK),
    MANDIRIBANK("mandiribank", "mandiribank", SitesType.BANK),
    VIETCMONBANK("vietcombank", "vietcombank", SitesType.BANK),
    ACBBANK("acbbank","acbban",SitesType.BANK),
    TECHCOMBANK("techcombank","techcombank",SitesType.BANK),
    BCABANK("bcabank","bcabank",SitesType.BANK),
    
    //UTILITIES
    EVNHCMC("evnhcmc", "evnhcmc", SitesType.UTILITIES),
    EVNHANOI("evnhanoi", "evnhanoi", SitesType.UTILITIES),
    
    //WEIBO
    SINAWEIBO("sinaweibo", "sinaweibo", SitesType.WEIBO),
    
    //TRIP
    BAIDUMAP("baidumap", "baidumap", SitesType.TRIP),
    
    //JOB
    JOBSTREET("jobstreet", "jobstreet", SitesType.JOB),
    
    //SOCIAL_RESUME
    LINKEDIN("linkedin", "linkedin", SitesType.SOCIAL_RESUME),
    
    //FACEBOOK
    FACEBOOK("facebook", "facebook", SitesType.FACEBOOK),
    
    //INTERLOCUTION
    ZHIHU("zhihu", "zhihu", SitesType.INTERLOCUTION),
    
    //PAYMENT
    DOKU("doku", "doku", SitesType.PAYMENT),
    
    //VIDEO
    YOUKU("youku", "youku", SitesType.VIDEO),
    
    //LIFE
    GOLIFE("golife", "golife", SitesType.LIFE),
    
    
    ;


    private String name;
    private String desc;
    private SitesType type;

    Sites(String name, String desc, SitesType type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public static Sites value(String name) {
        if (StringUtil.isEmpty(name)) {
            return null;
        }

        Sites[] values = values();
        for (Sites s : values) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public SitesType getType() {
        return type;
    }
}



