package priv.freeeeeedom.timer.data;

import com.google.gson.Gson;

/**
 * scf定时器入参
 *
 * @author: Nevernow
 * @Date: 15:11 2019/5/21
 */
public class ScfTimerDTO {
    private String nameSpace;
    private String functionName;
    private String triggerName;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
