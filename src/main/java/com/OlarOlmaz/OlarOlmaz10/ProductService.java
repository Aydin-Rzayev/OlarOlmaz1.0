package com.OlarOlmaz.OlarOlmaz10;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OlarOlmaz.OlarOlmaz10.models.ProductModel;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> findAll(){
        return (List<ProductModel>) productRepository.findAll();
    }

    public List<ProductModel> findByName(String productName){
        List<ProductModel> products = (List<ProductModel>) productRepository.findAll();
        return products.stream()
                        .filter(prod -> prod.getProductName().contains(productName))
                        .collect(Collectors.toList());
    }

    public Optional<ProductModel> findById(Integer id){
        return productRepository.findById(id);
    }

    
    public boolean existsProduct(ProductModel product){
        return productRepository.findAll().equals(product);
    }

    public ProductModel saveProduct(ProductModel product){
        return productRepository.save(product);
    }

    public boolean deleteProduct(ProductModel product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }
}
