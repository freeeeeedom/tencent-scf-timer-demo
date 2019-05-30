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
import priv.freeeeeedom.utils.ResultUtils;
import priv.freeeeeedom.utils.data.InfResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * 定时任务执行Controller
 *
 * @author: Nevernow
 * @Date: 17:18 2019/5/21
 */
@Controller
@RequestMapping("/scfTimerBind")
public class ScfTimerBind {

    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfTimerBind.class);


    @Autowired
    ScfTimerService scfTimerService;

    /**
     * 根据scf信息获取对应触发器下绑定的任务
     *
     * @param: [dto, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/22 16:26
     */
    @RequestMapping("/getTimerTasks.mob")
    @ResponseBody
    public InfResultVO getTimerTasks(@RequestBody ScfTimerDTO dto, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            resultVO.setResult(scfTimerService.getTimerTask(dto));
        } catch (ValidationException e) {// 校验错误
            resultVO.setError(e.getMessage());
        } catch (Exception e) {// 同步事件接口调用错误
            log.error("10000", e);
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    @RequestMapping("/updateInUseTimerTask.mob")
    @ResponseBody
    public InfResultVO updateInUseTimerTask(String inUse, String taskId, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            scfTimerService.updateInUseTimerTask(inUse, taskId);
        } catch (ValidationException e) {// 校验错误
            resultVO.setError(e.getMessage());
        } catch (Exception e) {// 同步事件接口调用错误
            log.error("10000", e);
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }
    @RequestMapping("/updateIsAsyncTimerTask.mob")
    @ResponseBody
    public InfResultVO updateIsAsyncTimerTask(String isAsync, String taskId, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            scfTimerService.updateIsAsyncTimerTask(isAsync, taskId);
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
