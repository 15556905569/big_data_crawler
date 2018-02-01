package com.jxl.jcrawler.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SitesInfo {
    Sites value();

    ProxyStrategy proxy() default ProxyStrategy.NONE;
}