package priv.freeeeeedom.cf;

import com.google.gson.Gson;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.scf.v20180416.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.cf.request.ScfUpdater;
import priv.freeeeeedom.cf.request.data.CreateTriggerRequestPlus;
import priv.freeeeeedom.cf.request.data.GetFunctionRequestPlus;
import priv.freeeeeedom.cf.request.data.ListFunctionsRequestPlus;
import priv.freeeeeedom.cf.request.data.UpdateFunctionConfigurationRequestPlus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 云函数更新demo
 *
 * @author: Nevernow
 * @Date: 10:47 2019/5/21
 */
public class ScfDemo {
    private static Logger log = LoggerFactory.getLogger(ScfDemo.class);

    public static void main(String[] args) throws TencentCloudSDKException {
        Gson gson = new Gson();
        ScfUpdater update = new ScfUpdater("crm");
        //更新云函数
        updateFunctionDemo(update);
        //查询scf列表
        ListFunctionsResponse list = update.getFunctionList(new ListFunctionsRequestPlus(100));
        log.info(String.valueOf(list.getFunctions().length));
        //查询scf详情
        GetFunctionResponse res = update.getFunctionDetail(new GetFunctionRequestPlus("test"));
        //获取触发器
        List<Trigger> triggers = Arrays.asList(res.getTriggers());
        triggers.stream().forEach(item -> {
            log.info(item.getType() + " " + item.getTriggerName() + " " + gson.fromJson(item.getTriggerDesc(), Map.class).get("cron"));
        });
        update.updateTrigger(new CreateTriggerRequestPlus("test", "testTimer", "0 1 2 4 * * *"));
    }

    private static void updateFunctionDemo(ScfUpdater update) throws TencentCloudSDKException {
        UpdateFunctionConfigurationRequestPlus req = new UpdateFunctionConfigurationRequestPlus();
        req.setFunctionName("test");
        req.setNamespace("crm");
        req.setDescription("测试远程修改");
        //环境变量
        Environment env = new Environment();
        List<Variable> list = new ArrayList<Variable>();
        list.add(ScfUpdater.buildNewVariable("k1", "v1"));
        list.add(ScfUpdater.buildNewVariable("k2", "v2"));

        Variable[] variables = new Variable[list.size()];
        env.setVariables(list.toArray(variables));
        req.setEnvironment(env);

        update.updateScf(req);
    }

}