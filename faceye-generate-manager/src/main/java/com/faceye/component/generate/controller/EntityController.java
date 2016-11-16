package com.faceye.component.generate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceye.component.generate.entity.Component;
import com.faceye.component.generate.entity.DataType;
import com.faceye.component.generate.entity.Entity;
import com.faceye.component.generate.entity.Property;
import com.faceye.component.generate.entity.Validate;
import com.faceye.component.generate.service.ComponentService;
import com.faceye.component.generate.service.DataTypeService;
import com.faceye.component.generate.service.EntityService;
import com.faceye.component.generate.service.PropertyService;
import com.faceye.component.generate.service.ValidateService;
import com.faceye.feature.controller.BaseController;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.pair.Pairs;

/**
 * 模块:generate<br>
 * 实体:Entity<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/generate/entity")
public class EntityController extends BaseController<Entity, Long, EntityService> {

	@Autowired
	private ComponentService componentService = null;
	@Autowired
	private PropertyService propertyService = null;
	@Autowired
	private DataTypeService dataTypeService = null;
	@Autowired
	private ValidateService validateService=null;

	@Autowired
	public EntityController(EntityService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<Entity> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);

		String componentId = MapUtils.getString(searchParams, "EQ|component.$id");
		if (StringUtils.isNotEmpty(componentId)) {
			Component component = this.componentService.get(Long.parseLong(componentId));
			model.addAttribute("component", component);
		}
		this.resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "generate.entity.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Entity entity = this.service.get(id);
			model.addAttribute("entity", entity);
		}
		return "generate.entity.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Entity entity, Model model, HttpServletRequest request) {
		beforeInput(model, request, entity);
		return "generate.entity.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Entity entity, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, entity);
			return "generate.entity.update";
		} else {
			this.service.save(entity);
			return "redirect:/generate/entity/home?EQ|component.$id=" + entity.getComponent().getId();
		}
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * 
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Long componentId = null;
		if (id != null) {
			// 删除entity
			Entity entity = this.service.get(id);
			componentId = entity.getComponent().getId();
			this.service.remove(entity);
		}
		return "redirect:/generate/entity/home?EQ|component.$id=" + componentId;
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * @haipenge@gmail.com<br>
	 * @CreateDate:2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Entity entity = this.service.get(id);
			model.addAttribute("entity", entity);
		}
		return "generate.entity.detail";
	}

	/**
	 * 表单构造器
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年6月30日 上午10:16:04
	 */
	@RequestMapping("/formBuilder/{id}")
	public String formBuilder(@PathVariable Long id, Model model) {
		Entity entity = this.service.get(id);
		Map searchParams = new HashMap();
		searchParams.put("EQ|entity.$id", id);
		Page<Property> properties = this.propertyService.getPage(searchParams, 1, 0);
		model.addAttribute("properties", properties);
		List<DataType> dataTypes = this.dataTypeService.getAll();
		model.addAttribute("dataTypes", dataTypes);
		// 引用实体
		searchParams = new HashMap();
		searchParams.put("EQ|project.$id", entity.getComponent().getProject().getId());
		List<Component> components = this.componentService.getPage(searchParams, 1, 0).getContent();
		List<Long> componentIds = new ArrayList<Long>(0);
		if (CollectionUtils.isNotEmpty(components)) {
			for (Component component : components) {
				componentIds.add(component.getId());
			}
		}
		searchParams = new HashMap();
		searchParams.put("IN|component.$id", componentIds.toArray(new Long[componentIds.size()]));
		List<Entity> entities = this.service.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("entities", entities);
		
		List<Validate> validates = this.validateService.getAll();
		model.addAttribute("validates", validates);
		Pairs<String,String> pairs=Property.inputTypes;
		model.addAttribute("inputTypes", pairs.getPairs());
		return "generate.entity.formBuilder";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * 
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request, Entity entity) {

		String componentId = request.getParameter("componentId");
		Component component = null;
		if (StringUtils.isNotEmpty(componentId)) {
			component = this.componentService.get(Long.parseLong(componentId));
			model.addAttribute("component", component);
			entity.setComponent(component);
		} else {
			List<Component> components = this.componentService.getAll();
			model.addAttribute("components", components);
		}

	}

}
