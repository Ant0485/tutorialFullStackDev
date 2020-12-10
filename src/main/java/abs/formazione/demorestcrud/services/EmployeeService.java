package abs.formazione.demorestcrud.services;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Employee> getEmployeeById(Integer id){
        LOGGER.info("getEmployeeByIdCalled");
        return repository.findById(id);
    }

    public List<Employee> getEmployeeByName(String name){
        LOGGER.info("getEmployeesByNameCalled");
        return repository.findByFirstName(name);
    }

    public List<Employee> getEmployeeByLastName(String lastName){
        LOGGER.info("getEmployeesByNameCalled");
        return repository.findByLastName(lastName);
    }

    //POST calls
    public Employee postNewEmployee(Employee employee){
        LOGGER.info("postNewEmployeeCalled");
        return repository.save(employee);
    }

    //DELETE calls
    public Boolean deleteEmployeeById(Integer id){
        LOGGER.info("deleteEmployeeByIdCalled");
        Boolean success = true;
        if (repository.existsById(id)){
            repository.deleteById(id);
        } else {
            success = false;
        }
        return success;
    }

    //PUT calls
    public Employee updateEmployeeById(Integer id, Employee new_employee){
        LOGGER.info("updateEmployeeByIdCalled");
        Employee upd_employee;
        //should replace also the id? Should we expect the id or not?
        if (repository.existsById(id) && new_employee.getId() == id){
            upd_employee = repository.save(new_employee);
        } else {
            upd_employee = null;
        }
        return upd_employee;
    }
}
