package priv.freeeeeedom.cf.request;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.scf.v20180416.ScfClient;
import com.tencentcloudapi.scf.v20180416.models.*;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import priv.freeeeeedom.ApplicationParam;
import priv.freeeeeedom.cf.request.data.*;
import priv.freeeeeedom.cf.request.data.base.ScfRequestBase;
import priv.freeeeeedom.utils.BeanTool;

import java.util.Map;

/**
 * scf更新sdk调用方法
 *
 * @author: Nevernow
 * @Date: 10:57 2019/5/21
 */

public class ScfUpdater {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfUpdater.class);
    private static ScfClient client = null;


    private String nameSpace;
    private String functionName;
    private static VariablePrototype variablePrototype = new VariablePrototype();

    public ScfUpdater(String nameSpace) {
        this(nameSpace, null);
    }

    private ScfUpdater(String nameSpace, String functionName) {
        this(nameSpace, functionName, "scf.tencentcloudapi.com", "ap-guangzhou");
    }

    private ScfUpdater(String namseSpace, String functionName, String enpoint, String region) {
        ApplicationParam param = (ApplicationParam) BeanTool.getBean(ApplicationParam.class);
        Map<String, String> applicationArguments = param.getApplicationArguments();
        Credential cred = new Credential(applicationArguments.get("secretId"), applicationArguments.get("secretKey"));

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(enpoint);

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        client = new ScfClient(cred, region, clientProfile);

        this.nameSpace = namseSpace;
        this.functionName = functionName;
    }

    /**
     * 更新触发器
     *
     * @param: [req]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/21 10:43
     */
    public void updateTrigger(CreateTriggerRequestPlus req) throws TencentCloudSDKException {
        delTrigger(new DeleteTriggerRequestPlus(nameSpace, req.getFunctionName(), req.getTriggerName()));
        createTrigger(req);
    }

    /**
     * 新增触发器
     *
     * @param: [req]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/21 10:43
     */
    public void createTrigger(CreateTriggerRequestPlus req) throws TencentCloudSDKException {
        initBaseParam(req);
        CreateTriggerResponse resp = client.CreateTrigger(req);
        log.info(CreateTriggerRequest.toJsonString(resp));

    }

    /**
     * 删除触发器
     *
     * @param: [req]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/21 10:43
     */
    public void delTrigger(DeleteTriggerRequestPlus req) throws TencentCloudSDKException {
        initBaseParam(req);
        DeleteTriggerResponse resp = client.DeleteTrigger(req);
        log.info(DeleteTriggerRequest.toJsonString(resp));
    }

    /**
     * 获取函数列表
     *
     * @param: [request]
     * @return: com.tencentcloudapi.scf.v20180416.models.ListFunctionsResponse
     * @author: Nevernow
     * @Date: 2019/5/21 10:44
     */
    public ListFunctionsResponse getFunctionList(ListFunctionsRequestPlus req) throws TencentCloudSDKException {
        req.setNamespace(nameSpace);
        return client.ListFunctions(req);
    }

    /**
     * 获取函数详情
     *
     * @param: [request]
     * @return: com.tencentcloudapi.scf.v20180416.models.GetFunctionResponse
     * @author: Nevernow
     * @Date: 2019/5/21 10:44
     */
    public GetFunctionResponse getFunctionDetail(GetFunctionRequestPlus req) throws TencentCloudSDKException {
        initBaseParam(req);
        return client.GetFunction(req);
    }

    /**
     * 更新云函数信息
     *
     * @param: [req]
     * @return: void
     * @author: Nevernow
     * @Date: 2019/5/21 10:44
     */
    public void updateScf(UpdateFunctionConfigurationRequestPlus req) throws TencentCloudSDKException {
        initBaseParam(req);
        UpdateFunctionConfigurationResponse resp = client.UpdateFunctionConfiguration(req);
        log.info(UpdateFunctionConfigurationRequest.toJsonString(resp));

    }

    private void initBaseParam(ScfRequestBase req) {
        req.setNs(nameSpace);
        if (functionName != null) {
            req.setFn(functionName);
        }
    }

    static class VariablePrototype extends Variable implements Cloneable {
        @Override
        public VariablePrototype clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return (VariablePrototype) clone;
        }
    }

    public static Variable buildNewVariable(String key, String value) {
        Variable v = variablePrototype.clone();
        v.setKey(key);
        v.setValue(value);
        return v;
    }
}
