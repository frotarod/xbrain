package com.xbrain.loja.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.loja.XbrainApplication;
import com.xbrain.loja.domain.Product;
import com.xbrain.loja.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private RabbitTemplate rabbitTemplate;
    

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              RabbitTemplate rabbitTemplate) {
        this.productRepository = productRepository;

        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Set<Product> getById(Set<Product> ids){
    	Set<Product> products = new LinkedHashSet<Product>();
    	for (Product integer : ids) {
    		products.add(getById(integer.getId()));
		}
    	return products;
    }
    
    @Override
    public Product saveOrUpdate(Product product) {

        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);

    }


}
