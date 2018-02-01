package com.jxl.jcrawler.util.mongo;

import com.jxl.jcrawler.enums.ContentTypes;
import com.jxl.jcrawler.util.common.JsonUtil;
import com.jxl.jcrawler.util.common.PropertiesUtil;
import com.jxl.jcrawler.util.common.StringUtil;
import com.jxl.jcrawler.util.http.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by amosli on 31/07/2017.
 */
public class MongoUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(MongoUtil.class);

    private static String domain;
    private static String dataUrl;
    private static String pagesUrl;
    public static String sendLog;
    public static String personUrl;
    //private static HttpHelper httpHelper = new SimpleHttpHelper();
    private static Integer MAX_RETRY = 3;

    static {
        load();
    }

    private static void load() {
        PropertiesUtil propertiesUtil = new PropertiesUtil("/config/mongo.properties");
        domain = propertiesUtil.getValue("domain");
        dataUrl = domain + propertiesUtil.getValue("dataUrl");
        pagesUrl = domain + propertiesUtil.getValue("pagesUrl");
        sendLog = domain + propertiesUtil.getValue("sendLog");
        personUrl = domain+propertiesUtil.getValue("personUrl");
    }

 /*   public static void saveParsedData(String siteType, DataMongoObject mongoObject) {
        save(siteType, mongoObject, mongoObject.getToken());
    }

    private static void save(String siteType, Object object, String token) {
        String url;

        if (object instanceof PagesMongoObject) {
            url = pagesUrl;
        } else if (object instanceof DataMongoObject) {
            url = dataUrl + "?siteType=" + siteType;
        } else {
            LOGGER.error("unknown MongoObject,token:" + token);
            return;
        }

        try {
            for (int i = 0; i < MAX_RETRY; i++) {
                httpHelper.config.setContentType(ContentTypes.JSON);
                String result = httpHelper.goPost(url, JsonUtil.toString(object));
                if (isSavedSuccess(result)) {
                    return;
                }
                LOGGER.error("save parsed data failed, retry times:" + i + ", token:" + token);
            }
            LOGGER.error("save parsed data failed,max retry:" + MAX_RETRY + ", token:" + token);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    public static void saveHtmlPages(PagesMongoObject mongoObject) {
        save("", mongoObject, mongoObject.getToken());
    }
    */

    private static boolean isSavedSuccess(String result) {
        if (StringUtil.isEmpty(result)) {
            LOGGER.error("save failed");
            return false;
        }
        LOGGER.error("save failed" + result);
        return false;
    }
    
    private static void saveKpuPerson(Object object) {
    	String url;
    }


}
