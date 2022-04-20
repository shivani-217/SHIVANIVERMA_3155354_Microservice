package com.learning.msa.product.controller;

import com.learning.msa.product.entity.Product;
import com.learning.msa.product.repository.ProductRepository;
import com.learning.msa.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Product> findAll()
    {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    private Product findByCode(@PathVariable final Long id)
    {
        Optional<Product> product = productRepository.findById(id);
        return product.isPresent()?product.get():null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody List<Product> product)
    {
        productRepository.saveAll(product);
        return "Services created successfully";
    }


}
