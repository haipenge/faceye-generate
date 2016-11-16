package com.faceye.component.generate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Component ORM 实体<br>
 * 数据库表:generate_component<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection = "generate_component")
public class Component implements Serializable {
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
	 * 说明:创建日期<br>
	 * 属性名: createDate<br>
	 * 类型: Date<br>
	 * 数据库字段:create_date<br>
	 * @author haipenge<br>
	 */

	private Date createDate=new Date();

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 所属项目
	 */
	@DBRef
	private Project project = null;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	    * 说明:包名<br>
	    * 属性名: packageName<br>
	    * 类型: String<br>
	    * 数据库字段:package_name<br>
	    * @author haipenge<br>
	    */
    @NotBlank
    @Pattern(regexp="[a-z]+")
    @Size(min=2,max=20,message="{component.packageName.length.limit}")
	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = StringUtils.lowerCase(packageName);
	}

}
/**@generate-entity-source@**/

