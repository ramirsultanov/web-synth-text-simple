package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.models.Thought;
import searchiarium.ct.com.websynthsimpletext.repositories.IndexRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    IndexRepository indexRepository;

    public List<Thought> getAllThoughts() {
        return indexRepository.findAll();
    }

    public List<Thought> getThoughts(String keywords) {
        String[] keys = keywords.split(" ");
        for (int i = 0; i < keys.length; i++) {
            keys[i] = " " + keys[i] + " ";
        }
        List<Thought> result = new ArrayList<>();
        List<Thought> thoughts = indexRepository.findAll();
        for (Thought thought : thoughts) {
            for (String key : keys) {
                if (thought.getText().contains(key)) {
                    result.add(thought);
                    break;
                }
            }
        }
        return result;
    }

}
