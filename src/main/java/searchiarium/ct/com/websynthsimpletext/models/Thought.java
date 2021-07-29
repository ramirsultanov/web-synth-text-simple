package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "thought")
public class Thought {
    @GeneratedValue
    @Id
    private Long id;
    private String text;
}
