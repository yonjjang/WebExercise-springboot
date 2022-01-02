package hello.hellospring.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.hellospring.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DataController {

    private SentenceService sentenceService;

    @Autowired
    public DataController (SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("get-data")
    @ResponseBody
    public DataForm getData(@RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("gender") String gender){

        DataForm dataForm = new DataForm();

        dataForm.setName(name);
        dataForm.setAge(age);
        dataForm.setGender(gender);

        return dataForm;
    }


    @PostMapping("post-data1")
    @ResponseBody
    public DataForm postData1(@RequestBody DataForm postedData){

        DataForm dataForm = new DataForm();

        //System.out.println(postedData.getName());
        dataForm.setName(postedData.getName());
        dataForm.setAge(postedData.getAge());
        dataForm.setGender(postedData.getGender());

        dataForm = postedData;
        return dataForm;
    }

    @PostMapping("post-data2")
    @ResponseBody
    public DataForm postData2(@RequestBody Map<String, String> postedData){

        DataForm dataForm = new DataForm();

        postedData.forEach((key, value) -> {
            System.out.println(key +": "+value);

            dataForm.setName(postedData.get("name"));
            dataForm.setAge(Integer.parseInt(postedData.get("age")));
            dataForm.setGender(postedData.get("gender"));
        });
        return dataForm;
    }

    @PostMapping("save-sentence")
    @ResponseBody
    public void saveSentence (@RequestBody Map<String, String> inputSentence) {

        String dtoSentence = "";

        dtoSentence = inputSentence.get("sentence");

        sentenceService.save(dtoSentence);
    }

    @PostMapping("view-sentence")
    @ResponseBody
    public String viewSentence (@RequestBody Map<String, String> inputId) {

        Long dto = 0L;

        dto = Long.parseLong(inputId.get("id"));

        return sentenceService.view(dto);
    }

    @PostMapping("update-sentence")
    @ResponseBody
    public void updateSentence (@RequestBody Map<String, String> inputId) {

        SentenceData dto = new SentenceData();

        dto.setId(Long.parseLong(inputId.get("id")));
        dto.setSentence(inputId.get("sentence"));

        sentenceService.update(dto);
    }

    @PostMapping("delete-sentence")
    @ResponseBody
    public void deleteSentence (@RequestBody Map<String, String> inputId) {

        Long dto = 0L;

        dto = Long.parseLong(inputId.get("id"));

        sentenceService.delete(dto);
    }


}
