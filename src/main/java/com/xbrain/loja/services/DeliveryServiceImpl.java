package com.xbrain.loja.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.loja.XbrainApplication;
import com.xbrain.loja.domain.Delivery;
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.domain.Product;
import com.xbrain.loja.repositories.DeliveryRepository;
import com.xbrain.loja.repositories.OrderRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	private static final Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);
	
	 	private OrderRepository orderRepository;
	 	private DeliveryRepository deliveryRepository;
	    private RabbitTemplate rabbitTemplate;
	    private AddressService addressService;
	    
	    @Autowired
	    public DeliveryServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate,
	    		DeliveryRepository deliveryRepository, AddressService addressService) {
	        this.orderRepository = orderRepository;
	        this.rabbitTemplate = rabbitTemplate;
	        this.deliveryRepository = deliveryRepository;
	        this.addressService = addressService;
	    }

	    
	    public List<Delivery>  send(List<Order> list){
	    	List<Delivery> sender = new ArrayList<>();
	    	
	    	for (Order order : list) {
	    		
	    		Delivery d = new Delivery(null, order.getAddress(), order);
				sender.add(d);
				deliveryRepository.save(d);
			}
	    	return sender;
	    }
	    
	    @Override
	    public List<Delivery> listAll() {
	        List<Delivery> orders = new ArrayList<>();
	        deliveryRepository.findAll().forEach(orders::add); 
	        return orders;
	    }

	    @Override
	    public Delivery getById(Integer id) {
	        return deliveryRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Delivery saveOrUpdate(Delivery d) {
	    	deliveryRepository.save(d);
	    	
	        return d;
	    }

	    @Override
	    public void delete(Integer id) {
	    	deliveryRepository.deleteById(id);

	    }
	    
	    public BigDecimal prices(Set<Product> list) {
	    	BigDecimal tot = BigDecimal.ZERO;
	    	for (Product product : list) {
	    		tot.add(product.getPrice());
			}
	    	
	    	return tot;
	    }
	    @Override
	    public void receiveOrderMessage() {
	    	log.info("receiving the message ");
	        Object message = rabbitTemplate.receiveAndConvert(XbrainApplication.SFG_MESSAGE_QUEUE);

	        if (message != null) {
	            Integer id = (Integer) message;
	            log.info("  received message [" + id + "] ");
	            Order order = orderRepository.findById(id).orElse(null);
		        Delivery delivery = new Delivery();
		        delivery.setAddress(order.getAddress());
		        delivery.setOrde(order);
		        this.deliveryRepository.save(delivery);
	        }
	       

	    }
	 
}
