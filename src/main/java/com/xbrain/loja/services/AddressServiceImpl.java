package com.xbrain.loja.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbrain.loja.XbrainApplication;
import com.xbrain.loja.domain.Address;
import com.xbrain.loja.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	 	private AddressRepository repository;
	    private RabbitTemplate rabbitTemplate;
	    
	    @Autowired
	    public AddressServiceImpl(AddressRepository repository, RabbitTemplate rabbitTemplate) {
	        this.repository = repository;
	        this.rabbitTemplate = rabbitTemplate;
	    }

	    @Override
	    public List<Address> listAll() {
	        List<Address> list = new ArrayList<>();
	        repository.findAll().forEach(list::add); 
	        return list;
	    }

	    @Override
	    public Address getById(Integer id) {
	        return repository.findById(id).orElse(null);
	    }

	    @Override
	    public Address saveOrUpdate(Address object) {
	    	repository.save(object);
	        return object;
	    }

	    @Override
	    public void delete(Integer id) {
	    	repository.deleteById(id);

	    }

	    @Override
	    public void sendOrderMessage(String id) {
	        Map<String, String> actionmap = new HashMap<>();
	        actionmap.put("id", id);
	        log.info("Sending the index request through queue message");
	        rabbitTemplate.convertAndSend(XbrainApplication.SFG_MESSAGE_QUEUE, actionmap);
	    }
}
