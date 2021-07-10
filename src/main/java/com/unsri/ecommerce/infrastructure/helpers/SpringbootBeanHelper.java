package com.unsri.ecommerce.infrastructure.helpers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringbootBeanHelper implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringbootBeanHelper.applicationContext == null) {
            SpringbootBeanHelper.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext configuration succeeded========");
        System.out.println("========In ordinary classes, the applicationContext object can be obtained by calling SpringBootBeanUtil.getApplicationContext() ========");
        System.out.println("========applicationContext="+ SpringbootBeanHelper.applicationContext +"========");
    }

    /**
     * Get applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Get Bean by name.
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * Obtain Bean through class.
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * Return the specified Bean by name and Clazz
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
