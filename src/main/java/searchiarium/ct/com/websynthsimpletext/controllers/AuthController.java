package searchiarium.ct.com.websynthsimpletext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import searchiarium.ct.com.websynthsimpletext.models.LoginDto;
import searchiarium.ct.com.websynthsimpletext.models.RegisterDto;
import searchiarium.ct.com.websynthsimpletext.models.RegisterTeamDto;
import searchiarium.ct.com.websynthsimpletext.services.AuthService;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(LoginDto loginDto) {
        if (authService.login(loginDto)) {
            return "redirect:/request.time";
        }
        return "login"; // todo
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String register(RegisterDto registerDto) {
        if (authService.register(registerDto)) {
            return "redirect:/request.time";
        }
        return "register"; // todo
    }

}
