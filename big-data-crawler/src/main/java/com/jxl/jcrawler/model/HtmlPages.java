package com.jxl.jcrawler.model;

import com.jxl.jcrawler.enums.HtmlPageTypes;
import com.jxl.jcrawler.util.common.StringUtil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by amosli on 14/07/2017.
 */
public class HtmlPages {
    ConcurrentHashMap<String, CopyOnWriteArrayList<String>> pages = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        HtmlPages pages = new HtmlPages();
        pages.add(HtmlPageTypes.bill, "201701");
        pages.add(HtmlPageTypes.bill, "201702");
        pages.add(HtmlPageTypes.bill, "201703");
        pages.add(HtmlPageTypes.bill, "201704");

        pages.add(HtmlPageTypes.sms, "201704");
        pages.add(HtmlPageTypes.sms, "201703");

        System.out.println(pages);
    }

    public void add(HtmlPageTypes pageTypes, String html) {
        String name = pageTypes.name();
        CopyOnWriteArrayList<String> pages = this.pages.get(name);
        if (StringUtil.isEmpty(pages)) {
            pages = new CopyOnWriteArrayList<>();
        }
        pages.add(html);
        this.pages.put(name, pages);
    }

    public ConcurrentHashMap<String, CopyOnWriteArrayList<String>> getPages() {
        return pages;
    }

    public void setPages(ConcurrentHashMap<String, CopyOnWriteArrayList<String>> pages) {
        this.pages = pages;
    }
}
