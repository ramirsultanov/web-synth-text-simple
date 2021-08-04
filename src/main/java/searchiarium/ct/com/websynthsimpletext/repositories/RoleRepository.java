package searchiarium.ct.com.websynthsimpletext.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchiarium.ct.com.websynthsimpletext.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
