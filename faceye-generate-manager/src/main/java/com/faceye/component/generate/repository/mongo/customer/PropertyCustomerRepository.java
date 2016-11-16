package com.faceye.component.generate.repository.mongo.customer;

import org.springframework.data.repository.NoRepositoryBean;

import com.faceye.component.generate.entity.Property;
//@NoRepositoryBean
public interface PropertyCustomerRepository{

	
	public Property getPropertyByName(String name);
	
	
}
