package pl.kabacinsp.obywatel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ObywatelConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObywatelConfigApplication.class, args);
	}

}
