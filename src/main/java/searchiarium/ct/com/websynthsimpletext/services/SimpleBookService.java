package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.entities.SimpleText;
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
        List<SimpleText> texts = new ArrayList<>(book.getTexts());
        texts.sort(Comparator.comparingInt(SimpleText::getNumber));
        return texts;
    }

    public boolean updateText(final String text) {
        OffsetDateTime dateTime = OffsetDateTime.now();
        SimpleText text1 = tRepository.findBy(name);
        if (text1 == null) {
            text1 = SimpleText.builder().text(text).createdDateTime(dateTime).lastEditedDateTime(dateTime).build();
        } else {
            text1.setText(text);
        }

    }

}
