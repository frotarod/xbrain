package com.xbrain.loja.services;

import java.util.List;

import com.xbrain.loja.domain.Client;

public interface ClientService {

	public List<Client> listAll();
	public Client getById(Integer id) ;
	public Client saveOrUpdate(Client object);
	public void delete(Integer id);
	public void sendOrderMessage(String id);
}
