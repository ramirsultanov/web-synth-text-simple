package searchiarium.ct.com.websynthsimpletext.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "team")
public class Team {
    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany
    private List<User> users;
    @OneToMany
    private List<User> owners;
    private Long points;
}
