package priv.freeeeeedom.timer.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.task.impl.BeanTypeTask;
import priv.freeeeeedom.timer.task.impl.ClassTypeTask;
import priv.freeeeeedom.timer.task.impl.FunctionTypeTask;
import priv.freeeeeedom.timer.task.impl.HttpTypeTask;

/**
 * 定时任务执行类型枚举类
 *
 * @author: Nevernow
 * @Date: 14:48 2019/5/27
 */
public enum TimerTaskFactory {
    /**
     * 通过class全名加载
     *
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    CLASS(ClassTypeTask.class),
    /**
     * 通过bean加载
     *
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    BEAN(BeanTypeTask.class),
    /**
     * 通过http连接调用触发
     *
     * @author: Nevernow
     * @Date: 2019/5/22 12:23
     */
    HTTP(HttpTypeTask.class),
    /**
     * 通过方法调用触发
     *
     * @author: Nevernow
     * @Date: 2019/5/22 15:27
     */
    FUNCTION(FunctionTypeTask.class);
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(TimerTaskFactory.class);
    Class<? extends ScfTimerStrategy> strategy;

    TimerTaskFactory(Class<? extends ScfTimerStrategy> clz) {
        this.strategy = clz;
    }

    public static ScfTimerStrategy getStrategy(Object taskType) {
        for (TimerTaskFactory value : values()) {
            if (value.name().equals(taskType)) {
                try {
                    return value.strategy.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    log.error("获取异常", e);
                }
            }
        }
        return null;
    }
}
