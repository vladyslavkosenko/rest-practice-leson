package com.example.restpracticeleson.http;

import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/one")
public class MappingExampleController {

    @GetMapping("/one")
    public String get() {
        return "Mapped with @GetMapping";
    }

    @PostMapping("/one")
    public String post() {
        return "Mapped with @PostMapping";
    }

    @PutMapping("/one")
    public String put() {
        return "Mapped with @PutMapping";
    }

    @PatchMapping("/one")
    public String patch() {
        return "Mapped with @PatchMapping";
    }

    @DeleteMapping("/one")
    public String delete() {
        return "Mapped with @DeleteMapping";
    }

    @RequestMapping(path = "/two", method = RequestMethod.GET)
    public String get2() {
        return "Mapped with @RequestMapping";
    }

    @PostMapping("/two")
    public String two() {
        return "Mapped with @PostMapping('/two')";
    }

}