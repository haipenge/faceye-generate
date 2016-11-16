package ${project.packageName}.component.${component.packageName}.repository.mongo.gen;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import ${project.packageName}.component.${component.packageName}.entity.${entity.className};
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * 模块:${component.name}->${project.packageName}.compoent.${component.packageName}.repository.mongo<br>
 * 说明:${entity.description!""}<br>
 * 实体:${entity.name}->${entity.packageName}.entity.${entity.className} 实体DAO<br>
 * @author ${project.author} <br>
 * 联系:haipenge@gmail.com<br>
*  创建日期:${.now}<br>
 */
public interface ${entity.className}GenRepository extends BaseMongoRepository<${entity.className},Long> {
	
	
}/**@generate-repository-source@**/
