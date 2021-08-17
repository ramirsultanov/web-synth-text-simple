package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.entities.Role;
import searchiarium.ct.com.websynthsimpletext.entities.Team;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.models.RegisterTeamDto;
import searchiarium.ct.com.websynthsimpletext.repositories.TeamRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean registerTeam(RegisterTeamDto registerTeamDto, String username) {
        registerTeamDto.setName(registerTeamDto.getName().toLowerCase());
        if (teamRepository.findByName(registerTeamDto.getName()) != null) {
            return false;
        }
        User user = userRepository.findByUsername(username);
        Team team = Team.builder()
                .name(registerTeamDto.getName())
                .points(0L)
                .owners(List.of(user))
                .build();
        teamRepository.save(team);
        user.addRole(Role.OWNER);
        return true;
    }

    public Team getTeamByUsername(final String username) {
        User user = userRepository.findByUsername(username);
        Team teamO = teamRepository.findByOwnersContains(user);
        Team teamP = teamRepository.findByUsersContains(user);
        return teamO == null ? teamP : teamO;
    }

    public boolean addUserToTeam(final String username, final String initiator) {
        User user = userRepository.findByUsername(username);
        User init = userRepository.findByUsername(initiator);
        /// validate
        if (
                user != null &&
                user.getTeam() == null &&
                init.getTeam() != null &&
                init.getTeam().getOwners().contains(init)
        ) {
            init.getTeam().addUser(user);
            return true;
        }
        return false;
    }

}
