package pl.kabacinsp.obywatel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ObywatelDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObywatelDiscoveryApplication.class, args);
    }

}
