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
import org.junit.Assert;

import com.faceye.component.exam.entity.EUser;
import com.faceye.component.exam.service.EUserService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * EUser  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class EUserServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private EUserService eUserService = null;
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
		Assert.assertTrue(eUserService != null);
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
		//this.eUserService.removeAllInBatch();
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
		EUser entity = new EUser();
		this.eUserService.save(entity);
		List<EUser> entites = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		EUser entity = new EUser();
		this.eUserService.save(entity);
		List<EUser> entites = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
		}
		List<EUser> entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		EUser entity = new EUser();
		this.eUserService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		EUser e = this.eUserService.get(entity.getId());
		Assert.assertTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		EUser entity = new EUser();
		this.eUserService.save(entity);
		this.eUserService.remove(entity);
		List<EUser> entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
		}
		List<EUser> entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.eUserService.removeAllInBatch();
		entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
		}
		this.eUserService.removeAll();
		List<EUser> entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<EUser> entities = new ArrayList<EUser>();
		for (int i = 0; i < 5; i++) {
			EUser entity = new EUser();
			
			this.eUserService.save(entity);
			entities.add(entity);
		}
		this.eUserService.removeInBatch(entities);
		entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
		}
		List<EUser> entities = this.eUserService.getAll();
		Assert.assertTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<EUser> page = this.eUserService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.eUserService.getPage(searchParams, 1, 5);
		Assert.assertTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.eUserService.getPage(searchParams, 1, 5);

		Assert.assertTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
			id = entity.getId();
		}
		EUser e = this.eUserService.get(id);
		Assert.assertTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			EUser entity = new EUser();
			this.eUserService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<EUser> entities = this.eUserService.getAll(ids);
		Assert.assertTrue(entities != null && entities.size() == 5);
	}
}
