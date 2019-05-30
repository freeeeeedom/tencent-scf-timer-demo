package priv.freeeeeedom.timer.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.freeeeeedom.timer.data.ScfTimerDTO;

public class ArbitrarilyTaskDemo {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ArbitrarilyTaskDemo.class);

    public Object ArbitrarilyTaskDemoTask(ScfTimerDTO dto, String string) {
        log.info("执行任意方法逻辑...");
        log.info(dto.toString());
        log.info(string);
        return dto;
    }
}
