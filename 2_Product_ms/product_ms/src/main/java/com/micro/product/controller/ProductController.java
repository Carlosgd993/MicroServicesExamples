package com.micro.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.product.dto.ProductDto;
import com.micro.product.model.Product;
import com.micro.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        var optionalProduct = productService.getProductById(id);
        if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice());
        } else {
            return null; // or throw an exception
        }
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        var products = productService.getAllProducts();
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        var product = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice());
        var newProduct = productService.createProduct(product);
        return new ProductDto(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice());
    }

}
