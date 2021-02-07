package pl.kabacinsp.obywatel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.kabacinsp.obywatel.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class ObywatelAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObywatelAuthApplication.class, args);
    }

}