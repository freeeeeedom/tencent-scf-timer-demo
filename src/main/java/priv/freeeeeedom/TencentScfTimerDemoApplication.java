package priv.freeeeeedom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class TencentScfTimerDemoApplication {

    public static ApplicationContext context = null;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        context = SpringApplication.run(TencentScfTimerDemoApplication.class, args);
    }
}
