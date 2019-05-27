package com.xbrain.loja.listener;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.xbrain.loja.XbrainApplication;
import com.xbrain.loja.domain.Delivery;
import com.xbrain.loja.domain.Order;
import com.xbrain.loja.repositories.DeliveryRepository;
import com.xbrain.loja.repositories.OrderRepository;

//@Component
public class MessageListener {
	
//	private OrderRepository orderRepository;
//	private DeliveryRepository deliveryRepository;
	
//	private static final Logger log = LogManager.getLogger(MessageListener.class);
	
  //  public MessageListener(OrderRepository orderRepository, DeliveryRepository deliveryRepository) {
    //    this.orderRepository = orderRepository;
      //  this.deliveryRepository = deliveryRepository;
    //}
    
    //@RabbitListener(queues = {XbrainApplication.SFG_MESSAGE_QUEUE})
    //public void receiveMessage(@Payload Map<String,String> fileBody) {
      //  log.info("Received <" + fileBody + ">");
        //Integer id = Integer.valueOf(fileBody.get("id"));
        //Order order = orderRepository.findById(id).orElse(null);
        //Delivery delivery = new Delivery();
        //delivery.setAddress(order.getAddress());
       // delivery.setOrde(order);
       // this.deliveryRepository.save(delivery);
     //   log.info("Message processed...");
   // }

}
