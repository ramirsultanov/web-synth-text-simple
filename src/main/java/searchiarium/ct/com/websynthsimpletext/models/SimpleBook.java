package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "simplebook")
public class SimpleBook {
    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String shortName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SimpleText> texts;
    @OneToOne
    private Team creator;
    @OneToOne
    private Team owner;
}
