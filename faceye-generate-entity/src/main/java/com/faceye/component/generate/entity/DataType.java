package com.faceye.component.generate.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * DataType ORM 实体<br>
 * 数据库表:generate_dataType<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月21日<br>
 */
@Document(collection="generate_dataType")
public class DataType implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	@Id
	@Indexed(direction=IndexDirection.DESCENDING)
	private  Long id=null;
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
    
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
   /**
    * 说明:类型<br>
    * 属性名: typeClass<br>
    * 类型: String<br>
    * 数据库字段:type_class<br>
    * @author haipenge<br>
    */
    
	private  String typeClass;
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		if(StringUtils.isNotEmpty(typeClass)){
			String [] splits=typeClass.split("\\.");
			this.setShortTypeClass(splits[splits.length-1]);
		}
		this.typeClass = typeClass;
	}
	

	
   /**
    * 说明:缩略类型<br>
    * 属性名: shortTypeClass<br>
    * 类型: String<br>
    * 数据库字段:short_type_class<br>
    * @author haipenge<br>
    */
    
	private  String shortTypeClass;
	public String getShortTypeClass() {
		return shortTypeClass;
	}
	public void setShortTypeClass(String shortTypeClass) {
		this.shortTypeClass = shortTypeClass;
	}
	

	
  
	
}/**@generate-entity-source@**/
	
