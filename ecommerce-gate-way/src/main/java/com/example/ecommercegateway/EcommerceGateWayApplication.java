package com.example.ecommercegateway;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.ecommercegateway.filter.*;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
public class EcommerceGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceGateWayApplication.class, args);
	}


	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
