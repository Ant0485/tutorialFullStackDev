package abs.formazione.demorestcrud;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import abs.formazione.demorestcrud.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository repository;
    //Without the annotation, it would be equivalent to:
    //private EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);

    private EmployeeService employeeService;
    private List<Employee> fake_list;

    //Setting up before each test a new service with a mock repository
    // and a fake list of entities from the DB.
    @BeforeEach
    void initUseCase() {
        employeeService = new EmployeeService(repository);
        fake_list = new ArrayList<Employee>();
        fake_list.add(new Employee(1, "Paolo", "Rossi", "fakeemail@gmail.com"));
        fake_list.add(new Employee(2, "Giacomo", "Bianchi", "fakeemail2@gmail.com"));
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




}
