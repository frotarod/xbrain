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
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.services.AddressService;
import com.xbrain.loja.services.OrderService;
import com.xbrain.loja.services.ProductService;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    private static final Logger log = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/all")
    public String list(){
        return new Gson().toJson(orderService.listAll());
    }

    @RequestMapping("/show/{id}")
    public String get(@PathVariable String id){
        return new Gson().toJson(orderService.getById(Integer.valueOf(id)));
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(@RequestBody Order order){

    	addressService.saveOrUpdate(order.getAddress());
    	order.setProducts(productService.getById(order.getProducts()));
    	order.setPrices(orderService.prices(order.getProducts()));
    	
    	Order savedOrder = orderService.saveOrUpdate(order);

        return new Gson().toJson(savedOrder);
    }
}
