package priv.freeeeeedom.timer.task.impl;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.task.ScfTimerStrategy;
import priv.freeeeeedom.utils.CfThreadPool;
import priv.freeeeeedom.utils.ResultUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FunctionTypeTask extends ScfTimerStrategy {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(FunctionTypeTask.class);
    @Override
    public Object run(Map<String, Object> task) {
        if (YES.equals(task.get(IS_ASYNC))) {
            CfThreadPool.THREAD_POOL.getTimerPool().execute(() -> runFunction(task));
            return IS_ASYNC_TEXT;
        } else {
            return runFunction(task);
        }
    }

    private Object runFunction(Map<String, Object> task) {
        String classPath = String.valueOf(task.get(TASK_VAL));
        String[] function = String.valueOf(task.get(TASK_FUNCTION)).split("@");
        String functionName = function[0];
        List<Class<?>> paramTypes = new LinkedList<>();
        try {
            for (int i = 1; i < function.length; i++) {
                paramTypes.add(Class.forName(function[i]));
            }
            Class[] classes = new Class[paramTypes.size()];
            paramTypes.toArray(classes);
            Class cls = Class.forName(classPath);
            Object object = cls.newInstance();

            Method method = cls.getMethod(functionName, classes);
            List list = new ArrayList();
            for (int i = 0; i < classes.length; i++) {
                list.add(new Gson().fromJson(String.valueOf(task.get(TASK_PARAM)).split("@")[i], classes[i]));
            }
            return method.invoke(object, list.toArray());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error("任意方法定时器执行异常!" + e);
            return ResultUtils.getExceptionLineSize(e, 7);
        }
    }
}
