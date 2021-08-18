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

    public SimpleText getSimpleText(final User user) {
        for (SimpleText text : texts) {
            if (text.getOwner() == user) {
                return text;
            }
        }
        throw new RuntimeException("There is no user's text in book");
    }
}
