package com.faceye.test.component.exam.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.faceye.component.exam.entity.UseCase;
import com.faceye.component.exam.repository.mongo.UseCaseRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * UseCase DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */
public class UseCaseRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private UseCaseRepository useCaseRepository = null;

	@Before
	public void before() throws Exception {
		//this.useCaseRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseRepository.save(entity);
		Iterable<UseCase> entities = this.useCaseRepository.findAll();
		Assert.isTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseRepository.save(entity);
        this.useCaseRepository.delete(entity.getId());
        Iterable<UseCase> entities = this.useCaseRepository.findAll();
		Assert.isTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseRepository.save(entity);
		UseCase useCase=this.useCaseRepository.findOne(entity.getId());
		Assert.isTrue(useCase!=null);
	}

	
}
