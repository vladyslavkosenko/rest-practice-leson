package com.example.restpracticeleson.http;
import com.example.restpracticeleson.rest.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@RestController
public class ResponseExampleController {

    @GetMapping("/no-content")
    public void noContentResponse() {
    }

    @GetMapping("/string")
    public String stringResponse() {
        return "my string";
    }

    @GetMapping("/json")
    public Product json() {
        Product product = new Product();
        product.setName("Mars");
        product.setPrice(BigDecimal.TEN);
        return product;
    }

    @GetMapping("/response-entity")
    public ResponseEntity<Product> responseEntity() {
        Product product = new Product();
        product.setName("Mars");
        product.setPrice(BigDecimal.TEN);
        return ResponseEntity.ok(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/status-no-content")
    public void statusNoContent() {
    }

    @GetMapping("/status-no-content-using-response-entity")
    public ResponseEntity<Void> statusNoContentUsingEntity() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/response-headers")
    public ResponseEntity<Void> responseHeader() {
        return ResponseEntity.noContent()
                .header("my-header", "this is header")
                .build();
    }

    @GetMapping("/servlet")
    public void responseHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getMethod() + " " + request.getServletPath());
        response.setHeader("my-header", "this is header from HttpServletResponse");
        response.setStatus(500);
        response.getOutputStream().write("hello".getBytes());
    }

}