package com.xbrain.loja.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xbrain.loja.domain.Order;
import com.xbrain.loja.domain.Product;

public interface OrderService {

	public List<Order> listAll();
	 public Order getById(Integer id);
	 public Order saveOrUpdate(Order order);
	 public void delete(Integer id);
	 public void sendOrderMessage(Order order) ;
	 public BigDecimal prices(Set<Product> list);
}
