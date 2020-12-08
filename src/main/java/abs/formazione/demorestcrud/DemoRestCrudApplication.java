package abs.formazione.demorestcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EnableJpaRepositories("abs.formazione.demorestcrud.repository")
public class DemoRestCrudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestCrudApplication.class, args);
	}

}
