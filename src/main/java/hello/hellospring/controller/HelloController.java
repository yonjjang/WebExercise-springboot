package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /** @ResponseBody 미사용
      * viewResolver 동작 - 스프링 부트 템플릿 엔진 기본 viewName 매핑
      * viewResolver가 return 값인 viewName에 해당하는 template을 띄워줌
      * return 값: String viewName (resources:templates/ +{ViewName}+ .html)
      **/

    @GetMapping("hello")
    // Get 방식 호출 파라미터 없음
    public String hello (Model model){
        model.addAttribute("data", "hello!!");
        return "hello";

    }

    @GetMapping("hello-param")
    // Get 방식 호출 파라미터 있음 (@RequestParam 사용, required의 default 값: true)
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    /** @ResponseBody 사용 <API 방식>
      * HttpMessageConverter 동작 - HTTP의 BODY에 문자 내용을 직접 반환
      * HttpMessageConverter가 return 타입에 맞는 converter 사용하여 데이터 변환 (ex. Object -> Json / String -> String)
      * return 값: 모든 타입의 데이터
      **/

    @GetMapping("hello-api-str")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        /* xml 형식으로 반환 */
        // return "<html>hello " + name + "</html>";

        return "hello " + name; // String을 그대로 반환
    }

    @GetMapping("hello-api-obj")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello; // 객체 내 데이터의 key와 value 값을 json 형식으로 반환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
