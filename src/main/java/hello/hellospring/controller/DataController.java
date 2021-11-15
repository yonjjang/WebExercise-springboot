package hello.hellospring.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DataController {

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

}
