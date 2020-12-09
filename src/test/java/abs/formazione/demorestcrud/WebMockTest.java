package abs.formazione.demorestcrud;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import abs.formazione.demorestcrud.api.EmployeeApiController;
import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/*
This test only verifies the correct interaction of the Controller class with the HTTP layer.
The service layer is mocked through Mockito.
 */

@WebMvcTest(EmployeeApiController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    //Used for converting entities into JSON
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(employeeService.greetings()).thenReturn("Hello there");
        this.mockMvc.perform(get("/api/employee/")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().string(containsString("Hello there")));
    }

    @Test
    public void checkSearchById() throws Exception {
        Integer test_id = 42;
        Integer wrong_test_id = 43;
        Employee test_employee = new Employee(42, "Sergio", "Rossi", "emailfalsa34@gmail.com");
        when(employeeService.getEmployeeById(test_id)).thenReturn(java.util.Optional.of(test_employee));
        this.mockMvc.perform(get("/api/employee/" + test_id.toString())).andDo(print()).andExpect(status().isOk()).
                andExpect(content().json(objectMapper.writeValueAsString(test_employee)));

        when(employeeService.getEmployeeById(test_id)).thenReturn(null);
        this.mockMvc.perform(get("/api/employee/" + test_id.toString())).andDo(print()).andExpect(status().isOk()).
                andExpect(content().string(""));
    }

    @Test
    public void checkReturnOnPostEmployee() throws Exception {
        Employee new_employee = new Employee(42, "Sergio", "Rossi", "emailfalsa34@gmail.com");
        when(employeeService.postNewEmployee(new_employee)).thenReturn(new_employee);
        this.mockMvc.perform(post("/api/employee/insertNewEmployee").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(new_employee))).
                andExpect(status().isOk()).
                andExpect(content().json(objectMapper.writeValueAsString(new_employee)));
    }

    @Test
    public void checkDeleteEmployeeById() throws Exception{
        Integer test_id = 42;
        Integer wrong_test_id = 43;
        when(employeeService.deleteEmployeeById(42)).thenReturn(true);
        this.mockMvc.perform(delete("/api/employee/" + test_id.toString())).
                andExpect(status().isOk()).
                andExpect(content().string("Employee record deleted correctly.\n"));

        when(employeeService.deleteEmployeeById(43)).thenReturn(false);
        this.mockMvc.perform(delete("/api/employee/" + wrong_test_id.toString())).
                andExpect(status().isBadRequest()).
                andExpect(content().string("The select id does not exist in the database.\n"));
    }


}
