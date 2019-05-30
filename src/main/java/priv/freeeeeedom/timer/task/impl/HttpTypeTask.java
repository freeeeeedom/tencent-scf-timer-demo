package priv.freeeeeedom.timer.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.base.BaseTimer;
import priv.freeeeeedom.timer.task.ScfTimerStrategy;
import priv.freeeeeedom.utils.HttpUtils;
import priv.freeeeeedom.utils.ResultUtils;

import java.util.Map;

/**
 * http请求类型任务
 * @author: Nevernow
 * @Date: 16:04 2019/5/28
 */ 
public class HttpTypeTask extends ScfTimerStrategy implements BaseTimer {
    private static final String POST = "POST";
    private static final String GET = "GET";
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(HttpTypeTask.class);

    @Override
    public Object run(Map<String, Object> task) {
        try {
            return runTask(task.get(IS_ASYNC), this, task.get(TASK_VAL), task.get(TASK_PARAM));
        } catch (Exception e) {
            log.error("http任务执行异常!!" + task.get("TASK_NAME"), e);
            return "http任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }


    @Override
    public Object startTimer(Object... param) {
        log.info("执行http逻辑");
        String[] arr = String.valueOf(param[0]).split("@");
        String restfulType = arr[0];
        String url = arr[1];
        String sendParam = String.valueOf(param[1]);
        log.info(restfulType, url, sendParam);
        Map result = null;
        if (restfulType.equals(POST)) {
            result = HttpUtils.getInstance().sendHttpPost(url, sendParam);
        }
        if (restfulType.equals(GET)) {
            result = HttpUtils.getInstance().sendHttpGet(url);
        }
        return result;
    }
}
