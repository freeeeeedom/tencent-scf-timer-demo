package priv.freeeeeedom.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * ImportResource引入资源文件有三种方式：
 * 1.直接引入，该路径就是src/resources/下面的文件：file
 * 2.classpath引入：该路径就是src/java下面的配置文件：classpath:file
 * 3.引入本地文件：该路径是一种绝对路径：file:D://....
 * @author Nevernow
 */

@Configuration
@ImportResource(locations = {"config/*.xml"})
public class XmlConfiguration {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize(DataSize.ofMegabytes(1500));
        config.setMaxRequestSize(DataSize.ofMegabytes(100));
        return config.createMultipartConfig();
    }
}
