package hello.hellospring.repository;

import hello.hellospring.controller.SentenceData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SentenceRepository {

    private static Map<Long, String> storage = new HashMap<>();
    private static long sequence = 0L;

    public void saveSentence(String sentence) {
        storage.put(++sequence, sentence);

        storage.forEach((key, value) -> {
            System.out.println(key +"/" +value);
        });

    }

    public void updateSentence(SentenceData dto) {

        if (storage.containsKey(dto.getId())) {
            storage.put(dto.getId(), dto.getSentence());
        }

        storage.forEach((key, value) -> {
            System.out.println(key +"/" +value);
        });
    }

    public String viewSentence(Long id) {

        String viewData = "";
        if (storage.containsKey(id)) {
            viewData = storage.get(id);
        }

        return viewData;

    }

    public void deleteSentence(Long id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
        }

        storage.forEach((key, value) -> {
            System.out.println(key +"/" +value);
        });
    }

}
