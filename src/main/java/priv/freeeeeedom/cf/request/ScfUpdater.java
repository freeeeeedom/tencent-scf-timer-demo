package priv.freeeeeedom.cf.request;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.scf.v20180416.ScfClient;
import com.tencentcloudapi.scf.v20180416.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.cf.request.data.*;
import priv.freeeeeedom.cf.request.data.base.ScfRequestBase;

/**
 * scf更新sdk调用方法
 *
 * @author: Nevernow
 * @Date: 10:57 2019/5/21
 */
public class ScfUpdater {
    //todo:输入接入点信息
    public static final String ENDPOINT = "scf.tencentcloudapi.com";
    public static final String REGION = "ap-guangzhou";
    public static final String SERCET_ID = "*******";
    public static final String SERCET_KEY = "*******";
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfUpdater.class);
    private static ScfClient client = null;

    private String Namespace = null;
    private String FunctionName = null;
    private static VariablePrototype variablePrototype = new VariablePrototype();

    public ScfUpdater(String Namespace) {
        this(Namespace, null);
    }

    public ScfUpdater(String Namespace, String FunctionName) {
        this(Namespace, FunctionName, SERCET_ID, SERCET_KEY,
                ENDPOINT, REGION);
    }

    public ScfUpdater(String Namespace, String FunctionName, String sercetId, String secretKey, String Endpoint, String region) {
        Credential cred = new Credential(sercetId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(Endpoint);

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        client = new ScfClient(cred, region, clientProfile);

        this.Namespace = Namespace;
        this.FunctionName = FunctionName;
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
        delTrigger(new DeleteTriggerRequestPlus(Namespace, req.getFunctionName(), req.getTriggerName()));
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
        req.setNamespace(Namespace);
        ListFunctionsResponse resp = client.ListFunctions(req);
        return resp;
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
        GetFunctionResponse resp = client.GetFunction(req);
        return resp;
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
        req.setNs(Namespace);
        if (FunctionName != null) {
            req.setFn(FunctionName);
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
