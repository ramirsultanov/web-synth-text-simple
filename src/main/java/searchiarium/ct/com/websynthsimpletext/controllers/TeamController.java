package searchiarium.ct.com.websynthsimpletext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchiarium.ct.com.websynthsimpletext.models.RegisterTeamDto;
import searchiarium.ct.com.websynthsimpletext.services.TeamService;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/regTeam")
    public String registerTeam() {
        return "registerTeam";
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/regTeam")
    public String registerTeam(RegisterTeamDto registerTeamDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (teamService.registerTeam(registerTeamDto, username)) {
            return "redirect:/request.time";
        }
        return "registerTeam";
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @GetMapping("/team")
    public String team(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("team", teamService.getTeamByUsername(username));
        return "team";
    }
    @PreAuthorize("hasAuthority('OWNER')")
    @PostMapping("/team")
    public String team(String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String initiator = auth.getName();
        teamService.addUserToTeam(username, initiator);
        return "redirect:/team?";
    }

}
