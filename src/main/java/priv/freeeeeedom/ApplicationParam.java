package priv.freeeeeedom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nevernow
 */
@Component
public class ApplicationParam {
    @Autowired
    private ApplicationArguments applicationArguments;
    static Map applicationParam = new ConcurrentHashMap();

    public Map<String, String> getApplicationArguments() {
        if (CollectionUtils.isEmpty(applicationParam)) {
            applicationArguments.getNonOptionArgs().stream()
                    .forEach(s -> applicationParam.put(s.split("=")[0], s.split("=")[1]));
        }
        return applicationParam;
    }
}
