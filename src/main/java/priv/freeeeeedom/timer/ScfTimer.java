package priv.freeeeeedom.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.freeeeeedom.timer.data.ScfTimerDTO;
import priv.freeeeeedom.timer.service.ScfTimerService;
import priv.freeeeeedom.timer.task.ScfTimerStrategy;
import priv.freeeeeedom.timer.task.TimerTaskFactory;
import priv.freeeeeedom.utils.ResultUtils;
import priv.freeeeeedom.utils.data.InfResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static priv.freeeeeedom.timer.task.ScfTimerStrategy.*;

/**
 * 定时任务执行Controller
 *
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
    ScfTimerService scfTimerService;

    /**
     * 定时任务调用方法
     *
     * @param: [dto, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/22 16:26
     */
    @RequestMapping("/runTimer.mob")
    @ResponseBody
    public InfResultVO runTimer(@RequestBody ScfTimerDTO dto, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            log.info("-----------------------定时器执行开始-----------------------");
            log.info(dto.toString());
            List<Map<String, Object>> tasks = scfTimerService.getTimerTask(dto)
                    .parallelStream()
                    .filter(item -> YES.equals(item.get(IN_USE))).collect(Collectors.toList());

            Map result = new ConcurrentHashMap((int) ((tasks.size() * 1.25) + 1));
            //并行流执行
            tasks.parallelStream().forEach(task -> {
                Object taskType = task.get(TASK_TYPE);
                Object taskName = task.get(TASK_NAME);
                try {
                    ScfTimerStrategy strategy = TimerTaskFactory.getStrategy(taskType);
                    assert strategy != null;
                    Object res= strategy.execute(task);
                    if (res == null) {
                        result.put(taskName, "返回结果为空");
                    } else {
                        result.put(taskName, res);
                    }
                } catch (Exception e) {
                    log.error(taskName + "任务执行异常", e);
                    result.put(taskName, ResultUtils.getExceptionLineSize(e, 7));
                }
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