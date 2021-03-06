package com.faceye.component.exam.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.faceye.component.exam.entity.UseCase;
import com.faceye.component.exam.repository.mongo.customer.UseCaseCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * UseCase 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface UseCaseGenRepository extends BaseMongoRepository<UseCase,Long>,UseCaseCustomerRepository  {
	 
	
}/**@generate-repository-source@**/
