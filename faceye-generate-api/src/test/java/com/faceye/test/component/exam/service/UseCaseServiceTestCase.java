package com.faceye.test.component.exam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.exam.entity.UseCase;
import com.faceye.component.exam.service.UseCaseService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * UseCase  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class UseCaseServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private UseCaseService useCaseService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(useCaseService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.useCaseService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseService.save(entity);
		List<UseCase> entites = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseService.save(entity);
		List<UseCase> entites = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
		}
		List<UseCase> entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		UseCase e = this.useCaseService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		UseCase entity = new UseCase();
		this.useCaseService.save(entity);
		this.useCaseService.remove(entity);
		List<UseCase> entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
		}
		List<UseCase> entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.useCaseService.removeAllInBatch();
		entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
		}
		this.useCaseService.removeAll();
		List<UseCase> entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<UseCase> entities = new ArrayList<UseCase>();
		for (int i = 0; i < 5; i++) {
			UseCase entity = new UseCase();
			
			this.useCaseService.save(entity);
			entities.add(entity);
		}
		this.useCaseService.removeInBatch(entities);
		entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
		}
		List<UseCase> entities = this.useCaseService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<UseCase> page = this.useCaseService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.useCaseService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.useCaseService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
			id = entity.getId();
		}
		UseCase e = this.useCaseService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			UseCase entity = new UseCase();
			this.useCaseService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<UseCase> entities = this.useCaseService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
