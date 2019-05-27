package priv.freeeeeedom.timer.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.freeeeeedom.timer.base.BaseTimerTask;
import priv.freeeeeedom.timer.base.HttpTimerTask;
import priv.freeeeeedom.timer.dao.ScfTimerTaskDao;
import priv.freeeeeedom.timer.data.ScfTimerDTO;
import priv.freeeeeedom.utils.BeanTool;
import priv.freeeeeedom.utils.CfThreadPool;
import priv.freeeeeedom.utils.ResultUtils;

import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nevernow
 */
@Service
public class ScfTimerService {
    public static final String TASK_TYPE = "TASK_TYPE";
    public static final String TASK_NAME = "TASK_NAME";
    private static final String TASK_VAL = "TASK_VAL";
    private static final String TASK_PARAM = "TASK_PARAM";
    private static final String TASK_FUNCTION = "TASK_FUNCTION";
    private static final String IS_ASYNC = "IS_ASYNC";
    private static final String YES = "1";
    private static final String NO = "0";
    private static final String IS_ASYNC_TEXT = "该任务为异步";

    @Autowired
    ScfTimerTaskDao dao;

    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfTimerService.class);

    /**
     * 获取定时器任务
     *
     * @param: [dto]
     * @return: java.util.List<java.util.Map>
     * @author: Nevernow
     * @Date: 2019/5/27 9:50
     */
    public List<Map<String, Object>> getTimerTask(ScfTimerDTO dto) {
        List<Map<String, Object>> tasks = dao.getTimerTask(dto);
        if (tasks == null) {
            log.info("没有要执行的任务!");
            throw new ValidationException("没有要执行的任务!");
        }
        log.info("此次执行任务:" + tasks.parallelStream().map(task -> task.get(TASK_NAME)).collect(Collectors.toList()));

        List asyncTasks = tasks.stream().filter(task -> YES.equals(task.get(IS_ASYNC))).map(task -> task.get(TASK_NAME)).collect(Collectors.toList());
        List syncTasks = tasks.stream().filter(task -> NO.equals(task.get(IS_ASYNC))).map(task -> task.get(TASK_NAME)).collect(Collectors.toList());
        log.info("！！！！！！！同步任务列表:" + syncTasks);
        log.info("！！！！！！！异步任务列表:" + asyncTasks);
        return dao.getTimerTask(dto);
    }

    /**
     * 执行任意函数类型任务
     *
     * @param: [tasks 任务列表, result 同步执行返回值]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/27 9:50
     */
    public Object functionTypeTask(Map<String, Object> task) {
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

    /**
     * http类型任务执行
     *
     * @param: [tasks 任务列表, result 同步执行返回值]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/27 9:51
     */
    public Object httpTypeTask(Map<String, Object> task) {
        try {
            if (YES.equals(task.get(IS_ASYNC))) {
                CfThreadPool.THREAD_POOL.getTimerPool().execute(() -> HttpTimerTask.TASK.startTimer(task.get(TASK_VAL), task.get(TASK_PARAM)));
                return IS_ASYNC_TEXT;
            } else {
                return HttpTimerTask.TASK.startTimer(task.get(TASK_VAL), task.get(TASK_PARAM));
            }
        } catch (Exception e) {
            log.error("http任务执行异常!!" + task.get("TASK_NAME"), e);
            return "http任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }

    /**
     * beanTypeTask
     *
     * @param: [tasks, result]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/27 9:52
     */
    public Object beanTypeTask(Map<String, Object> task) {
        try {
            BaseTimerTask run = (BaseTimerTask) BeanTool.getBean(String.valueOf(task.get(TASK_VAL)));
            return runTask(task, run);
        } catch (Exception e) {
            log.error("bean任务执行异常!!" + task.get("TASK_NAME"), e);
            return "bean任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }

    /**
     * classTypeTask
     *
     * @param: [task]
     * @return: java.lang.Object
     * @author: Nevernow
     * @Date: 2019/5/27 10:05
     */
    public Object classTypeTask(Map<String, Object> task) {
        try {
            BaseTimerTask run = (BaseTimerTask) (Class.forName(String.valueOf(task.get(TASK_VAL)))).newInstance();
            return runTask(task, run);
        } catch (Exception e) {
            log.error("class任务执行异常!!" + task.get("TASK_NAME"), e);
            return "class任务执行异常!!" + task.get("TASK_NAME") + " " + ResultUtils.getExceptionLineSize(e, 7);
        }
    }

    private Object runTask(Map<String, Object> task, BaseTimerTask run) {
        if (YES.equals(task.get(IS_ASYNC))) {
            CfThreadPool.THREAD_POOL.getTimerPool().execute(run::startTimer);
            return IS_ASYNC_TEXT;
        } else {
            return run.startTimer();
        }
    }
}
