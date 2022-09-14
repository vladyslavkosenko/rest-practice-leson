package com.example.restpracticeleson.stateless;


import com.example.restpracticeleson.rest.Product;
import com.example.restpracticeleson.rest.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StatefulProductController {

    @Autowired
    private ProductRepository repository;

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    void init() {
        repository.findAll().forEach(products::add);
    }

    @GetMapping
    public List<Product> products() {
        return products; // NOT OK
        //Remark: it's ok if products would be some temporal in-memory cache instead of List
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        products.add(product);
        return repository.save(product);
    }

}