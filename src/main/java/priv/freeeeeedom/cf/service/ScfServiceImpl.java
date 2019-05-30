package priv.freeeeeedom.cf.service;

import com.google.gson.Gson;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.models.GetFunctionResponse;
import com.tencentcloudapi.scf.v20180416.models.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import priv.freeeeeedom.cf.ScfUpdaterFactory;
import priv.freeeeeedom.cf.request.ScfUpdater;
import priv.freeeeeedom.cf.request.data.GetFunctionRequestPlus;

import java.util.*;

@Service
public class ScfServiceImpl {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfServiceImpl.class);

    public List<Map<String, String>> getFunctionTriggers(String nameSpace, String functionName) throws TencentCloudSDKException {
        List<Map<String, String>> result = Collections.synchronizedList(new ArrayList<>());
        Gson gson = new Gson();
        ScfUpdater update = ScfUpdaterFactory.getUpdater(nameSpace);
        //查询scf详情
        GetFunctionResponse res = update.getFunctionDetail(new GetFunctionRequestPlus(functionName));
        //获取触发器
        List<Trigger> triggers = Arrays.asList(res.getTriggers());
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
        return result;
    }
}
