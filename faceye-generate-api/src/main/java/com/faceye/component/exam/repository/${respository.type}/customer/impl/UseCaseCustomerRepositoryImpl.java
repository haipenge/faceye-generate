package com.faceye.component.exam.repository.mongo.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.faceye.component.exam.entity.UseCase;
import com.faceye.component.exam.repository.mongo.customer.UseCaseCustomerRepository;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * UseCase 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public class UseCaseCustomerRepositoryImpl implements UseCaseCustomerRepository {
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private MongoOperations mongoOperations = null;
	
}/**@generate-repository-source@**/
