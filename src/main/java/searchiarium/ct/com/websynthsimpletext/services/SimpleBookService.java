package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.models.SimpleBook;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleBookRepository;
import searchiarium.ct.com.websynthsimpletext.repositories.SimpleTextRepository;

@Service
public class SimpleBookService {

    @Autowired
    SimpleBookRepository bRepository;

    public SimpleBook getSimpleBook(final String name) {
        return bRepository.findByShortNameEquals(name);
    }

}
