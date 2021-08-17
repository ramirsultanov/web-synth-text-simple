package searchiarium.ct.com.websynthsimpletext.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public void addUser(final User user) {
        this.users.add(user);
    }
}
