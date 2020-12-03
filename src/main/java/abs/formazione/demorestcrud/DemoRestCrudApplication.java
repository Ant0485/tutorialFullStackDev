package abs.formazione.demorestcrud;

import abs.formazione.application.api.EmployeeApiController;
import abs.formazione.application.services.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DemoRestCrudApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestCrudApplication.class, args);
	}

}
