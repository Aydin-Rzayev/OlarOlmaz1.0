package com.OlarOlmaz.OlarOlmaz10;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.OlarOlmaz.OlarOlmaz10.models.ProductModel;;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, Integer> {

}
