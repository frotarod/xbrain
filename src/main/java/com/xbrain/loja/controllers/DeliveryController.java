package com.xbrain.loja.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.xbrain.loja.domain.Delivery;
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.services.AddressService;
import com.xbrain.loja.services.DeliveryService;
import com.xbrain.loja.services.OrderService;


@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private static final Logger log = LogManager.getLogger(DeliveryController.class);

    @Autowired
    private DeliveryService serive;
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/all")
    public String list(){
    	
    	List<Order> list = orderService.listAll();
    	
    	List<Delivery> senders = serive.send(list);	
    	serive.receiveOrderMessage();
    	
        return new Gson().toJson(senders);
    }


}
