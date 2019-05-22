package priv.freeeeeedom.cf.request.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tencentcloudapi.scf.v20180416.models.ListFunctionsRequest;

import java.util.HashMap;

public class ListFunctionsRequestPlus extends ListFunctionsRequest {
    @SerializedName("Namespace")
    @Expose
    private String Namespace;

    public ListFunctionsRequestPlus() {
    }

    public ListFunctionsRequestPlus(Integer limit) {
        this.setLimit(limit);
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
}