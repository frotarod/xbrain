package com.xbrain.loja.services;

import java.util.List;
import java.util.Set;

import com.xbrain.loja.domain.Product;


public interface ProductService {

    List<Product> listAll();

    Product getById(Integer id);

    Product saveOrUpdate(Product product);

    void delete(Integer id);
    
    public Set<Product> getById(Set<Product> ids);
}
