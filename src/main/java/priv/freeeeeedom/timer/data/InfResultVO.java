package priv.freeeeeedom.timer.data;

/**
 * @author Nevernow
 */
public class InfResultVO<T> extends BaseResult {
    private T result;

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public BaseResult success(T result, String message) {
        this.setResult(result);
        this.setMeg(message);
        return this;
    }
}
