package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("name", "juyeon");
 
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);

        return "hello";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloTemplate(@RequestParam("name") String name){
        return "name " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Person helloApi(@RequestParam("name") String name){
        Person p = new Person();
        p.setName(name);

        return p;
    }


    static class Person{
        private String name;

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

}
