package com.example.ecommerceadmin.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerceadmin.models.Product;
import com.example.ecommerceadmin.services.ProductService;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    ProductService productService = new ProductService();
    @GetMapping("hello")
    public  String hello(){
        return "Hello admin";
    }
    @PostMapping("add_product")
    public String addProduct(@RequestBody Product product) {
        

        return productService.addProduct(product);
    }
    @DeleteMapping("delete_product/{product_id}")
    public String deleteProduct(@PathVariable int product_id){
        
        return productService.deleteProduct(product_id);
    }
    @GetMapping("get_product/{product_id}")
    public Product getProduct(@PathVariable int product_id){
        
        return productService.getProduct(product_id);
    }
    @GetMapping("list_product")
    public List<Product> getAllProducts(){
        
        return productService.getAllProducts();
    }
    @PutMapping("update_product/{product_id}")
    public Product updateProduct(@PathVariable int product_id,@RequestBody Product product){
        return productService.updateProduct(product, product_id);
    }
}
