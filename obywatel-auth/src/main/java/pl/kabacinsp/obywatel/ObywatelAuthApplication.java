package pl.kabacinsp.obywatel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pl.kabacinsp.obywatel.config.ConfProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfProperties.class)
@EnableDiscoveryClient
public class ObywatelAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObywatelAuthApplication.class, args);
	}

}