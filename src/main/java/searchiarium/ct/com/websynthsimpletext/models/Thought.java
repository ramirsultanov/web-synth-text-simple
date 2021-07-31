package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "thought")
public class Thought {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
}
