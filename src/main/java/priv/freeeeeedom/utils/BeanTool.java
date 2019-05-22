package priv.freeeeeedom.utils;

import priv.freeeeeedom.TencentScfTimerDemoApplication;

public class BeanTool {
    public static Object getBean(String beanName) {
        return TencentScfTimerDemoApplication.context.getBean(beanName);
    }
}
