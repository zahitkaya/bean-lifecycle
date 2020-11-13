package com.example.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Beans implements InitializingBean, DisposableBean, BeanNameAware, ResourceLoaderAware,ApplicationContextAware
, ApplicationEventPublisherAware,MessageSourceAware, NotificationPublisherAware {


    //DisposibleBean'den geliyor. (destroy)
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean sonlandırılıyor. ");
    }
    //InitialingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean başlatıldı.. InitializingBean interface'inden implement edildi. ");
    }
    //Pre-Initialization
    @PostConstruct
    public void postConstruct(){
        System.out.println("Bean başlatılacak (postConstructor)");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Bean bitirilecek (preDestroy)..");
    }

    public void initBean(){
        System.out.println("Bean kurulumu tamamlandı (Custom initialization)");
    }
    public void destroyBean(){
        System.out.println("Bean yıkımı tamamlandı. (Custom destruction)");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        User user=(User) applicationContext.getBean("user");
        user.setName("Zahit");
        System.out.println("AWARE INTERFACE Application context : "+ user.getName());
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("Dosyamızın kaynağı :"+resourceLoader.getResource("com/sample.txt"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("AWARE INTERFACE applicationEventPublisher : "+applicationEventPublisher.toString());

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("AWARE INTERFACE messageSource :"+ messageSource.toString());
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        System.out.println("AWARE INTERFACE notificationPublisher :" +notificationPublisher.toString());
    }
    @Override
    public void setBeanName(String s) {
        System.out.println("AWARE INTERFACE Bean name: "+s);
    }
}
