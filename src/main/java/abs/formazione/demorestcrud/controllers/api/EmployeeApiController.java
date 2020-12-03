package abs.formazione.demorestcrud.controllers.api;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController()

@RequestMapping("/api/employee")
public class EmployeeApiController {

    @Resource
    EmployeeService service;

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return service.getEmployeesDefault();
    }

}
