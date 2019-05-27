package com.xbrain.loja.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.loja.XbrainApplication;
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.domain.Product;
import com.xbrain.loja.repositories.OrderRepository;
import com.xbrain.loja.repositories.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	 	private OrderRepository orderRepository;
	 	private ProductRepository productRepository;
	    private RabbitTemplate rabbitTemplate;
	    private Queue queue;
	    
	    @Autowired
	    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate,
	    		ProductRepository productRepository, Queue queue) {
	        this.orderRepository = orderRepository;
	        this.rabbitTemplate = rabbitTemplate;
	        this.productRepository = productRepository;
	        this.queue = queue;
	    }

	    @Override
	    public List<Order> listAll() {
	        List<Order> orders = new ArrayList<>();
	        orderRepository.findAll().forEach(orders::add); 
	        return orders;
	    }

	    @Override
	    public Order getById(Integer id) {
	        return orderRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Order saveOrUpdate(Order order) {
	    	orderRepository.save(order);
	    	sendOrderMessage(order);
	        return order;
	    }

	    @Override
	    public void delete(Integer id) {
	    	orderRepository.deleteById(id);

	    }
	    
	  
	    
	    public BigDecimal prices(Set<Product> list) {
	    	BigDecimal tot = BigDecimal.ZERO;
	    	for (Product product : list) {
	    		tot.add(product.getPrice());
			}
	    	
	    	return tot;
	    }

	    @Override
	    public void sendOrderMessage(Order order) {
	        rabbitTemplate.convertAndSend(this.queue.getName(), order.getId());
	    }
}
