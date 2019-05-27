package com.xbrain.loja.services;

import java.util.List;

import com.xbrain.loja.domain.Address;

public interface AddressService {

	public List<Address> listAll();
	public Address getById(Integer id) ;
	public Address saveOrUpdate(Address object);
	public void delete(Integer id);
	public void sendOrderMessage(String id);
}
