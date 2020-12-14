package abs.formazione.demorestcrud.api;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.security.EmployeePrincipal;
import abs.formazione.demorestcrud.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Optional<Employee> getEmployeeById(
            @AuthenticationPrincipal EmployeePrincipal employeePrincipal, @PathVariable Integer id){
        LOGGER.info("getEmployeeById in API");
        if (employeePrincipal.getEmployee().getId() == id){
            return service.getEmployeeById(id);
        } else {
            return Optional.empty();
        }


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

    //PUT APIs
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(
            @PathVariable Integer id, @RequestBody Employee new_employee){
        LOGGER.info("updateEmployee in API");
        Employee upd_employee = service.updateEmployeeById(id, new_employee);
        if (upd_employee != null) {
            return new ResponseEntity<Employee>(upd_employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }
    }


}
