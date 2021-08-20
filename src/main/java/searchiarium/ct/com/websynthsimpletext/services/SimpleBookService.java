package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.entities.Role;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleText;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.models.SimpleTextUpdateDto;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleBookRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleTextRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.UserRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class SimpleBookService {

    @Autowired
    SimpleBookRepository bRepository;

    @Autowired
    SimpleTextRepository tRepository;

    @Autowired
    UserRepository userRepository;

    public SimpleBook getSimpleBook(final String name) {
        return bRepository.findByShortNameEquals(name);
    }

    public List<SimpleText> getSimpleTextsOrderByNumber(final SimpleBook book) {
        List<SimpleText> texts = book.getTexts();
        texts.sort(Comparator.comparingInt(SimpleText::getNumber));
        return texts;
    }

    public List<SimpleText> getSimpleTextsOrderByNumberBeforeUserSimpleText(final SimpleBook book, final User user) {
        List<SimpleText> texts = book.getTexts();
        texts.sort(Comparator.comparingInt(SimpleText::getNumber));
        int num = -1;
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getOwner() == user) {
                num = i;
                break;
            }
        }
        if (num == -1) {
            throw new RuntimeException("There is no user's text, so, can not get texts before user's");
        }
        return texts.subList(0, num);
    }

    public List<SimpleText> getSimpleTextOrderByNumberAfterUserSimpleText(final SimpleBook book, final User user) {
        List<SimpleText> texts = book.getTexts();
        texts.sort(Comparator.comparingInt(SimpleText::getNumber));
        int num = -1;
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getOwner() == user) {
                num = i;
                break;
            }
        }
        if (num == -1) {
            throw new RuntimeException("There is no user's text, so, can not get texts before user's");
        }
        return texts.subList(num, texts.size());
    }

    public SimpleText getSimpleText(final SimpleBook book, final User user) {
        return book.getSimpleText(user);
    }

    public boolean addBook(String name, String username) {
        if (bRepository.findByShortNameEquals(name) == null) {
            User user = userRepository.findByUsername(username);
            user.setRole(Role.OWNER);
            SimpleBook book = SimpleBook.builder()
                    .creator(user.getTeam())
                    .owner(user.getTeam())
                    .shortName(name)
                    .build();
            bRepository.save(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean addBook(String name, String ownerUsername, String[] usernames) {
        if (bRepository.findByShortNameEquals(name) == null) {
            User user = userRepository.findByUsername(ownerUsername);
            user.setRole(Role.OWNER);
            SimpleBook book = SimpleBook.builder()
                    .creator(user.getTeam())
                    .owner(user.getTeam())
                    .shortName(name)
                    .build();
            User[] users = new User[usernames.length];
            SimpleText[] texts = new SimpleText[usernames.length];
            for (int i = 0; i < usernames.length; i++) {
                users[i] = userRepository.findByUsername(usernames[i]);
                texts[i] = SimpleText.builder()
                        .book(book)
                        .owner(users[i])
                        .text("")
                        .createdDateTime(OffsetDateTime.now())
                        .lastEditedDateTime(OffsetDateTime.now())
                        .number(i)
                        .build();
            }
            book.setTexts(Arrays.asList(texts));
            bRepository.save(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateText(SimpleTextUpdateDto dto, String username) {
        User user = userRepository.findByUsername(username);
        if (dto.bookId != null && bRepository.findById(dto.bookId).isPresent()) {
            SimpleBook book = bRepository.findById(dto.bookId).get();
            for (SimpleText text : book.getTexts()) {
                if (text.getOwner() == user) {
                    text.setText(dto.text);
                    text.setLastEditedDateTime(OffsetDateTime.now());
                    bRepository.save(book);
//                    return true;
                    break;
                }
            }
//            SimpleText text = SimpleText.builder()
//                    .book(book)
//                    .owner(user)
//                    .text(dto.text)
//                    .createdDateTime(OffsetDateTime.now())
//                    .lastEditedDateTime(OffsetDateTime.now())
//                    .number()
//                    .build();
            return true;
        } else {
            return false;
        }
    }

//    public boolean updateText(final String text) {
//        OffsetDateTime dateTime = OffsetDateTime.now();
//        SimpleText text1 = tRepository.findBy(name);
//        if (text1 == null) {
//            text1 = SimpleText.builder().text(text).createdDateTime(dateTime).lastEditedDateTime(dateTime).build();
//        } else {
//            text1.setText(text);
//        }
//
//    }

}
