package priv.freeeeeedom.timer.demo;

import org.springframework.stereotype.Component;
import priv.freeeeeedom.timer.base.BaseTimerTask;

@Component
public class ExceptionTaskDemo extends BaseTimerTask {
    @Override
    protected Object runTask() throws Exception {
        throw new RuntimeException("异常了！！");
    }
}
