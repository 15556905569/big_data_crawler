package com.jxl.jcrawler.util.common;

import com.jxl.jcrawler.model.HtmlPages;
import com.jxl.jcrawler.model.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by amosli on 11/07/2017.
 */
public class FileUtil extends FileUtils {
    public static final String DIR = "htmls";
    public static final String IMAGE = "image";
    public static final String IMAGE_SUFFIX = ".jpg";
    public static final String PAGES = "pages";
    public static final String PAGES_SUFFIX = ".html";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "MM";
    public static final String DAY_FORMAT = "dd";

    private static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static String getUserWorkingPath() {
        return System.getProperty("user.dir");
    }

    public static void asyncWrite(final String fileName, String str) {
        new Thread(() -> {
            try {
                write(new File(fileName), str);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }).run();
    }

    public static void asyncWrite(final String fileName, String str, boolean flag) {
        new Thread(() -> {
            try {
                write(new File(fileName), str, flag);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }).run();
    }

    public static void asyncWriteBytes(final String fileName, byte[] bytes) {
        new Thread(() -> {
            try {
                writeByteArrayToFile(new File(fileName), bytes);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }).run();
    }

    /**
     * 写网页请求,日期命名
     *
     * @param bytes
     */
    public static void asyncWritePages(User user, byte[] bytes) {
        String fileName = DIR + File.separator
                + PAGES + File.separator
                + DateUtil.getFormatDate(YEAR_FORMAT) + File.separator
                + DateUtil.getFormatDate(MONTH_FORMAT) + File.separator
                + DateUtil.getFormatDate(DAY_FORMAT) + File.separator
                + user.getWebsite() + File.separator
                + user.getToken() + DateUtil.getTime() + RandomUtil.generateSpecifyDigits(6) + PAGES_SUFFIX;
        asyncWriteBytes(fileName, bytes);
    }

    /**
     * 写原始网页,要解析的见面数据
     *
     * @param htmlPages
     * @param website
     * @param token
     */
    public static void asyncWriteHtmlPages(HtmlPages htmlPages, String website, String token) {
        String path = DIR + File.separator
                + PAGES + File.separator
                + DateUtil.getFormatDate(YEAR_FORMAT) + File.separator
                + DateUtil.getFormatDate(MONTH_FORMAT) + File.separator
                + DateUtil.getFormatDate(DAY_FORMAT) + File.separator
                + website + File.separator
                + token + File.separator;
        ConcurrentHashMap<String, CopyOnWriteArrayList<String>> pages = htmlPages.getPages();
        pages.forEach((k, v) -> {
            for (int i = 0; i < v.size(); i++) {
                String fileName = path + k + "." + (i + 1) + PAGES_SUFFIX;
                asyncWrite(fileName, v.get(i));
            }
        });
    }


    public static void asyncWriteImages(byte[] bytes) {
        String fileName = DIR + File.separator
                + IMAGE + File.separator
                + DateUtil.getFormatDate(YEAR_FORMAT) + File.separator
                + DateUtil.getFormatDate(MONTH_FORMAT) + File.separator
                + DateUtil.getFormatDate(DAY_FORMAT) + File.separator
                + DateUtil.getTime() + RandomUtil.generateSpecifyDigits(6) + IMAGE_SUFFIX;
        asyncWriteBytes(fileName, bytes);
    }

    public static void main(String[] args) {
    }

}
