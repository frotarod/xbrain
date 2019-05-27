package com.xbrain.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import com.xbrain.loja.domain.Client;


public interface ClientRepository extends CrudRepository<Client, Integer> {
}
