package com.micro.product.service;

import java.util.List;
import java.util.Optional;

import com.micro.product.model.Product;

public interface ProductService {

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

}