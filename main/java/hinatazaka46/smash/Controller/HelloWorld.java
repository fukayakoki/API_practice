package hinatazaka46.smash.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping(value = "/hello")
    public String hello(){
        return "Hello hinatazaka";
    }
}