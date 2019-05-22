package priv.freeeeeedom.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.freeeeeedom.timer.base.BaseTimerTask;
import priv.freeeeeedom.timer.dao.ScfTimerTaskDao;
import priv.freeeeeedom.timer.data.InfResultVO;
import priv.freeeeeedom.timer.data.ScfTimerDTO;
import priv.freeeeeedom.timer.data.TimerTaskType;
import priv.freeeeeedom.utils.BeanTool;
import priv.freeeeeedom.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 定时任务执行Controller
 * @author: Nevernow
 * @Date: 17:18 2019/5/21
 */
@Controller
@RequestMapping("/scfTimer")
public class ScfTimer {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfTimer.class);

    @Autowired
    ScfTimerTaskDao dao;

    /**
     * 远程定时器调用该方法发起任务
     * @param: [dto]
     * @return: priv.freeeeeedom.timer.data.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/22 10:31
     */
    @RequestMapping("/runTimer.mob")
    @ResponseBody
    public InfResultVO runTimer(@RequestBody ScfTimerDTO dto) {
        InfResultVO resultVO = new InfResultVO();
        try {
            log.info("-----------------------定时器执行开始-----------------------");
            log.info(dto.toString());
            List<Map<String, Object>> tasks = dao.getTimerTask(dto);
            if (tasks == null) {
                log.info("没有要执行的任务!");
                return resultVO;
            }
            log.info("此次执行任务:" + tasks.parallelStream().map(task -> task.get("TASK_NAME")).collect(Collectors.toList()));
            Map result = new ConcurrentHashMap((int) ((tasks.size() * 1.25) + 1));
            //Class类型任务
            tasks.parallelStream().filter(task -> TimerTaskType.CLASS.getVal().equals(task.get("TASK_TYPE"))).forEach(task -> {
                try {
                    result.put(task.get("TASK_NAME"), ((BaseTimerTask) (Class.forName(String.valueOf(task.get("TASK_VAL")))).newInstance()).startTimer());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    log.error("任务执行异常!!" + task.get("TASK_NAME"), e);
                }
            });
            //Bean类型任务
            tasks.parallelStream().filter(task -> TimerTaskType.BEAN.getVal().equals(task.get("TASK_TYPE"))).forEach(task -> {
                BaseTimerTask run = (BaseTimerTask) BeanTool.getBean(String.valueOf(task.get("TASK_VAL")));
                result.put(task.get("TASK_NAME"), run.startTimer());
            });
            resultVO.setResult(result);
            log.info("-----------------------定时器执行正常结束-----------------------");
        } catch (ValidationException e) {// 校验错误
            resultVO.setError(e.getMessage());
        } catch (Exception e) {// 同步事件接口调用错误
            log.error("10000", e);
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }
}
