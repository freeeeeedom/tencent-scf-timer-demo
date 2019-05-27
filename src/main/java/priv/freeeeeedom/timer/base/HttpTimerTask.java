package priv.freeeeeedom.timer.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.utils.HttpUtils;
import priv.freeeeeedom.utils.ResultUtils;

import java.util.Map;

public class HttpTimerTask implements BaseTimer {

    private static final String POST = "POST";
    private static final String GET = "GET";
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(HttpTimerTask.class);
    public final static HttpTimerTask TASK = new HttpTimerTask();

    /**
     * 执行任务核心逻辑
     *
     * @param: []
     * @return: T
     * @author: Nevernow
     * @Date: 2019/5/21 17:11
     */
    protected Object runTask(Object... param) throws Exception {
        log.info("执行http逻辑");
        String[] arr = String.valueOf(param[0]).split("@");
        String restfulType = arr[0];
        String url = arr[1];
        String sendParam = String.valueOf(param[1]);
        log.info(restfulType,url,sendParam);
        Map result = null;
        if (restfulType.equals(POST)) {
            result = HttpUtils.getInstance().sendHttpPost(url, sendParam);
        }
        if (restfulType.equals(GET)) {
            result = HttpUtils.getInstance().sendHttpGet(url);
        }
        return result;
    }

    @Override
    public Object startTimer(Object... param) {
        try {
            return this.runTask(param);
        } catch (Exception e) {
            log.error("定时任务执行异常!", e);
            return ResultUtils.getExceptionLineSize(e, 7);
        }
    }

    @Override
    public Object startTimer() {
        return null;
    }
}
