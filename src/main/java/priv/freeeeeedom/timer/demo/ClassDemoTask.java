package priv.freeeeeedom.timer.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.base.BaseTimerTask;

/**
 * 根据class加载的方式执行任务
 *
 * @author: Nevernow
 * @Date: 16:40 2019/5/21
 */
public class ClassDemoTask extends BaseTimerTask {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ClassDemoTask.class);

    @Override
    public Object runTask(Object... param) throws Exception {
        log.info("执行逻辑class...");
        return "success";
    }
}
