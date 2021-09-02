package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("hello/")
public class HelloController {

    //    @RequestMapping(value = "say", method = RequestMethod.GET)
    @GetMapping("say")
    @ResponseBody
    public String sayHello(@RequestParam(value = "age", defaultValue = "10") int age) {//localhost:9999/myapp/hello/say
        return "Hello, MVC " + age;
    }

    @GetMapping("speak/{id}")
    @ResponseBody
    public String speak(@PathVariable("id") int id) {//localhost:9999/myapp/hello/say
        return "Hello, MVC " + id;
    }

    @GetMapping("send")
    public ModelAndView send(ModelAndView mav) {

        mav.setViewName("index");
        mav.addObject("message", "Hello, Max");

        return mav;
    }
}
