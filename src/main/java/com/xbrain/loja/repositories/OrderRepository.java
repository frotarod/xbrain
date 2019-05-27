package com.xbrain.loja.repositories;

import org.springframework.data.repository.CrudRepository;

import com.xbrain.loja.domain.Order;


public interface OrderRepository extends CrudRepository<Order, Integer> {
}
