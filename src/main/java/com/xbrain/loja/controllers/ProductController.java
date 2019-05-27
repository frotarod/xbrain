package com.xbrain.loja.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.xbrain.loja.domain.Product;
import com.xbrain.loja.services.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger log = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;



    @RequestMapping("/all")
    public String listProducts(){
        return new Gson().toJson(productService.listAll());
    }

    @RequestMapping("/show/{id}")
    public String getProduct(@PathVariable String id){
        return new Gson().toJson(productService.getById(Integer.valueOf(id)));
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdateProduct(Product product){

        Product savedProduct = productService.saveOrUpdate(product);

        return new Gson().toJson(savedProduct);
    }
}
