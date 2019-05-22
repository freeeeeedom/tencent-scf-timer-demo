package priv.freeeeeedom.timer.data;

import com.google.gson.Gson;

/**
 * scf定时器入参
 *
 * @author: Nevernow
 * @Date: 15:11 2019/5/21
 */
public class ScfTimerDTO {
    private String scfNamespace;
    private String scfName;
    private String triggerName;

    public String getScfNamespace() {
        return scfNamespace;
    }

    public void setScfNamespace(String scfNamespace) {
        this.scfNamespace = scfNamespace;
    }

    public String getScfName() {
        return scfName;
    }

    public void setScfName(String scfName) {
        this.scfName = scfName;
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
