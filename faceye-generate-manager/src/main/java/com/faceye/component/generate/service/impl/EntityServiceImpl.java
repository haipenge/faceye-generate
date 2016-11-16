package com.faceye.component.generate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.repository.mongo.ComponentRepository;
import com.faceye.component.generate.repository.mongo.EntityRepository;
import com.faceye.component.generate.service.EntityService;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;

/**
 * Entity 服务实现类<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月20日<br>
 */
@Service
public class EntityServiceImpl extends BaseMongoServiceImpl<Entity, Long, EntityRepository> implements EntityService {

	@Autowired
	ComponentRepository componentRepository = null;
	@Autowired
	private PropertyService propertyService = null;

	@Autowired
	public EntityServiceImpl(EntityRepository dao) {
		super(dao);
	}

	public void remove(Long id) {
		// 删除Property
		this.beforeRemove(id);
		Entity entity = this.get(id);
		this.remove(entity);
	}

	public void remove(Entity entity) {
		this.beforeRemove(entity.getId());
		super.remove(entity);
	}

	public void removeInBatch(List<Entity> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			for (Entity entity : entities) {
				this.beforeRemove(entity.getId());
			}
		}
		super.removeInBatch(entities);
	}

	private void beforeRemove(Long id) {
		Map params = new HashMap();
		params.put("EQ|entity.$id", id);
		List<Property> properties = this.propertyService.getPage(params, 1, 0).getContent();
		if (CollectionUtils.isNotEmpty(properties)) {
			this.propertyService.removeInBatch(properties);
		}
	}

}/** @generate-service-source@ **/
