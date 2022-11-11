package com.example.ecommerceproduct.services;

import java.util.Arrays;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.example.ecommerceproduct.models.Product;
public class ProductDetailsService{
    private String url = "http://localhost:5000/products";

    public Product getProduct(int id) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final Product loadProduct = restTemplate.getForObject(url + "/" + id, Product.class, headers);
        return loadProduct;
    }
}