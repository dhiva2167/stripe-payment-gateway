package payment_gateway_integration.Payment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private String name;
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
  
     @GetMapping("/greet")
    public String successPage(@RequestParam(name = "name", defaultValue = "Value") String name) {

        return "Hello, " + name + "!"; 
    }
    @PostMapping("/greet-post")
    public String Contoller(@RequestBody GreetRequest request) {
        return "Hello, " + request.getName() + "!";
           
    }

    
    
}
