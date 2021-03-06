package com.faceye.test.component.generate.repository;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.PropertyRepository;
import com.faceye.test.feature.repository.BaseRepositoryTestCase;
/**
 * Property DAO 测试
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年5月26日
 */

public class PropertyRepositoryTestCase extends BaseRepositoryTestCase {
	@Autowired
	private PropertyRepository propertyRepository = null;

	@Before
	public void before() throws Exception {
		//this.propertyRepository.deleteAll();
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		Property entity = new Property();
		this.propertyRepository.save(entity);
		Iterable<Property> entities = this.propertyRepository.findAll();
		Assert.assertTrue(entities.iterator().hasNext());
	}

	@Test
	public void testDelete() throws Exception {
		Property entity = new Property();
		this.propertyRepository.save(entity);
        this.propertyRepository.deleteById(entity.getId());
        Iterable<Property> entities = this.propertyRepository.findAll();
		Assert.assertTrue(!entities.iterator().hasNext());
	}

	@Test
	public void testFindOne() throws Exception {
		Property entity = new Property();
		entity.setId(100001L);
		entity.setName("test");
		this.propertyRepository.save(entity);
		Property property=this.propertyRepository.findById(entity.getId()).get();
		Assert.assertTrue(property!=null);
	}
	
	@Test
	public void testGetPropertyByName() throws Exception{
		String name="test";
		Property property=this.propertyRepository.getPropertyByName(name);
		Assert.assertTrue(property!=null&&StringUtils.equals("test", property.getName()));
	}

	@Test
	public void testGetPropertyByPropertyName() throws Exception{
		Property entity = new Property();
		entity.setId(100002L);
		entity.setName("test-property");
		entity.setPropertyName("property-name");
		this.propertyRepository.save(entity);
		Property property=this.propertyRepository.getPropertyByPropertyName("property-name");
		Assert.assertTrue(property!=null&&StringUtils.equals("property-name", property.getPropertyName()));
		this.propertyRepository.deleteById(property);
		Property p=this.propertyRepository.findById(100002L);
		Assert.assertTrue(p==null);
	}
	
}
