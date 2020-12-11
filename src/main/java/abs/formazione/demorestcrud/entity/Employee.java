package abs.formazione.demorestcrud.entity;

import io.swagger.annotations.ApiModelProperty;
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
}
