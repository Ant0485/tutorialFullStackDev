package abs.formazione.demorestcrud.security;

import abs.formazione.demorestcrud.entity.Employee;
import abs.formazione.demorestcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Still not sure if this class is actually needed in a OAuth2 context
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByEmail(email);
        if (employee.isEmpty())
            throw new UsernameNotFoundException("Could not find in the database the employee with the specified email.");
        return new EmployeePrincipal(employee.get());
    }
}
