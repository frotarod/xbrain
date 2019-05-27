package com.xbrain.loja.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.xbrain.loja.domain.Client;
import com.xbrain.loja.services.AddressService;
import com.xbrain.loja.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	private static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AddressService addressService;
	

	@RequestMapping("/getClients")
	public String getClients() {
		return new Gson().toJson(clientService.listAll());
	}
	
	@GetMapping("/getClient/{id}")
	public String getClient(@PathVariable(value = "id") Integer id) {
		return new Gson().toJson(clientService.getById(id));
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
	public String saveOrupdate(Client client){
		logger.info("params, client = {}", client);
		client.setAddress(addressService.saveOrUpdate(client.getAddress()));
		return new Gson().toJson(clientService.saveOrUpdate(client));
	}
	

}
