package com.OlarOlmaz.OlarOlmaz10.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.OlarOlmaz.OlarOlmaz10.ProductRepository;
import com.OlarOlmaz.OlarOlmaz10.models.ProductModel;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping(path = "/products/all")
    public @ResponseBody Iterable<ProductModel> getAll(){
        return productRepository.findAll();
    } 

    @GetMapping(path = "/products")
    
    public @ResponseBody List<ProductModel> getByName (@RequestParam String name) throws Exception, HttpClientErrorException{
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

    @PostMapping(path = "/products/add")
    public @ResponseBody HttpStatusCode addProduct(@RequestParam String name, @RequestParam Boolean permission, @RequestParam String ingredient) throws Exception, HttpClientErrorException{
        Iterable<ProductModel> productsIterable = productRepository.findAll();
        
        while(productsIterable.iterator().hasNext()){
            ProductModel product = productsIterable.iterator().next();
            if(product.getProductName().equalsIgnoreCase(name)){
                throw new Exception("Product already exists!");
            }
        }
        ProductModel product = new ProductModel();
        product.setProductName(name);
        product.setPermissionOfUsage(permission);
        product.setIngredient(ingredient);
        productRepository.save(product);
        return HttpStatusCode.valueOf(200);
    }

    @PutMapping(path = "/products/update/igredients?q={productName}")
    public @ResponseBody HttpStatusCode updateProductIngredients(@PathVariable String productName, @RequestParam String ingredient) throws Exception, HttpClientErrorException{
        Iterable<ProductModel> productsIterable = productRepository.findAll();
        Boolean flag = false;
        while(productsIterable.iterator().hasNext()){
            ProductModel product = productsIterable.iterator().next();
            if(product.getProductName().equalsIgnoreCase(productName)){
                product.setIngredient(ingredient);
                flag = true; 
            }
        }
        if(flag == false){
            return HttpStatusCode.valueOf(404);
        }
        else{
            return HttpStatusCode.valueOf(200);
        }

    }

    @PutMapping(path = "/products/update/permission?q={productName}")
    public @ResponseBody HttpStatusCode updateProductPermission(@PathVariable String productName, @RequestParam Boolean permission) throws Exception, HttpClientErrorException{
        Iterable<ProductModel> productsIterable = productRepository.findAll();
        Boolean flag = false;
        while(productsIterable.iterator().hasNext()){
            ProductModel product = productsIterable.iterator().next();
            if(product.getProductName().equalsIgnoreCase(productName)){
                product.setPermissionOfUsage(permission);
                flag = true; 
            }
        }
        if(flag == false){
            return HttpStatusCode.valueOf(404);
        }
        else{
            return HttpStatusCode.valueOf(200);
        }

    }

    @DeleteMapping(path = "/products/delete?q={productName}")
    public HttpStatusCode deleteProduct(@PathVariable String productName){
        Iterable<ProductModel> productsIterable = productRepository.findAll();
        Boolean flag = false;
        while(productsIterable.iterator().hasNext()){
            ProductModel product = productsIterable.iterator().next();
            if(product.getProductName().equalsIgnoreCase(productName)){
                productRepository.delete(product);
                flag = true;
            }
        }
        if(flag = true){
            return HttpStatusCode.valueOf(200);
        }
        else{
            return HttpStatusCode.valueOf(404);
        }
    }
    

    
}

