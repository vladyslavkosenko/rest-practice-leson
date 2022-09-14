package com.example.restpracticeleson.http;

import com.example.restpracticeleson.rest.Product;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class RequestExampleController {

    @PostMapping("/path/{id}/{someName}")
    public String pathVariable(@PathVariable Long id,
                               @PathVariable("someName") Long name) {
        return "Request with path variable id " + id + " and name " + name;
    }

    @PostMapping("/parameters")
    public String queryParameter(@RequestParam String parameterOne,
                                 @RequestParam("parameter-two") String parameterTwo,
                                 @RequestParam(required = false) String optional) {
        return "Request with query parameter one " + parameterOne
                + " and parameter two " + parameterTwo
                + " and optional " + optional;
    }

    @PostMapping("/header")
    public String header(@RequestHeader String myHeader,
                         @RequestHeader("my-header") String myHeader2,
                         @RequestHeader(required = false) String optional) {
        return "Request with myHeader " + myHeader
                + " and my-header " + myHeader2
                + " and optional " + optional;
    }

    @PostMapping("/raw-body")
    public String rawBody(@RequestBody String body) {
        return "Request body is " + body;
    }

    @PostMapping("/object")
    public String rawBody(@RequestBody Product product) {
        return "Request body is " + product;
    }

    @PostMapping("/form-data")
    public String formData(@RequestParam("file") MultipartFile file,
                           @RequestParam("text") String text) throws IOException {
        return "File: " + new String(file.getBytes(), StandardCharsets.UTF_8)
                + "\nText: " + text;
    }

    @PostMapping(value = "/form-urlencoded")
    public String formUrlencoded(@RequestParam("text") String text) throws IOException {
        return "Text: " + text;
    }

    @PostMapping(value = "/binary")
    public String binary(@RequestBody byte[] body) throws IOException {
        return "File: " + new String(body, StandardCharsets.UTF_8);
    }

    @PostMapping("/request-entity")
    public String entity(RequestEntity<Product> requestEntity) {
        return "RequestEntity is " + requestEntity;
    }

}