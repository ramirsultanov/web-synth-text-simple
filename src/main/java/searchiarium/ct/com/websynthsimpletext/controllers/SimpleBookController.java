package searchiarium.ct.com.websynthsimpletext.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleText;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.services.SimpleBookService;
import searchiarium.ct.com.websynthsimpletext.services.UserService;

import java.util.List;

@Controller
public class SimpleBookController {

    @Autowired
    private SimpleBookService bService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ROLE_USER')") // CHECK ME
    @GetMapping("/book")
    public String simpleBook(@RequestParam final String b, Model model) {
        if (b != null) {
            SimpleBook book = bService.getSimpleBook(b);
            if (book == null) {
                return "notFoundSimpleBook";
            }
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userService.find(username);
            if (user.getTeam() != book.getOwner()) {
                model.addAttribute("becauseOf", "user does not belong to the owner team");
                return "denied";
            } else {
                List<SimpleText> textsBefore = bService.getSimpleTextsOrderByNumberBeforeUserSimpleText(book, user);
                List<SimpleText> textsAfter = bService.getSimpleTextOrderByNumberAfterUserSimpleText(book, user);
                SimpleText textEqual = bService.getSimpleText(book, user);
                model.addAttribute("book", book);
                model.addAttribute("textsBefore", textsBefore);
                model.addAttribute("textsAfter", textsAfter);
                model.addAttribute("textEqual", textEqual);
            }
        } else {
            return "invalidSimpleBook";
        }
        return "simpleBook";
    }

}
