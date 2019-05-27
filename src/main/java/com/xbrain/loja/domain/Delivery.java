package com.xbrain.loja.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "delivery_table")
public class Delivery implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1553610583305584779L;

	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private Order orde;

	
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Delivery(Integer id, Address address, Order orde) {
		super();
		this.id = id;
		this.address = address;
		this.orde = orde;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order getOrde() {
		return orde;
	}

	public void setOrde(Order orde) {
		this.orde = orde;
	}
	
	

}
