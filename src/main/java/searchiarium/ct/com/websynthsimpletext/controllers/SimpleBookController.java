package searchiarium.ct.com.websynthsimpletext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchiarium.ct.com.websynthsimpletext.models.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.services.SimpleBookService;

@Controller
public class SimpleBookController {

    @Autowired
    private SimpleBookService bService;

    @GetMapping("/")
    public String simpleBook(@RequestParam final String b, Model model) {
        if (b != null) {
            SimpleBook book = bService.getSimpleBook(b);
            if (book != null/* && user is in owner team*/) {
                model.addAttribute("pageName", book.getShortName());
            } else {
                return "notFoundSimpleBook";
            }
        } else {
            return "invalidSimpleBook";
        }
        return "simpleBook";
    }

}
