package priv.freeeeeedom.timer.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.base.BaseTimerTask;
import priv.freeeeeedom.timer.task.ScfTimerStrategy;
import priv.freeeeeedom.utils.ResultUtils;

import java.util.Map;

public class ClassTypeTask extends ScfTimerStrategy {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ClassTypeTask.class);

    @Override
    public Object run(Map<String, Object> task) {
        try {
            BaseTimerTask run = (BaseTimerTask) (Class.forName(String.valueOf(task.get(TASK_VAL)))).newInstance();
            return runTask(task.get(IS_ASYNC), run);
        } catch (Exception e) {
            log.error("class任务执行异常!!" + task.get("TASK_NAME"), e);
            return "class任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }
}
