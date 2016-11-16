package com.faceye.component.generate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.repository.mongo.ComponentRepository;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.EntityService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Component 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class ComponentServiceImpl extends BaseMongoServiceImpl<Component, Long, ComponentRepository> implements ComponentService {
	@Autowired
	private EntityService entityService=null;
	@Autowired
	public ComponentServiceImpl(ComponentRepository dao) {
		super(dao);
	}
	public void remove(Long id){
		this.beforeRemove(id);
		super.remove(id);
	}
	
	public void removeInBatch(List<Component> components){
		if(CollectionUtils.isNotEmpty(components)){
			for(Component component:components){
				this.beforeRemove(component.getId());
			}
		}
		super.removeInBatch(components);
	}
	
	private void beforeRemove(Long id){
		Map params=new HashMap();
		params.put("EQ|component.$id", id);
		List<Entity> entities=this.entityService.getPage(params, 1, 0).getContent();
		if(CollectionUtils.isNotEmpty(entities)){
			this.entityService.removeInBatch(entities);
		}
	}
	
	
	
}/**@generate-service-source@**/
