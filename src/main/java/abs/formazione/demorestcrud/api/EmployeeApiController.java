package abs.formazione.demorestcrud.api;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getByName/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
        LOGGER.info("getByName in API");
        return service.getEmployeeByName(name);
    }

    @GetMapping("/getBySurname/{name}")
    public List<Employee> getEmployeeByLastName(@PathVariable String lastName){
        LOGGER.info("getByLastName in API");
        return service.getEmployeeByLastName(lastName);
    }

    @PostMapping(value = "/insertNewEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee postNewEmployee(@RequestBody Employee employee){
        LOGGER.info("info nome: " + employee.getFirstName() + " cognome: " + employee.getLastName() + " email: " + employee.getEmail());
        return service.postNewEmployee(employee);
    }
}
