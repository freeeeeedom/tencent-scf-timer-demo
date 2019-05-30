package priv.freeeeeedom.timer.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.base.BaseTimerTask;
import priv.freeeeeedom.timer.task.ScfTimerStrategy;
import priv.freeeeeedom.utils.BeanTool;
import priv.freeeeeedom.utils.ResultUtils;

import java.util.Map;

public class BeanTypeTask extends ScfTimerStrategy {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(BeanTypeTask.class);

    @Override
    public Object run(Map<String, Object> task) {
        try {
            BaseTimerTask run = (BaseTimerTask) BeanTool.getBean(String.valueOf(task.get(TASK_VAL)));
            return runTask(task.get(IS_ASYNC), run);
        } catch (Exception e) {
            log.error("bean任务执行异常!!" + task.get("TASK_NAME"), e);
            return "bean任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }
}
