package searchiarium.ct.com.websynthsimpletext.models;

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
    @OneToMany(fetch =  FetchType.EAGER)
    private List<User> users;
    private Long points;
}
