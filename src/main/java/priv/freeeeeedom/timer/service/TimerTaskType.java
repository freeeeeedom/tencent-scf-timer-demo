package priv.freeeeeedom.timer.service;

public enum TimerTaskType {
    /**
     * 通过class全名加载
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    CLASS,
    /**
     * 通过bean加载
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    BEAN,
    /**
     * 通过http连接调用触发
     * @author: Nevernow
     * @Date: 2019/5/22 12:23
     */
    HTTP,
    /**
     * 通过方法调用触发
     * @author: Nevernow
     * @Date: 2019/5/22 15:27
     */
    FUNCTION
}
