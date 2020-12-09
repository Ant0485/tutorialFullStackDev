package abs.formazione.demorestcrud.api;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/employee")
public class EmployeeApiController {

    private static final Logger LOGGER = Logger.getLogger(EmployeeApiController.class.getName());

    @Autowired
    private EmployeeService service;

    public EmployeeApiController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public @ResponseBody String greetings(){
        LOGGER.info("Greetings in home");
        return service.greetings();
    }

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        LOGGER.info("getAll in API");
        return service.getEmployeesDefault();
    }
}
