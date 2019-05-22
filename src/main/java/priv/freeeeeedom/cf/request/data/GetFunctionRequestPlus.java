package priv.freeeeeedom.cf.request.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.scf.v20180416.models.GetFunctionRequest;
import priv.freeeeeedom.cf.request.data.base.ScfRequestBase;

import java.util.HashMap;

public class GetFunctionRequestPlus extends GetFunctionRequest implements ScfRequestBase {
    @SerializedName("Namespace")
    @Expose
    private String Namespace;

    public GetFunctionRequestPlus() {
    }

    public GetFunctionRequestPlus(String functionName) {
        this.setFunctionName(functionName);
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
