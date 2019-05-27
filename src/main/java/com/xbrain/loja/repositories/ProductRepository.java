package com.xbrain.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import com.xbrain.loja.domain.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {
}
