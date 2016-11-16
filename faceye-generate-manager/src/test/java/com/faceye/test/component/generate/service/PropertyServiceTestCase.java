package com.faceye.test.component.generate.service;

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

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Property  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class PropertyServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private PropertyService propertyService = null;
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
		Assert.isTrue(propertyService != null);
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
		//this.propertyService.removeAllInBatch();
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
		Property entity = new Property();
		this.propertyService.save(entity);
		List<Property> entites = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Property entity = new Property();
		this.propertyService.save(entity);
		List<Property> entites = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
		}
		List<Property> entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Property entity = new Property();
		this.propertyService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Property e = this.propertyService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Property entity = new Property();
		this.propertyService.save(entity);
		this.propertyService.remove(entity);
		List<Property> entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
		}
		List<Property> entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.propertyService.removeAllInBatch();
		entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
		}
		this.propertyService.removeAll();
		List<Property> entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Property> entities = new ArrayList<Property>();
		for (int i = 0; i < 5; i++) {
			Property entity = new Property();
			
			this.propertyService.save(entity);
			entities.add(entity);
		}
		this.propertyService.removeInBatch(entities);
		entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
		}
		List<Property> entities = this.propertyService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Property> page = this.propertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.propertyService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.propertyService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
			id = entity.getId();
		}
		Property e = this.propertyService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Property entity = new Property();
			this.propertyService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Property> entities = this.propertyService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
	@Test
	public void testGetPropertyByName() throws Exception{
		Property property=this.propertyService.getPropertyByName("name");
		Assert.isTrue(property!=null);
	}
}