package searchiarium.ct.com.websynthsimpletext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchiarium.ct.com.websynthsimpletext.models.Thought;
import searchiarium.ct.com.websynthsimpletext.models.TimeModel;
import searchiarium.ct.com.websynthsimpletext.services.IndexService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public ResponseEntity<?> index(@RequestParam("query") String query) {
        Map<String, List<Thought>> map = new HashMap<>();
        List<Thought> list = indexService.getThoughts(query);
        map.put("text", list);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/request.time")
    public String requestTime(Model model) {
        model.addAttribute("time", new TimeModel());
        return "time";
    }
}
