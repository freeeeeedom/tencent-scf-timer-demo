package priv.freeeeeedom.utils;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import priv.freeeeeedom.TencentScfTimerDemoApplication;

public class BeanTool {
    public static Object getBean(String beanName) {
        return TencentScfTimerDemoApplication.context.getBean(beanName);
    }

    public static Object getBean(Class clz) {
        return TencentScfTimerDemoApplication.context.getBean(clz);
    }

    public static Object setBean(Object object) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) TencentScfTimerDemoApplication.context.getParentBeanFactory();
        if (beanFactory == null) {
            beanFactory = (DefaultListableBeanFactory) TencentScfTimerDemoApplication.context.getAutowireCapableBeanFactory();
        }
        //根据obj的类型、创建一个新的bean、添加到Spring容器中，
        //注意BeanDefinition有不同的实现类，注意不同实现类应用的场景
        String beanName = Character.toLowerCase(object.getClass().getSimpleName().charAt(0)) + object.getClass().getSimpleName().substring(1);
        BeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClassName(object.getClass().getName());
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
        //让obj完成Spring初始化过程中所有增强器检验，只是不重新创建obj
        TencentScfTimerDemoApplication.context.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(object, object.getClass().getName());
        //将obj以单例的形式入驻到容器中，此时通过obj.getClass().getName()或obj.getClass()都可以拿到放入Spring容器的Bean
        beanFactory.registerSingleton(beanName, object);
        return object.getClass().getName();
    }
}
