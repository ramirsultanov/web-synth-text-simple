package searchiarium.ct.com.websynthsimpletext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchiarium.ct.com.websynthsimpletext.entities.Thought;

@Repository
public interface IndexRepository extends JpaRepository<Thought, Long> {
//    List<Thought> findAllByTextRegex(String textRegex);
}
