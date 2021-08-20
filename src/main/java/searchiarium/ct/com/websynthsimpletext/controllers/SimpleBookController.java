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
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleText;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.models.SimpleTextUpdateDto;
import searchiarium.ct.com.websynthsimpletext.services.SimpleBookService;
import searchiarium.ct.com.websynthsimpletext.services.UserService;
import searchiarium.ct.com.websynthsimpletext.validators.SimpleBookNameValidator;

import java.util.List;

@Controller
public class SimpleBookController {

    @Autowired
    private SimpleBookService bService;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpleBookNameValidator simpleBookNameValidator;

    @PreAuthorize("hasAnyAuthority('USER', 'OWNER', 'ADMIN')") // CHECK ME
    @GetMapping("/book") // change b => &13
    public String simpleBook(@RequestParam final String id, Model model) {
        if (model.containsAttribute("error")) {
            return "error";
        } else if (id != null) {
            SimpleBook book = bService.getSimpleBook(id);
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
    @PreAuthorize("hasAnyAuthority('USER', 'OWNER', 'ADMIN')")
    @PostMapping("/book")
    public String simpleBook(final SimpleTextUpdateDto dto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (bService.updateText(dto, username)) {
            return "redirect:/book?id" + dto.bookId;
        } else {
            model.addAttribute("error", "update was not successful");
            return "error";
        }
    }

    @PreAuthorize("hasAnyAuthority('USER', 'OWNER', 'ADMIN')")
    @GetMapping("/createBook")
    public String createSimpleBook() {
        return "createSimpleBook";
    }
    @PreAuthorize("hasAnyAuthority('USER', 'OWNER', 'ADMIN')")
    @PostMapping("/createBook")
    public String createSimpleBook(String name, Model model) {
        if (simpleBookNameValidator.validate(name)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            if (bService.addBook(name, username)) {
                return "redirect:/book?id="+name;
            } else {
                model.addAttribute("error", name + " is already exists");
                return "redirect:/book?";
            }
        } else {
            model.addAttribute("error", name + " is not viable");
            return "redirect:/book?";
        }
    }
    @PreAuthorize("hasAnyAuthority('USER', 'OWNER', 'ADMIN')")
    @PostMapping("/createBook")
    public String createSimpleBook(String name, String[] usernames, Model model) {
        if (simpleBookNameValidator.validate(name)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            if (bService.addBook(name, username, usernames)) {
                return "redirect:/book?id="+name;
            } else {
                model.addAttribute("error", name + " is already exists");
                return "redirect:/book?";
            }
        } else {
            model.addAttribute("error", name + " is not viable");
            return "redirect:/book?";
        }
    }

}
