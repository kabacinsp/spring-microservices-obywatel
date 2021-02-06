package pl.kabacinsp.obywatel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ObywatelGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObywatelGatewayApplication.class, args);
    }

}