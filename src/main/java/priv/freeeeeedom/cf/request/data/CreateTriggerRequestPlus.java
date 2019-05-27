package priv.freeeeeedom.cf.request.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.scf.v20180416.models.CreateTriggerRequest;
import priv.freeeeeedom.cf.request.data.base.ScfRequestBase;

import java.util.HashMap;

public class CreateTriggerRequestPlus extends CreateTriggerRequest implements ScfRequestBase {
    @SerializedName("Namespace")
    @Expose
    private String Namespace;

    public CreateTriggerRequestPlus(String FunctionName, String TriggerName, String TriggerDesc, String Type, String enable) {
        this.setTriggerName(TriggerName);
        this.setTriggerDesc(TriggerDesc);
        this.setType(Type);
        this.setNamespace(Namespace);
        this.setFunctionName(FunctionName);
        this.setEnable(enable);
    }

    public CreateTriggerRequestPlus(String FunctionName, String TriggerName, String TriggerDesc) {
        this(FunctionName, TriggerName, TriggerDesc, "OPEN");
    }

    public CreateTriggerRequestPlus(String FunctionName, String TriggerName, String TriggerDesc, String enable) {
        this(FunctionName, TriggerName, TriggerDesc, "timer", enable);
    }

    public String getNamespace() {
        return Namespace;
    }

    public void setNamespace(String namespace) {
        Namespace = namespace;
    }

    @Override
    public void toMap(HashMap<String, String> map, String prefix) {
        super.toMap(map, prefix);
        this.setParamSimple(map, prefix + "Namespace", this.Namespace);
    }

    @Override
    public void setFn(String functionName) {
        this.setFunctionName(functionName);
    }

    @Override
    public void setNs(String Namespace) {
        this.setNamespace(Namespace);
    }
}
