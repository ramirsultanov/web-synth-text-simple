package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.entities.Role;
import searchiarium.ct.com.websynthsimpletext.entities.Team;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.exceptions.UsernameAlreadyExistsException;
import searchiarium.ct.com.websynthsimpletext.models.LoginDto;
import searchiarium.ct.com.websynthsimpletext.models.RegisterDto;
import searchiarium.ct.com.websynthsimpletext.models.RegisterTeamDto;
import searchiarium.ct.com.websynthsimpletext.repositories.TeamRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    public boolean login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null) {
            return user.getPassword().equals(passwordEncoder.encode(loginDto.getPassword()));
        }
        return false;
    }

    public boolean register(RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()) != null) {
            // throw new UsernameAlreadyExistsException("There is already user with username " + registerDto.getUsername());
            return false;
        }
        User user = User.builder()
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode(registerDto.getPassword()))
//                .roles(Set.of(Role.builder().name("User").build()))
                .roles(Set.of(Role.USER))
                .build();
        userRepository.save(user);
        return true;
    }

}
