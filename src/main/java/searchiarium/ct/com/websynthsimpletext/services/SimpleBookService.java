package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleText;
import searchiarium.ct.com.websynthsimpletext.entities.User;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleBookRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleTextRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SimpleBookService {

    @Autowired
    SimpleBookRepository bRepository;

    @Autowired
    SimpleTextRepository tRepository;

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
