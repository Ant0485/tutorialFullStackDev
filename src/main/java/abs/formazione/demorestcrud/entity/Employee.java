package abs.formazione.demorestcrud.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Id of the Employee",name="id",required=true,value="test id")
    public Integer id;

    @Column(name = "first_name")
    @Getter
    @Setter
    @ApiModelProperty(notes = "firstName of the Employee",name="firstName",required=true,value="test firstName")
    public String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    @ApiModelProperty(notes = "lastName of the Employee",name="lastName",required=true,value="test lastName")
    public String lastName;

    @Column(name = "email")
    @Getter
    @Setter
    @ApiModelProperty(notes = "email of the Employee",name="email",required=true,value="test email")
    public String email;

    /*
    Creation of a new attribute that defines a ManyToMany relationship with the Role table.
    On the dataset, this property would be reflected by a connection table defined of couples
    of foreign keys from the employee and the roles table.
    A user can have different roles, not just one.
     */
    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(name = "enabled")
    private boolean isEnabled;
}
