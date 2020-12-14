package abs.formazione.demorestcrud;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.entity.Role;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import abs.formazione.demorestcrud.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository repository;

    private EmployeeService employeeService;
    private List<Employee> fake_list;
    private Set<Role> roles;

    //Setting up before each test a new service with a mock repository
    // and a fake list of entities from the DB.
    @BeforeEach
    void initUseCase() {
        employeeService = new EmployeeService(repository);
        fake_list = new ArrayList<Employee>();
        roles = new HashSet<>();
        roles.add(new Role(1, "ROLE_USER"));
        fake_list.add(new Employee(1, "Paolo", "Rossi", "fakeemail@gmail.com", roles, true));
        fake_list.add(new Employee(2, "Giacomo", "Bianchi", "fakeemail2@gmail.com", roles, true));
    }


    @Test
    void getAllEmployeeTest() {
        when(repository.findAll()).thenReturn(fake_list);
        assertEquals(employeeService.getEmployeesDefault(), fake_list);
    }

    @Test
    void getEmployeeByName() {
        List<Employee> result_list = new ArrayList<Employee>();
        result_list.add(fake_list.get(0));
        when(repository.findByFirstName("Paolo")).thenReturn(result_list);
        assertEquals(employeeService.getEmployeeByName("Paolo").get(0), fake_list.get(0));
    }

    @Test
    void postNewEmployee() {
        Employee new_employee = fake_list.get(0);
        when(repository.save(new_employee)).thenReturn(new_employee);
        assertEquals(employeeService.postNewEmployee(new_employee), new_employee);
    }

    @Test
    void deleteEmployeeById() {
        Integer test_id = 42;
        Integer wrong_test_id = 43;
        doReturn(true).when(repository).existsById(test_id);

        Employee new_employee = new Employee(test_id, "Sergio", "Rossi", "emailfalsa34@gmail.com", roles, true);
        assertEquals(employeeService.deleteEmployeeById(test_id), true);


        doReturn(false).when(repository).existsById(wrong_test_id);
        assertEquals(employeeService.deleteEmployeeById(wrong_test_id), false);
    }


    @Test
    void updateEmployeeById() {
        Integer test_id = 42;
        Integer wrong_test_id = 43;
        Employee upd_employee = new Employee(test_id, "Sergio", "Rossi", "emailnuova34@gmail.com", roles, true);
        doReturn(true).when(repository).existsById(test_id);
        doReturn(upd_employee).when(repository).save(upd_employee);
        assertEquals(employeeService.updateEmployeeById(test_id, upd_employee), upd_employee);

        doReturn(false).when(repository).existsById(test_id);
        assertEquals(employeeService.updateEmployeeById(test_id, upd_employee), null);

        doReturn(true).when(repository).existsById(test_id);
        upd_employee.setId(wrong_test_id);
        assertEquals(employeeService.updateEmployeeById(test_id, upd_employee), null);
    }

}
