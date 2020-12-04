package abs.formazione.demorestcrud.repository;

import abs.formazione.demorestcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
