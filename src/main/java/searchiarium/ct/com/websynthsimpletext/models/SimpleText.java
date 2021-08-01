package searchiarium.ct.com.websynthsimpletext.models;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * SimpleText is a class mapping a text written by users in any book (page)
 */
@Data
@Entity
@Table(name = "simpletext")
public class SimpleText {
    @GeneratedValue
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String text;
    @OneToOne(fetch = FetchType.EAGER)
    private User owner;
    private OffsetDateTime createdDateTime;
    private OffsetDateTime lastEditedDateTime;
    @ManyToOne(fetch = FetchType.EAGER)
    private SimpleBook books;
}
