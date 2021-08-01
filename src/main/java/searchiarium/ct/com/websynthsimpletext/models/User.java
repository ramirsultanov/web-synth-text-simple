package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
}
