package com.xbrain.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import com.xbrain.loja.domain.Address;


public interface AddressRepository extends CrudRepository<Address, Integer> {
}
