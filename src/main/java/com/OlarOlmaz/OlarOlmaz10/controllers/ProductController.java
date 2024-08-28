package com.OlarOlmaz.OlarOlmaz10.controllers;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.OlarOlmaz.OlarOlmaz10.ProductRepository;
import com.OlarOlmaz.OlarOlmaz10.models.ProductModel;

@RestController
@RequestMapping(path = "/app")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping(path = "/products/all")
    public @ResponseBody Iterable<ProductModel> getAll(){
        return productRepository.findAll();
    } 

    @GetMapping(path = "/products?q={name}")
    
    public @ResponseBody List<ProductModel> getByName (@PathVariable String name) throws Exception, HttpClientErrorException{
        Iterable<ProductModel> productsIterable = productRepository.findAll();
        List<ProductModel> productsByName = new ArrayList<ProductModel>();
        while(productsIterable.iterator().hasNext()){
            ProductModel product = productsIterable.iterator().next();
            if(product.getProductName().contains(name)){
                productsByName.add(product);
            }
        }                                                           
        if(productsByName.isEmpty()){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(404));
        }
        else{
            return productsByName;
        }
    }
   

   }


