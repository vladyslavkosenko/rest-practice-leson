package com.example.restpracticeleson.cache;


import com.example.restpracticeleson.rest.Product;
import com.example.restpracticeleson.rest.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/cache-control/{id}")
    public ResponseEntity<Product> cacheControl(@PathVariable Long id) {
        System.out.println("cacheControl() call!!!");
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(product);
    }

    @GetMapping("/etag/{id}")
    public ResponseEntity<Product> eTag(@RequestHeader(name = HttpHeaders.IF_NONE_MATCH, required = false) String ifNoneMatch,
                                        @PathVariable Long id) {
        System.out.println("eTag() call!!!");
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String version = Long.toString(product.getVersion());
        /* This check is not needed - Spring makes all the magic
        if (ifNoneMatch != null && ifNoneMatch.equals(version)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }*/
        return ResponseEntity.ok()
                .eTag(version)
                .body(product);
    }

}