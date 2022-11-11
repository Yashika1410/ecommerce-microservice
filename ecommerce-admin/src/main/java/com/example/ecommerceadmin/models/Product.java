package com.example.ecommerceadmin.models;

import lombok.Data;

@Data
public class Product {
    
    private int id=0;
    private String name;
    private String size;
    private float price;
    private String description;

}
