package com.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.springboot_crud.models.Product;
import com.springboot.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProcductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {

       Optional<Product> optionalProduct = repository.findById(id);

       if(optionalProduct.isPresent()){
        Product productDb = optionalProduct.orElseThrow();
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setDescription(product.getDescription());
        productDb.setSku(product.getSku());
        return Optional.of(repository.save(productDb));
       }

        return optionalProduct;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        optionalProduct.ifPresent(productDb ->{ 
            repository.delete(productDb);
        });

        return optionalProduct;
    }

    @Override
    @Transactional(readOnly= true)
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }
    

}
