package searchiarium.ct.com.websynthsimpletext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;

import java.util.Optional;

@Repository
public interface SimpleBookRepository extends JpaRepository<SimpleBook, Long> {
    SimpleBook findByShortNameEquals(String shortName);
}
