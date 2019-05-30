package priv.freeeeeedom.timer.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.utils.ResultUtils;

/**
 * 定时任务抽象类
 *
 * @author: Nevernow
 * @Date: 17:09 2019/5/21
 */
public abstract class BaseTimerTask<T> implements BaseTimer {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(BaseTimerTask.class);

    /**
     * 执行任务核心逻辑
     *
     * @param: []
     * @return: T
     * @author: Nevernow
     * @Date: 2019/5/21 17:11
     */
    protected abstract T runTask(Object... param) throws Exception;

    @Override
    public Object startTimer(Object... param) {
        try {
            return this.runTask(param);
        } catch (Exception e) {
            log.error("定时任务执行异常!", e);
            return ResultUtils.getExceptionLineSize(e, 7);
        }
    }

}
