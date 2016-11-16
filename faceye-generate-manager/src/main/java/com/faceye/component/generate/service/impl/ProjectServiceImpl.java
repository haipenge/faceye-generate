package com.faceye.component.generate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.Project;
import com.faceye.component.generate.repository.mongo.ProjectRepository;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.ProjectService;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
/**
 * Project 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
@Service
public class ProjectServiceImpl extends BaseMongoServiceImpl<Project, Long, ProjectRepository> implements ProjectService {
	@Autowired
	private ComponentService componentService=null;
	@Autowired
	public ProjectServiceImpl(ProjectRepository dao) {
		super(dao);
	}
	
	public void remove(Long id){
		Map params=new HashMap();
		params.put("EQ|project.$id", id);
		List<Component> components=this.componentService.getPage(params, 1, 0).getContent();
		this.componentService.removeInBatch(components);
		super.remove(id);
		
	}
	
	
}/**@generate-service-source@**/
