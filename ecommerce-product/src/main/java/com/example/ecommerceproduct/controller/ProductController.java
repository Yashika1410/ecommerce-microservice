package com.example.ecommerceproduct.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceproduct.services.ProductDetailsService;
import com.example.ecommerceproduct.models.Product;

@RestController
@RequestMapping("/api/v1/product_details/")
public class ProductController {
    ProductDetailsService productService=new ProductDetailsService();
    @GetMapping("{product_id}")
    public Product getProduct(@PathVariable int product_id) {

        return productService.getProduct(product_id);
    }
}
