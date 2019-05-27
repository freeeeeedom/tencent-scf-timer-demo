package priv.freeeeeedom.cf;

import com.google.gson.Gson;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.models.GetFunctionResponse;
import com.tencentcloudapi.scf.v20180416.models.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import priv.freeeeeedom.cf.request.ScfUpdater;
import priv.freeeeeedom.cf.request.data.CreateTriggerRequestPlus;
import priv.freeeeeedom.cf.request.data.GetFunctionRequestPlus;
import priv.freeeeeedom.cf.request.data.ListFunctionsRequestPlus;
import priv.freeeeeedom.timer.data.InfResultVO;
import priv.freeeeeedom.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * scf触发器操作
 *
 * @author: Nevernow
 * @Date: 17:49 2019/5/23
 */
@Controller
@RequestMapping("/scf")
public class ScfController {
    private static final String OPEN = "OPEN";
    private static final String CLOSE = "CLOSE";
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfController.class);

    /**
     * 查询函数列表
     *
     * @param: [nameSpace, request, response]
     * @return: InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:50
     */
    @CrossOrigin
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
     * @return: InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @CrossOrigin
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
     * @return: InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @CrossOrigin
    @RequestMapping(value = "/functionTriggers.mob", method = RequestMethod.GET)
    @ResponseBody
    public InfResultVO functionTriggers(String nameSpace, String functionName,
                                        HttpServletRequest request, HttpServletResponse response) {
        InfResultVO resultVO = new InfResultVO();
        try {
            Gson gson = new Gson();
            ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
            //查询scf详情
            GetFunctionResponse res = update.getFunctionDetail(new GetFunctionRequestPlus(functionName));
            //获取触发器
            List<Trigger> triggers = Arrays.asList(res.getTriggers());
            List<Map<String, String>> result = Collections.synchronizedList(new ArrayList<>());
            triggers.parallelStream().forEach(item -> {
                Map map = new HashMap(9);
                map.put("nameSpace", nameSpace);
                map.put("functionName", functionName);
                map.put("type", item.getType());
                map.put("triggerName", item.getTriggerName());
                map.put("enable", item.getEnable());
                map.put("triggerDesc", gson.fromJson(item.getTriggerDesc(), Map.class).get("cron"));
                result.add(map);
            });
            resultVO.setResult(result);
        } catch (Exception e) {// 同步事件接口调用错误
            resultVO.setDes(ResultUtils.getExceptionLineSize(e, 7));
            resultVO.setError("10000");
        }
        return resultVO;
    }

    /**
     * 更新触发器
     *
     * @param: [param, request, response]
     * @return: InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @CrossOrigin
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
     * 创建触发器
     *
     * @param: [param, request, response]
     * @return: InfResultVO
     * @author: Nevernow
     * @Date: 2019/5/23 17:51
     */
    @CrossOrigin
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
        Assert.notNull(nameSpace, "命名空间为空!");
        Assert.notNull(functionName, "函数名为空!");
        Assert.notNull(triggerName, "触发器名称为空!");
        Assert.notNull(triggerDesc, "cron表达式为空!");
        Assert.notNull(enable, "是否开启为空!");
        if (!enable.equals(OPEN) && !enable.equals(CLOSE)) {
            throw new ValidationException("是否开启值不正确,应为字符串OPEN或CLOSE!!!");
        }
    }

    /**
     * 享元工厂
     *
     * @author: Nevernow
     * @Date: 16:23 2019/5/23
     */
    static class ScfUpdaterFactory {
        private static final Map<String, ScfUpdater> UPDATERS = new ConcurrentHashMap(50);

        static ScfUpdater getUpdater(String nameSpace) {
            ScfUpdater updater;
            if (StringUtils.isEmpty(nameSpace)) {
                updater = getUpdater("crm");
            } else {
                updater = UPDATERS.get(nameSpace);
            }
            if (null == updater) {
                updater = new ScfUpdater(nameSpace);
                log.info("新建命名空间原型" + nameSpace);
                UPDATERS.putIfAbsent(nameSpace, updater);
            }
            return updater;
        }
    }
}
