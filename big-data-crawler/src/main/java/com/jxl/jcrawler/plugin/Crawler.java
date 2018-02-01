package com.jxl.jcrawler.plugin;

import com.jxl.jcrawler.model.HtmlPages;
import com.jxl.jcrawler.util.http.DefaultHttpHelper;
import com.jxl.jcrawler.util.http.HttpHelper;

/**
 * Created by amosli on 11/07/2017.
 */
public abstract class Crawler {
    protected HttpHelper httpHelper;

    public HtmlPages getPages() {
        return httpHelper.pages;
    }

    public HttpHelper getHttpHelper() {
        return httpHelper;
    }

    /**
     * @param user
     * @param httpHelper
     */
    public void setProperties(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    public void setProperties() {
        setProperties(new DefaultHttpHelper());
    }
    
}

