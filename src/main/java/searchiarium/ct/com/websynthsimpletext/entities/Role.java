package searchiarium.ct.com.websynthsimpletext.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "entity")
public enum Role implements GrantedAuthority {
//    @GeneratedValue
//    @Id
//    private Long id;
//    private String name;
//    @Transient
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;

    USER, OWNER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
