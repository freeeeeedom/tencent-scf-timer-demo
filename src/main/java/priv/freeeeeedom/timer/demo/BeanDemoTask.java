package priv.freeeeeedom.timer.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priv.freeeeeedom.timer.base.BaseTimerTask;

/**
 * 根据bean注入方式执行定时任务
 * @author: Nevernow
 * @Date: 16:40 2019/5/21
 */
@Component
public class BeanDemoTask extends BaseTimerTask {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(BeanDemoTask.class);

    @Override
    public Object runTask(Object... param) throws Exception {
        log.info("执行逻辑Bean...");
        return "success"+Thread.currentThread().getName();
    }
}
