package com.springboot.app.springboot_crud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.springboot_crud.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    boolean existsBySku(String sku);
}
