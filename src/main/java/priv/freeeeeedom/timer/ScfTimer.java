package priv.freeeeeedom.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.freeeeeedom.timer.data.InfResultVO;
import priv.freeeeeedom.timer.data.ScfTimerDTO;
import priv.freeeeeedom.timer.data.TimerTaskType;
import priv.freeeeeedom.timer.service.ScfTimerService;
import priv.freeeeeedom.utils.BeanSetTest;
import priv.freeeeeedom.utils.BeanTool;
import priv.freeeeeedom.utils.ResultUtils;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static priv.freeeeeedom.timer.service.ScfTimerService.TASK_NAME;
import static priv.freeeeeedom.timer.service.ScfTimerService.TASK_TYPE;

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
     * 远程定时器调用该方法发起任务
     *
     * @param: [dto]
     * @return: priv.freeeeeedom.timer.data.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/22 10:31
     */
    @CrossOrigin
    @RequestMapping("/runTimer.mob")
    @ResponseBody
    public InfResultVO runTimer(@RequestBody ScfTimerDTO dto) {
        InfResultVO resultVO = new InfResultVO();
        try {
            log.info("-----------------------定时器执行开始-----------------------");
            log.info(dto.toString());
            List<Map<String, Object>> tasks = scfTimerService.getTimerTask(dto);

            Map result = new ConcurrentHashMap((int) ((tasks.size() * 1.25) + 1));
            //并行流执行
            tasks.parallelStream().forEach(task -> {
                Object taskType = task.get(TASK_TYPE);
                Object taskName = task.get(TASK_NAME);
                //Class类型任务
                if (TimerTaskType.CLASS.name().equals(taskType)) {
                    result.put(taskName, scfTimerService.classTypeTask(task));
                }
                //Bean类型任务
                if (TimerTaskType.BEAN.name().equals(taskType)) {
                    result.put(taskName, scfTimerService.beanTypeTask(task));
                }
                //Http类型任务
                if (TimerTaskType.HTTP.name().equals(taskType)) {
                    result.put(taskName, scfTimerService.httpTypeTask(task));
                }
                //Function类型任务
                if (TimerTaskType.FUNCTION.name().equals(taskType)) {
                    result.put(taskName, scfTimerService.functionTypeTask(task));
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

    @RequestMapping("/setBeanTest.mob")
    @ResponseBody
    public Object setBeanTest() {
        BeanSetTest test = new BeanSetTest();
        try {
            BeanTool.setBean(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((BeanSetTest) BeanTool.getBean("beanSetTest")).sayYes();
        return test.getClass().getName();
    }
}


