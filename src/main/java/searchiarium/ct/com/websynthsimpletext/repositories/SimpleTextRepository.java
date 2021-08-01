package searchiarium.ct.com.websynthsimpletext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchiarium.ct.com.websynthsimpletext.models.SimpleText;

@Repository
public interface SimpleTextRepository extends JpaRepository<SimpleText, Long> {
}
