package abs.formazione.demorestcrud;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import abs.formazione.demorestcrud.api.EmployeeApiController;
import abs.formazione.demorestcrud.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(employeeService.greetings()).thenReturn("Hello there");
        this.mockMvc.perform(get("/api/employee/")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().string(containsString("Hello there")));
    }




}
