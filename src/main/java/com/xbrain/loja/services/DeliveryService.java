package com.xbrain.loja.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.xbrain.loja.domain.Delivery;
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.domain.Product;

public interface DeliveryService {

    public List<Delivery> listAll();
    public Delivery getById(Integer id);
    public Delivery saveOrUpdate(Delivery d);
    public void delete(Integer id);    
    public BigDecimal prices(Set<Product> list);
    public List<Delivery>  send(List<Order> list);
    public void receiveOrderMessage();
    
}
