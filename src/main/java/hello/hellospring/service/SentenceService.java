package hello.hellospring.service;

import hello.hellospring.controller.SentenceData;
import hello.hellospring.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceService {

    private SentenceRepository sentenceRepository;

    @Autowired
    public SentenceService (SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    public void save (String sentence) {
        sentenceRepository.saveSentence(sentence);
    }

    public String view (Long id) {

        //SentenceData sentenceData = new SentenceData();

        String sentence = sentenceRepository.viewSentence(id);

        return sentence;
    }

    public void update (SentenceData dto) {
        sentenceRepository.updateSentence(dto);
    }

    public void delete(Long id) {
        sentenceRepository.deleteSentence(id);
    }

}
