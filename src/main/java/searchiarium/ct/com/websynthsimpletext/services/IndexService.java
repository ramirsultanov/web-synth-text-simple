package searchiarium.ct.com.websynthsimpletext.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchiarium.ct.com.websynthsimpletext.models.Thought;
import searchiarium.ct.com.websynthsimpletext.repositories.IndexRepository;

import java.util.*;

@Service
public class IndexService {

    final Random random = new Random();

    @Autowired
    IndexRepository indexRepository;

    private Thought addQuotes(Thought thought) {
        thought.setText("\"" + thought.getText() + "\"");
        return thought;
    }

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
            String s = thought.getText();
            s = s.replaceAll("[^a-zA-Z0-9\\s]", "");
            s = s.toLowerCase();
            s = " " + s + " ";
            for (String key : keys) {
                if (s.contains(key)) {
                    result.add(thought);
                    break;
                }
            }
        }
        return result;
    }

    public List<Thought> getOneThoughtAsList(String keywords) {
        if (keywords.equals("")) {
            List<Thought> thoughts = indexRepository.findAll();
            return Collections.singletonList(addQuotes(thoughts.get(random.nextInt(thoughts.size()))));
        } else {
            String[] keys = keywords.split(" ");
            for (int i = 0; i < keys.length; i++) {
                keys[i] = " " + keys[i] + " ";
            }
            List<Thought> result = new ArrayList<>();
            List<Thought> thoughts = indexRepository.findAll();
            for (Thought thought : thoughts) {
                String s = thought.getText();
                s = s.replaceAll("[^a-zA-Z0-9\\s]", "");
                s = s.toLowerCase();
                s = " " + s + " ";
                for (String key : keys) {
                    if (s.contains(key)) {
                        result.add(thought);
                        break;
                    }
                }
            }
            if (result.isEmpty()) {
                return new ArrayList<>();
            } else {
                final Thought thought = result.get(random.nextInt(result.size()));
                return Collections.singletonList(addQuotes(thought));
            }
        }
    }

}
