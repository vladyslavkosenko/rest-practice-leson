package com.example.restpracticeleson.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingExampleController2 {

    @GetMapping(path = "/mapping", params = "one")
    public String one() {
        return "Request with query param one";
    }

    @GetMapping(path = "/mapping", params = "two")
    public String two() {
        return "Request with query param two";
    }

    @GetMapping(path = "/mapping", params = "three=a")
    public String threeA() {
        return "Request with query param three=a";
    }

    @GetMapping(path = "/mapping", params = "three=b")
    public String threeB() {
        return "Request with query param three=b";
    }

    @GetMapping(path = "/mapping", headers = "myHeader")
    public String header() {
        return "Request with header myHeader";
    }

}