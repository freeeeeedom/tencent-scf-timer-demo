package priv.freeeeeedom.timer.data;

public enum TimerTaskType {
    /**
     * 通过class全名加载
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    CLASS("class"),
    /**
     * 通过bean加载
     * @author: Nevernow
     * @Date: 2019/5/21 16:38
     */
    BEAN("bean");

    TimerTaskType(String val) {
        this.val = val;
    }

    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
