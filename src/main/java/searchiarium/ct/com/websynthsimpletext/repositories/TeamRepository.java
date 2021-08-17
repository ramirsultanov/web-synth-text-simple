package searchiarium.ct.com.websynthsimpletext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchiarium.ct.com.websynthsimpletext.entities.Team;
import searchiarium.ct.com.websynthsimpletext.entities.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByName(String name);
    Team findByOwnersContains(User user);
    Team findByUsersContains(User user);
}
