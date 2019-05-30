package priv.freeeeeedom.cf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import priv.freeeeeedom.cf.request.ScfUpdater;

import javax.validation.ValidationException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 享元工厂
 *
 * @author: Nevernow
 * @Date: 16:23 2019/5/23
 */
public class ScfUpdaterFactory {
    /**
     * log对象
     */
    private static Logger log = LoggerFactory.getLogger(ScfUpdaterFactory.class);
    private static final Map<String, ScfUpdater> UPDATERS = new ConcurrentHashMap(50);

    public static ScfUpdater getUpdater(String nameSpace) {
        ScfUpdater updater;
        if (StringUtils.isEmpty(nameSpace)) {
            throw new ValidationException("命名空间为空");
        } else {
            updater = UPDATERS.get(nameSpace);
        }
        if (null == updater) {
            updater = new ScfUpdater(nameSpace);
            log.info("新建命名空间原型" + nameSpace);
            UPDATERS.putIfAbsent(nameSpace, updater);
        }
        return updater;
    }
}
