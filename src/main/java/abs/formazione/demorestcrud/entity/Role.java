package abs.formazione.demorestcrud.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer role_id;

    @Column(name = "role_name")
    @Getter
    @Setter
    private String role_name;
}
