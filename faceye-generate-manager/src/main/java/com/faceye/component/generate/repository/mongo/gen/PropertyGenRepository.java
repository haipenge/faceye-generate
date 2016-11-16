package com.faceye.component.generate.repository.mongo.gen;

import org.springframework.data.repository.NoRepositoryBean;

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.customer.PropertyCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Property 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@NoRepositoryBean
public interface PropertyGenRepository extends BaseMongoRepository<Property,Long>{
	
}/**@generate-repository-source@**/
