package io.brunostuessi.web.controller;

import io.brunostuessi.web.dto.GreetingDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/")
    GreetingDto home() {
        return new GreetingDto("Hello World!");
    }

}
