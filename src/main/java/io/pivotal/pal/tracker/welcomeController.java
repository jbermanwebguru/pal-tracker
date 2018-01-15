package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeController {

    @GetMapping("/")
    public String sayHellow() {
        return "hello";
    }

}
