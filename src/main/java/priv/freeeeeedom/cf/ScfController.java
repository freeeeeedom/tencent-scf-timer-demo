package priv.freeeeeedom.cf;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.models.GetFunctionResponse;
import com.tencentcloudapi.scf.v20180416.models.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import priv.freeeeeedom.cf.request.ScfUpdater;
import priv.freeeeeedom.cf.request.data.CreateTriggerRequestPlus;
import priv.freeeeeedom.cf.request.data.DeleteTriggerRequestPlus;
import priv.freeeeeedom.cf.request.data.GetFunctionRequestPlus;
import priv.freeeeeedom.cf.request.data.ListFunctionsRequestPlus;
import priv.freeeeeedom.cf.service.ScfServiceImpl;
import priv.freeeeeedom.timer.data.InfResultVO;
import priv.freeeeeedom.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * scf触发器操作
 *
 * @author: Nevernow
 * @Date: 17:49 2019/5/23
 */
@CrossOrigin
@Controller
@RequestMapping("/scf")
public class ScfController {
    private static final String OPEN = "OPEN";
    private static final String CLOSE = "CLOSE";
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfController.class);

    @Autowired
    ScfServiceImpl service;

    /**
     * 查询函数列表
     *
     * @param: [nameSpace, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:50
     */
    @RequestMapping(value = "/functionList.mob", method = RequestMethod.GET)
    @ResponseBody
    public InfResultVO functionList(String nameSpace, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            resultVO.setResult(update.getFunctionList(new ListFunctionsRequestPlus(100)));
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 查询函数详情
     *
     * @param: [nameSpace, functionName, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @RequestMapping(value = "/functionDetail.mob", method = RequestMethod.GET)
    @ResponseBody
    public InfResultVO functionDetail(String nameSpace, String functionName,
                                      HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            resultVO.setResult(update.getFunctionDetail(new GetFunctionRequestPlus(functionName)));
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 查询函数触发器详情
     *
     * @param: [nameSpace, functionName, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @RequestMapping(value = "/functionTriggers.mob", method = RequestMethod.GET)
    @ResponseBody
    public InfResultVO functionTriggers(String nameSpace, String functionName,
                                        HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            List<Map<String, String>> result = service.getFunctionTriggers(nameSpace, functionName);
            resultVO.setResult(result);
        } catch (Exception e) {// 同步事件接口调用错误
            log.error("异常", e);
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 更新触发器
     *
     * @param: [param, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @RequestMapping(value = "/updateTrigger.mob", method = RequestMethod.POST)
    @ResponseBody
    public InfResultVO updateTrigger(@RequestBody Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            String nameSpace = (String) param.get("nameSpace");
            String functionName = (String) param.get("functionName");
            String triggerName = (String) param.get("triggerName");
            String triggerDesc = (String) param.get("triggerDesc");
            String enable = (String) param.get("enable");
            validateArgumentNull(nameSpace, functionName, triggerName, triggerDesc, enable);
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            update.updateTrigger(new CreateTriggerRequestPlus(functionName, triggerName, triggerDesc, enable));
            queryScfBuildResult(resultVO, functionName, triggerName, update);
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 刪除触发器
     *
     * @param: [param, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @RequestMapping(value = "/deleteTrigger.mob", method = RequestMethod.DELETE)
    @ResponseBody
    public InfResultVO deleteTrigger(@RequestBody Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            String nameSpace = (String) param.get("nameSpace");
            String functionName = (String) param.get("functionName");
            String triggerName = (String) param.get("triggerName");

            validateBaseArgument(nameSpace, functionName, triggerName);
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            update.delTrigger(new DeleteTriggerRequestPlus(nameSpace, functionName, triggerName));
            queryScfBuildResult(resultVO, functionName, triggerName, update);
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 创建触发器
     *
     * @param: [param, request, response]
     * @return: red.sea.outwork.mobileinterface.view.InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @RequestMapping(value = "/createTrigger.mob", method = RequestMethod.POST)
    @ResponseBody
    public InfResultVO createTrigger(@RequestBody Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            String nameSpace = (String) param.get("nameSpace");
            String functionName = (String) param.get("functionName");
            String enable = (String) param.get("enable");
            String triggerDesc = (String) param.get("triggerDesc");
            String triggerName = (String) param.get("triggerName");
            validateArgumentNull(nameSpace, functionName, triggerName, triggerDesc, enable);
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            update.createTrigger(new CreateTriggerRequestPlus(functionName, triggerName, triggerDesc, enable));
            //查询scf详情
            queryScfBuildResult(resultVO, functionName, triggerName, update);
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    private void queryScfBuildResult(InfResultVO resultVO, String functionName, String triggerName, ScfUpdater update) throws TencentCloudSDKException {
        //查询scf详情
        GetFunctionResponse res = update.getFunctionDetail(new GetFunctionRequestPlus(functionName));
        //获取触发器
        List<Trigger> triggers = Arrays.asList(res.getTriggers());
        resultVO.setResult(triggers.stream().filter(item -> item.getTriggerName().equals(triggerName)).collect(Collectors.toList()));
    }

    private void validateArgumentNull(String nameSpace, String functionName, String triggerName, String triggerDesc, String enable) {
        validateBaseArgument(nameSpace, functionName, triggerName);
        Assert.notNull(triggerDesc, "cron表达式为空!");
        Assert.notNull(enable, "是否开启为空!");
        if (!enable.equals(OPEN) && !enable.equals(CLOSE)) {
            throw new ValidationException("是否开启值不正确,应为字符串OPEN或CLOSE!!!");
        }
    }

    private void validateBaseArgument(String nameSpace, String functionName, String triggerName) {
        Assert.notNull(nameSpace, "命名空间为空!");
        Assert.notNull(functionName, "函数名为空!");
        Assert.notNull(triggerName, "触发器名称为空!");
    }
}
