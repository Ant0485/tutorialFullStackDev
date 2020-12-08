package abs.formazione.demorestcrud.repository;

import abs.formazione.demorestcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
