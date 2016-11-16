package com.faceye.component.generate.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.RandUtil;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.pair.Pairs;
import com.google.common.base.CaseFormat;

/**
 * 模块:generate<br>
 * 实体:Property<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/generate/property")
public class PropertyController extends BaseController<Property, Long, PropertyService> {
	@Autowired
	private EntityService entityService = null;
	@Autowired
	private DataTypeService dataTypeService = null;
	@Autowired
	private ValidateService validateService = null;
	@Autowired
	private ComponentService componentService = null;

	@Autowired
	public PropertyController(PropertyService service) {
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
		model.addAttribute("searchParams", searchParams);
		Long entityId = MapUtils.getLong(searchParams, "EQ|entity.$id");
		if (entityId != null) {
			Entity entity = this.entityService.get(entityId);
			model.addAttribute("entity", entity);
		}
		Page<Property> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		return "generate.property.manager";
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
			Property entity = this.service.get(id);
			beforeInput(model, request, entity);
			model.addAttribute("property", entity);
		}
		return "generate.property.update";
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
	public String input(Property property, Model model, HttpServletRequest request) {
		beforeInput(model, request, property);
		return "generate.property.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping("/save")
	public String save(@Valid Property property, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			beforeInput(model, request, property);
			return "generate.property.update";
		} else {
			this.beforeSave(property, request);
			this.service.save(property);
			return "redirect:/generate/property/home?EQ|entity.$id=" + property.getEntity().getId();
		}
	}

	/**
	 * 点击，自动增加Property
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月19日 下午2:13:42
	 */
	@RequestMapping("/saveDefaultProperty")
	@ResponseBody
	public Property saveDefaultProperty(@RequestParam(required = true) Long entityId,@RequestParam String inputTypeText) {
		Entity entity = this.entityService.get(entityId);
		DataType defaultDataType = this.dataTypeService.getDataTypeByShortTypeClass("String");
		Property property = null;
		if (entity != null) {
			// text
			property = new Property();
			Integer inputType=1;
			if(StringUtils.isNotEmpty(inputTypeText)){
				inputType=Integer.parseInt(Property.inputTypes.getPairByValue(inputTypeText).getKey());
			}
			property.setInputType(inputType);
			property.setDataType(defaultDataType);
			property.setEntity(entity);
			property.setName("Field");
			property.setPropertyName("autoGenerateField" + RandUtil.getRandInt(100, 999));
			this.service.save(property);
		}
		return property;
	}

	/**
	 * 更新property 中某一属性的定义
	 * 
	 * @param id
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月19日 上午10:03:21
	 */
	@RequestMapping("/updateProperty")
	@ResponseBody
	public String updateProperty(@RequestParam(required = true) Long id, @RequestParam(required = true) String propertyName, @RequestParam(required = true) String propertyValue) {
		Property property = this.service.get(id);
		if (property != null) {
			if (StringUtils.isNotEmpty(propertyName)) {
				if (StringUtils.contains(propertyName, ".")) {
					if (StringUtils.equals(propertyName, "dataType.id")) {
						DataType dataType = this.dataTypeService.get(Long.parseLong(propertyValue));
						if (dataType != null) {
							property.setDataType(dataType);
						}
					} else if (StringUtils.equals(propertyName, "referenceEntity.id")) {
						Entity entity = this.entityService.get(Long.parseLong(propertyValue));
						if (entity != null) {
							property.setReferenceEntity(entity);
						}
					}
				} else {
					try {
						Object existPropertyValue = BeanUtils.getProperty(property, propertyName);
						if (existPropertyValue != null) {
							BeanUtils.setProperty(property, propertyName, propertyValue);
						}
					} catch (IllegalAccessException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					} catch (InvocationTargetException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					} catch (NoSuchMethodException e) {
						logger.error(">>FaceYe Throws Exception:", e);
					}
				}
			}
		}
		this.service.save(property);
		return AjaxResult.getInstance().buildDefaultResult(true);
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
		Long entityId = null;
		if (id != null) {
			Property property = this.service.get(id);
			entityId = property.getEntity().getId();
			this.service.remove(property);
		}
		return "redirect:/generate/property/home?EQ|entity.$id=" + entityId;
	}

	/**
	 * 以Ajax方式删除
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月19日 下午2:06:30
	 */
	@RequestMapping("/removeProperty")
	@ResponseBody
	public String removeProperty(@RequestParam(required = true) Long id, HttpServletRequest request) {
		this.service.remove(id);
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * 
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * 						haipenge@gmail.com<br>
	 *                       2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Property entity = this.service.get(id);
			model.addAttribute("property", entity);
		}
		return "generate.property.detail";
	}

	/**
	 * Ajax方式的数据详情
	 * 
	 * @param id
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年9月17日 下午4:51:49
	 */
	@RequestMapping("/ajaxDetail/{id}")
	@ResponseBody
	public Property ajaxDetail(@PathVariable Long id) {
		Property property = null;
		property = this.service.get(id);
		return property;
	}

	/**
	 * 自动设置列名(字母小写+下划线)
	 * 
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2016年7月11日 下午3:44:29
	 */
	@RequestMapping("/autoSetPropertyColumnValueByPropertyName")
	@ResponseBody
	public String autoSetPropertyColumnValueByPropertyName(@RequestParam(required = true) String propertyName) {
		String res = "";
		String columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, propertyName);
		res = "{\"columnName\":\"" + columnName + "\"}";
		return res;
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
	protected void beforeInput(Model model, HttpServletRequest request, Property property) {
		String entityId = request.getParameter("EQ|entity.$id");
		if (StringUtils.isNotEmpty(entityId)) {
			Entity entity = this.entityService.get(Long.parseLong(entityId));
			// model.addAttribute("entity", entity);
			property.setEntity(entity);
		}
		Map searchParams = new HashMap();
		searchParams.put("EQ|project.$id", property.getEntity().getComponent().getProject().getId());
		List<Component> components = this.componentService.getPage(searchParams, 1, 0).getContent();
		List<Long> componentIds = new ArrayList<Long>(0);
		if (CollectionUtils.isNotEmpty(components)) {
			for (Component component : components) {
				componentIds.add(component.getId());
			}
		}
		searchParams = new HashMap();
		searchParams.put("IN|component.$id", componentIds.toArray(new Long[componentIds.size()]));
		List<Entity> entities = this.entityService.getPage(searchParams, 1, 0).getContent();
		model.addAttribute("entities", entities);
		List<DataType> dataTypes = this.dataTypeService.getAll();
		model.addAttribute("dataTypes", dataTypes);
		List<Validate> validates = this.validateService.getAll();
		model.addAttribute("validates", validates);
		Pairs<String,String> pairs=Property.inputTypes;
		model.addAttribute("inputTypes", pairs.getPairs());

	}

	/**
	 * 保存前的数据回调
	 * 
	 * @todo
	 * @param property
	 * @param request
	 * @author:@haipenge haipenge@gmail.com 2015年4月11日
	 */
	protected void beforeSave(Property property, HttpServletRequest request) {
		String[] validateIds = request.getParameterValues("validate.id");
		List<Validate> validates = new ArrayList<Validate>();
		if (validateIds != null) {
			for (String validateId : validateIds) {
				Validate validate = this.validateService.get(Long.parseLong(validateId));
				if (null != validate) {
					validates.add(validate);
				}
			}
		}
		property.setValidates(validates);
	}

}
