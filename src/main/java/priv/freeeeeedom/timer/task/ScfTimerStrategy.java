package priv.freeeeeedom.timer.task;

import priv.freeeeeedom.timer.base.BaseTimer;
import priv.freeeeeedom.utils.CfThreadPool;

import javax.validation.ValidationException;
import java.util.Map;

/**
 * @author Nevernow
 */
public abstract class ScfTimerStrategy {
    public static final String TASK_TYPE = "TASK_TYPE";
    public static final String TASK_NAME = "TASK_NAME";
    public static final String TASK_VAL = "TASK_VAL";
    public static final String TASK_PARAM = "TASK_PARAM";
    public static final String TASK_FUNCTION = "TASK_FUNCTION";
    public static final String IS_ASYNC = "IS_ASYNC";
    public static final String YES = "1";
    public static final String NO = "0";
    public static final String IS_ASYNC_TEXT = "该任务为异步";
    public static final String IN_USE = "IN_USE";

    private boolean isRun = false;

    public abstract Object run(Map<String, Object> task);

    protected Object runTask(Object isASync, BaseTimer run, Object... param) {
        if (YES.equals(isASync)) {
            CfThreadPool.THREAD_POOL.getTimerPool().execute(() -> run.startTimer(param));
            return IS_ASYNC_TEXT;
        } else {
            return run.startTimer(param);
        }
    }

    public Object execute(Map<String, Object> task) throws Exception {
        if (isRunning()) {
            throw new ValidationException("任务正在执行中");
        }
        System.out.println(isRun);
        try {
            isRun = true;
            return run(task);
        } finally {
            isRun = false;
        }
    }

    public boolean isRunning() {
        return isRun;
    }
}
