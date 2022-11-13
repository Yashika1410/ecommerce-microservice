package com.example.ecommerceadmin.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.example.ecommerceadmin.models.Product;

public class ProductService {
   
    private String url="http://json-db:5000/products";

    public String addProduct(Product product){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
        Product newProduct=restTemplate.exchange(url, HttpMethod.POST, entity, Product.class).getBody();
        return "Successfully added Product"+ newProduct.toString();
   }
   public String deleteProduct(int id){
     RestTemplate restTemplate = new RestTemplate();
     restTemplate.delete(url+"/"+id);
    return "Successfully delete product whoes id is "+id;
   }
   public Product updateProduct(Product product,int id){
     RestTemplate restTemplate = new RestTemplate();
     HttpHeaders headers = new HttpHeaders();
     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
     Product updateProduct= restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, Product.class).getBody();
     return updateProduct;
   }
   public List<Product> getAllProducts(){
     RestTemplate restTemplate = new RestTemplate();
     HttpHeaders headers = new HttpHeaders();
     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     final List<Product> listProduct =(List<Product>) restTemplate.getForObject(url, List.class, headers);
    return listProduct;
   }
   
   public Product getProduct(int id) {
     RestTemplate restTemplate = new RestTemplate();
     HttpHeaders headers = new HttpHeaders();
     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     final Product loadProduct =  restTemplate.getForObject(url + "/" + id, Product.class, headers);
     return loadProduct;
   }

        
    }
    

