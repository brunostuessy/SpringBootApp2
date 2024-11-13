package io.brunostuessi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/")
    Greeting home() {
        return new Greeting("Hello World!");
    }

}
