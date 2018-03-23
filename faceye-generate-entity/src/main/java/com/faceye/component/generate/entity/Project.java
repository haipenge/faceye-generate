package com.faceye.component.generate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Project ORM 实体<br>
 * 数据库表:generate_project<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
 * Create Date:2014年5月21日<br>
 */
/**
*spring-data-mongo支持的注释类型<br>
*@Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。<br>
*@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。<br>
*@DBRef - 声明类似于关系数据库的关联关系。ps：暂不支持级联的保存功能，当你在本实例中修改了DERef对象里面的值时，单独保存本实例并不能保存DERef引用的对象，它要另外保存<br>
*@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。<br>
*@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。<br>
*@GeoSpatialIndexed - 声明该字段为地理信息的索引。<br>
*@Transient - 映射忽略的字段，该字段不会保存到<br>
*@PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。<br>
 */
@Document(collection = "generate_project")
//@CompoundIndexes({
//    @CompoundIndex(name = "age_idx", def = "{'lastName': 1, 'age': -1}")
//})
//@Indexed(unique = true)
public class Project implements Serializable {
	private static final long serialVersionUID = 8926119711730830203L;
	
	@Id
	@Indexed(direction=IndexDirection.DESCENDING)
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
	//@NotNull(message="{generate.project.name.not.blank}")
//	@NotBlank(message="{generate.project}")
	@NotBlank()
	//@Length(min=3,max=20,message="{project.name.length.limit}")
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
	@DateTimeFormat(pattern="yyyy-MM-dd hh24:mi:ss")
	private Date createDate;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 说明:包名<br>
	 * 属性名: packageName<br>
	 * 类型: String<br>
	 * 数据库字段:package_name<br>
	 * @author haipenge<br>
	 */
    @Pattern(regexp="[org|com|net|cn|me]+\\.[a-z0-9]+")
    @NotBlank()
	private String packageName=null;

	public String getPackageName() {
		
		return packageName;
	}

	public void setPackageName(String packageName) {
		if (StringUtils.isNotEmpty(packageName)) {
			String[] packages = packageName.split("\\.");
			if (packages.length >= 2) {
				this.setRoot(packages[1]);
			}
		}
		this.packageName = packageName;
	}

	/**
	 * 说明:存储路径<br>
	 * 属性名: path<br>
	 * 类型: String<br>
	 * 数据库字段:path<br>
	 * @author haipenge<br>
	 */
//	private String path;
//
//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}

	/**
	 * 说明:儿者<br>
	 * 属性名: author<br>
	 * 类型: String<br>
	 * 数据库字段:author<br>
	 * @author haipenge<br>
	 */

	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 项目根目录
	 */
	private String root = "";

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}



   /**
    * 说明:是否已生成<br>
    * 属性名: isGenerate<br>
    * 类型: Boolean<br>
    * 数据库字段:is_generate<br>
    * @author haipenge<br>
    */
    
	private  Boolean isGenerate=false;
	public Boolean getIsGenerate() {
		return isGenerate;
	}
	public void setIsGenerate(Boolean isGenerate) {
		this.isGenerate = isGenerate;
	}
	

	
   /**
    * 说明:是否运行中<br>
    * 属性名: isRun<br>
    * 类型: Boolean<br>
    * 数据库字段:is_run<br>
    * @author haipenge<br>
    */
    
	private  Boolean isRun=false;
	public Boolean getIsRun() {
		return isRun;
	}
	public void setIsRun(Boolean isRun) {
		this.isRun = isRun;
	}
	

	
   /**
    * 说明:生成时间<br>
    * 属性名: generateDate<br>
    * 类型: Date<br>
    * 数据库字段:generate_date<br>
    * @author haipenge<br>
    */
    
	private  Date generateDate=new Date();
	public Date getGenerateDate() {
		return generateDate;
	}
	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}
	

	
   /**
    * 说明:最后运行时间<br>
    * 属性名: runDate<br>
    * 类型: Date<br>
    * 数据库字段:run_date<br>
    * @author haipenge<br>
    */
    
	private  Date runDate=new Date();
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	

	
   /**
    * 说明:服务端口<br>
    * 属性名: port<br>
    * 类型: String<br>
    * 数据库字段:port<br>
    * @author haipenge<br>
    */
    
	private  String port;
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	

	
   /**
    * 说明:是已打包<br>
    * 属性名: isPackage<br>
    * 类型: Boolean<br>
    * 数据库字段:is_package<br>
    * @author haipenge<br>
    */
    
	private  Boolean isPackage;
	public Boolean getIsPackage() {
		return isPackage;
	}
	public void setIsPackage(Boolean isPackage) {
		this.isPackage = isPackage;
	}
	

	
   /**
    * 说明:打包时间<br>
    * 属性名: packageDate<br>
    * 类型: Date<br>
    * 数据库字段:package_date<br>
    * @author haipenge<br>
    */
    
	private  Date packageDate;
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	

	
   /**
    * 说明:持久久类型<br>
    * 属性名: repositoryType<br>
    * 类型: String<br>
    * 数据库字段:repository_type<br>
    * @author haipenge<br>
    */
    
	private  String repositoryType;
	public String getRepositoryType() {
		return repositoryType;
	}
	public void setRepositoryType(String repositoryType) {
		this.repositoryType = repositoryType;
	}
	
}/**@generate-entity-source@**/
	
