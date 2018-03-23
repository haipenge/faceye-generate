package com.faceye.component.generate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity ORM 实体<br>
 * 数据库表:generate_entity<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection = "generate_entity")
public class Entity implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	private Long id = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 说明:名称<br>
	 * 属性名: name<br>
	 * 类型: String<br>
	 * 数据库字段:name<br>
	 * @author haipenge<br>
	 */
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:类名,如Room,不包含包名<br>
	 * 属性名: className<br>
	 * 类型: String<br>
	 * 数据库字段:class_name<br>
	 * @author haipenge<br>
	 */
	@NotBlank
	@Size(min = 2, max = 50, message = "{entity.className.length.limit}")
	@Pattern(regexp="[a-zA-Z]+[0-9]*")
	private String className;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		if (StringUtils.isNotEmpty(className)) {
			this.className = StringUtils.capitalize(className);
		}
	}

	/**
	 * 说明:备注<br>
	 * 属性名: remark<br>
	 * 类型: String<br>
	 * 数据库字段:re_mark<br>
	 * @author haipenge<br>
	 */

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 说明:创建时间<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:create_date<br>
	 * @author haipenge<br>
	 */

	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 设置所属模块
	 */
	@DBRef
	private Component component = null;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	/**
	 * 取得当前实体的包名
	 */
	@Transient
	private String packageName = "";

	public String getPackageName() {
		// 模块包名,如:generate,不包含前部
		String componentPackageName = this.getComponent().getPackageName();
		Project project = this.getComponent().getProject();
		String projectPackageName = project.getPackageName();
		packageName = projectPackageName + ".component." + componentPackageName + ".entity";
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
/**@generate-entity-source@**/

