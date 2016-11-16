package com.faceye.component.generate.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.faceye.feature.util.pair.Pairs;
import com.google.common.base.CaseFormat;

/**
 * Property ORM 实体<br>
 * 数据库表:generate_property<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
/**
 * Property ORM 实体<br>
 * 数据库表:generate_property<br>
 * 
 * @author @haipenge <br>
 *         haipenge@gmail.com<br>
 *         Create Date:2014年5月21日<br>
 */
@Document(collection = "generate_property")
public class Property implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;

	/**
	 * 输入框类型
	 */
	
	public static Pairs<String, String> inputTypes = new Pairs().add("1", "text").add("2", "textarea").add("3", "radio").add("4", "select").add("5", "checkbox");
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
	 * 
	 * @author haipenge<br>
	 */
	@NotBlank
	@Size(min = 2, max = 20, message = "{property.name.length.limit}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 说明:属性<br>
	 * 属性名: propertyName<br>
	 * 类型: String<br>
	 * 数据库字段:property_name<br>
	 * 
	 * @author haipenge<br>
	 */
	@NotBlank
	@Size(min = 2, max = 20, message = "{property.propertyName.length.limit}")
	@Pattern(regexp = "[a-zA-Z]+[0-9]*")
	private String propertyName;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = StringUtils.uncapitalize(propertyName);
	}

	/**
	 * 所属实体
	 */
	@DBRef
	private Entity entity = null;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	/**
	 * 数据类型
	 */
	@DBRef
	private DataType dataType = null;

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * 说明:校验规则<br>
	 * 属性名: validateIds<br>
	 * 类型: Long []<br>
	 * 数据库字段:validate_ids<br>
	 * 
	 * @author haipenge<br>
	 */
	@DBRef
	private List<Validate> validates = new ArrayList<Validate>();

	public List<Validate> getValidates() {
		return validates;
	}

	public void setValidates(List<Validate> validates) {
		this.validates = validates;
	}

	/**
	 * 说明:列名<br>
	 * 属性名: columnName<br>
	 * 类型: String<br>
	 * 数据库字段:column_name<br>
	 * 
	 * @author haipenge<br>
	 */
	@Pattern(regexp = "[a-zA-Z_]+[0-9]*")
	private String columnName = "";

	public String getColumnName() {
		if (StringUtils.isEmpty(columnName) && StringUtils.isNotBlank(propertyName)) {
			// 大写字母转下划线
			columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.propertyName);
		}
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = StringUtils.lowerCase(columnName);
	}

	/**
	 * 属性引用的对像
	 */
	@DBRef
	private Entity referenceEntity = null;

	public Entity getReferenceEntity() {
		return referenceEntity;
	}

	public void setReferenceEntity(Entity referenceEntity) {
		this.referenceEntity = referenceEntity;
	}

	/**
	 * 说明:是否多选<br>
	 * 属性名: isMulti<br>
	 * 类型: Boolean<br>
	 * 数据库字段:is_multi<br>
	 * 
	 * @author haipenge<br>
	 */

	private Boolean isMulti = Boolean.FALSE;

	public Boolean getIsMulti() {
		return isMulti;
	}

	public void setIsMulti(Boolean isMulti) {
		this.isMulti = isMulti;
	}

	/**
	 * 说明:是否查询字段<br>
	 * 属性名: isQuery<br>
	 * 类型: Boolean<br>
	 * 数据库字段:is_query<br>
	 * 
	 * @author haipenge<br>
	 */

	private Boolean isQuery = Boolean.FALSE;

	public Boolean getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Boolean isQuery) {
		this.isQuery = isQuery;
	}

	/**
	 * 说明:是否索引<br>
	 * 属性名: isIndex<br>
	 * 类型: Boolean<br>
	 * 数据库字段:is_index<br>
	 * 
	 * @author haipenge<br>
	 */

	private Boolean isIndex = Boolean.FALSE;

	public Boolean getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(Boolean isIndex) {
		this.isIndex = isIndex;
	}

	/**
	 * 输入框类型，默认为input.
	 */
	private Integer inputType = 1;

	public Integer getInputType() {
		return inputType;
	}

	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}

	/**
	 * 输入框类型描述
	 */
	@Transient
	private String inputTypeText = "";
	public String getInputTypeText() {
		inputTypeText = inputTypes.getPair("" + inputType).getValue();
		return inputTypeText;
	}

	public void setInputTypeText(String inputTypeText) {
		this.inputTypeText = inputTypeText;
	}

	
	

}/** @generate-entity-source@ **/
