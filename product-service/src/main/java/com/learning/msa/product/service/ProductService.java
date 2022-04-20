package com.learning.msa.product.service;

import com.learning.msa.product.entity.Product;
import com.learning.msa.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product)
    {
        return productRepository.save(product);
    }
}
