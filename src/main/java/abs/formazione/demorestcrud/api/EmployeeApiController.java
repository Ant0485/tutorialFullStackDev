package abs.formazione.demorestcrud.api;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<String> greetings(){
        LOGGER.info("Greetings in home");
        return new ResponseEntity<>(service.greetings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id){
        LOGGER.info("getEmployeeById in API");
        return service.getEmployeeById(id);
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

    //POST APIs
    @PostMapping(value = "/insertNewEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee postNewEmployee(@RequestBody Employee employee){
        LOGGER.info("info nome: " + employee.getFirstName() + " cognome: " + employee.getLastName() + " email: " + employee.getEmail());
        return service.postNewEmployee(employee);
    }

    //DELETE APIs
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        LOGGER.info("deleteEmployee in API");
        if (service.deleteEmployeeById(id)){
            return new ResponseEntity<>("Employee record deleted correctly.\n",
                                        HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The select id does not exist in the database.\n",
                                        HttpStatus.BAD_REQUEST);
        }
    }
}
