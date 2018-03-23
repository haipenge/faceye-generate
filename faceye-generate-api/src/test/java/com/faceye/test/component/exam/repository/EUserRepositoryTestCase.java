package com.faceye.test.component.exam.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.exam.entity.EUser;
import com.faceye.component.exam.repository.mongo.EUserRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * EUser DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class EUserRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private EUserRepository eUserRepository = null;

	@Before
	public void before() throws Exception {
		//this.eUserRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		EUser entity = new EUser();
		this.eUserRepository.save(entity);
		Iterable<EUser> entities = this.eUserRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		EUser entity = new EUser();
		this.eUserRepository.save(entity);
        this.eUserRepository.deleteById(entity.getId());
        Iterable<EUser> entities = this.eUserRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		EUser entity = new EUser();
		this.eUserRepository.save(entity);
		EUser eUser=this.eUserRepository.findById(entity.getId()).get();
		Assert.assertTrue(eUser!=null);
	}

	
}
