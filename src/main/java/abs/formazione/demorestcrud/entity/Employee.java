package abs.formazione.demorestcrud.entity;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    @Getter
    @Setter
    public Integer id;

    @Column(name = "first_name")
    @Getter
    @Setter
    public String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    public String lastName;

    @Column(name = "email")
    @Getter
    @Setter
    public String email;

    /*
    Creation of a new attribute that defines a ManyToMany relationship with the Role table.
    On the dataset, this property would be reflected by a connection table defined of couples
    of foreign keys from the employee and the roles table.
    A user can have different roles, not just one.
     */
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "enabled")
    private boolean isEnabled;
}
