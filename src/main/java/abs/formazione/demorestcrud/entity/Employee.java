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

    @Column(name = "firstName")
    @Getter
    @Setter
    public String firstName;

    @Column(name = "lastName")
    @Getter
    @Setter
    public String lastName;

    @Column(name = "email")
    @Getter
    @Setter
    public String email;
}
