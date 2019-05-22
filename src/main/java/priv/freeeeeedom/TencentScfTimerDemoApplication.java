package priv.freeeeeedom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TencentScfTimerDemoApplication {

    public static ApplicationContext context = null;

    public static void main(String[] args) {
        context = SpringApplication.run(TencentScfTimerDemoApplication.class, args);
    }
}
