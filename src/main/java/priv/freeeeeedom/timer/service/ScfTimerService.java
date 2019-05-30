package priv.freeeeeedom.timer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import priv.freeeeeedom.timer.dao.ScfTimerTaskDao;
import priv.freeeeeedom.timer.data.ScfTimerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static priv.freeeeeedom.timer.task.ScfTimerStrategy.*;

/**
 * @author Nevernow
 */
@Service
public class ScfTimerService {
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
        if (CollectionUtils.isEmpty(tasks)) {
            log.info("没有要执行的任务!");
            return new ArrayList();
        }
        log.info("此次执行任务:" + tasks.parallelStream().map(task -> task.get(TASK_NAME)).collect(Collectors.toList()));

        List asyncTasks = tasks.stream().filter(task -> YES.equals(task.get(IS_ASYNC)) && YES.equals(task.get(IN_USE))).map(task -> task.get(TASK_NAME)).collect(Collectors.toList());
        List syncTasks = tasks.stream().filter(task -> NO.equals(task.get(IS_ASYNC)) && YES.equals(task.get(IN_USE))).map(task -> task.get(TASK_NAME)).collect(Collectors.toList());
        log.info("！！！！！！！同步任务列表:" + syncTasks);
        log.info("！！！！！！！异步任务列表:" + asyncTasks);
        return tasks;
    }

    public void updateInUseTimerTask(String inUse, String taskId) {
        dao.updateInUseTimerTask(inUse, taskId);
    }
    public void updateIsAsyncTimerTask(String inUse, String taskId) {
        dao.updateIsAsyncTimerTask(inUse, taskId);
    }
}
