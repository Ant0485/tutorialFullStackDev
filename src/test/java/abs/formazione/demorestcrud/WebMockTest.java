package abs.formazione.demorestcrud;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        this.mockMvc.perform(get("/api/employee/")).andExpect(status().isOk()).
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
        doReturn(true).when(employeeService).deleteEmployeeById(test_id);
        this.mockMvc.perform(delete("/api/employee/" + test_id.toString())).
                andExpect(status().isOk()).
                andExpect(content().string("Employee record deleted correctly.\n"));

        doReturn(false).when(employeeService).deleteEmployeeById(wrong_test_id);
        this.mockMvc.perform(delete("/api/employee/" + wrong_test_id.toString())).
                andExpect(status().isBadRequest()).
                andExpect(content().string("The select id does not exist in the database.\n"));
    }

    @Test
    public void checkUpdateEmployeeById() throws Exception{
        Integer test_id = 12;
        Integer wrong_test_id = 13;
        Employee upd_employee = new Employee(test_id, "Sergio", "Rossi", "nuovaemail34@gmail.com");
        doReturn(upd_employee).when(employeeService).updateEmployeeById(test_id, upd_employee);
        this.mockMvc.perform(put("/api/employee/" + test_id.toString()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(upd_employee))).
                andExpect(status().isOk()).
                andExpect(content().json(objectMapper.writeValueAsString(upd_employee)));

        doReturn(null).when(employeeService).updateEmployeeById(wrong_test_id, upd_employee);
        this.mockMvc.perform(put("/api/employee/" + wrong_test_id.toString()).
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(upd_employee))).
                andExpect(status().isBadRequest()).
                andExpect(content().string(""));
    }

}
