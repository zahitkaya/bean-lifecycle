package com.example.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Beans implements InitializingBean, DisposableBean, BeanNameAware, ResourceLoaderAware, ApplicationContextAware
        , ApplicationEventPublisherAware, MessageSourceAware, NotificationPublisherAware, BeanPostProcessor {

    /*Instantiation Bean lifecycle da ilk adım bean manuel olarak oluşturuluyor.
    IOC XML configirationlarına veya beani tanımlayan anotasyonlara bakar bakar. Bu işlemden sonra beanin instance'tını
     oluşturur.1
     Bu işlemden sonra properties injectionla  Bean DI la inject edilir.

     */
    ///////////////////////////////////////////////////////
    @Override
    public void setBeanName(String s) {
        System.out.println("AWARE INTERFACE Bean name: " + s);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return "Before initialization of bean";
    }//2 bu metod bütün bean callbackleri için yeni instance oluşturur.

    //////////////////////////////////////////////////////////
    //Pre-Initialization
    @PostConstruct
    public void postConstruct() {
        System.out.println("Bean başlatılacak (postConstructor)");
    } //3

    //////////////////////////////////////////////////////////
    //InitialingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean başlatıldı.. InitializingBean interface'inden implement edildi. ");
    }//4

    //////////////////////////////////////////////////////////
    public void initBean() {
        System.out.println("Bean kurulumu tamamlandı (Custom initialization)");
    } //5

    //////////////////////////////////////////////////////////
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return "After initialization of bean";
    }//6 Bu işlemden sonra bean kullanıma hazır

    /*
    -------------------------------------------
     */

    @PreDestroy
    public void preDestroy() {
        System.out.println("Bean bitirilecek (preDestroy)..");
    }//7

    //////////////////////////////////////////////////////////
    //DisposibleBean'den geliyor. (destroy)
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean sonlandırılıyor. ");
    }//8 Bu metod bean referansına ihtiyaç kalınmadığında çağırılır.

    //////////////////////////////////////////////////////////
    public void destroyBean() {
        System.out.println("Bean yıkımı tamamlandı. (Custom destruction)");
    }//9


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        User user = (User) applicationContext.getBean("user");
        user.setName("Zahit");
        System.out.println("AWARE INTERFACE Application context : " + user.getName());
    }


    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("AWARE INTERFACE Dosyamızın kaynağı :" + resourceLoader.getResource("com/sample.txt"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("AWARE INTERFACE applicationEventPublisher : " + applicationEventPublisher.toString());

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("AWARE INTERFACE messageSource :" + messageSource.toString());
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        System.out.println("AWARE INTERFACE notificationPublisher :" + notificationPublisher.toString());
    }




}
