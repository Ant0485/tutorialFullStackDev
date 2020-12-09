package abs.formazione.demorestcrud.services;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeService.class.getName());

    @Autowired
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    public String greetings() {
        return "Hello there!";
    }

    public List<Employee> getEmployeesDefault() {
        LOGGER.info("getEmployeesDefaultCalled");
        return repository.findAll();
    }

    public List<Employee> getEmployeeByName(String name){
        LOGGER.info("getEmployeesByNameCalled");
        return repository.findByFirstName(name);
    }
}
