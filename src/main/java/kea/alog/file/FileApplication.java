package kea.alog.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FileApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(FileApplication.class);
		app.setDefaultProperties(java.util.Collections.singletonMap("server.port", "8086"));
		app.run(args);
		
	}

}
