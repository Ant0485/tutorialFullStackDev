package abs.formazione.demorestcrud.entity;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;


@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
}
