package com.jxl.jcrawler.config;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by maybo on 17/12/4.
 */
@Component
public class SpringHolder implements ApplicationContextAware {

    private static Logger logger = Logger.getLogger(SpringHolder.class);

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (SpringHolder.applicationContext == null) {

            SpringHolder.applicationContext = applicationContext;

        }

        System.out.println("---------------------------------------------------------------------");

        System.out.println("---------------------------------------------------------------------");

        System.out.println("---------------com.kfit.base.util.SpringUtil------------------------------------------------------");

        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext=" + SpringHolder.applicationContext + "========");

        System.out.println("---------------------------------------------------------------------");

    }


    //获取applicationContext

    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }


    //通过name获取 Bean.

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }


    public static Object getExistBean(Class clazz, String beanName) {
        String[] beanNames = getApplicationContext().getBeanNamesForType(clazz);
        if (null != beanNames) {
            for (String bean : beanNames) {
                if (bean.equals(beanName)) {
                    return getApplicationContext().getBean(beanName);
                }
            }
        }
        return null;
    }

    //通过class获取Bean.

    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }

    //通过name,以及Clazz返回指定的Bean

    public static <T> T getBean(String name, Class<T> clazz) {

        return getApplicationContext().getBean(name, clazz);

    }

}