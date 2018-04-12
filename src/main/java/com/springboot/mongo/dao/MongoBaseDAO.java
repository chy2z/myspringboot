package com.springboot.mongo.dao;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * 方式2
 * @author admin
 *
 * @param <T>
 */
public class MongoBaseDAO<T> {
	
	@Autowired
    MongoTemplate mongoTemplate;	
	
	/**
	 * 保存实体对象
	 * 如果有一个相同_ID的文档时，会覆盖原来的
	 * @param entity
	 * @return
	 */
	public void save(T entity,String collectionName){
		mongoTemplate.save(entity, collectionName);
	}
	
	/**
	 * 插入实体对象
	 * 如果有一个相同的_ID时，就会新增失败
	 * @param entity
	 * @param collectionName
	 */
	void insert(T entity,String collectionName){
		mongoTemplate.insert(entity, collectionName);
	}
	
	/**
	 * 批量插入实体对象
	 * @param batchToSave
	 * @param collectionName
	 */
	void insertMulti(List<T> batchToSave,String collectionName){
		mongoTemplate.insert(batchToSave, collectionName);
	}

	/**
	 * 修改实体对象
	 * 如果query找到了符合条件的行，则修改这些行，如果没有找到，则追加一行
	 * @param query
	 * @param update
	 * @param collectionName
	 */
	int upsert(Query query, Update update,String collectionName){
		 int result = 0;
		 WriteResult writeResult=mongoTemplate.upsert(query, update, collectionName);
		 result= writeResult.getN();  
		 return result;
	}  	
	
	/**
	 * 修改实体对象
	 * 如果query找到了符合条件的1行，则修改1行，如果没有找到，则不追加一行
	 * @param query
	 * @param update
	 * @param collectionName
	 */
	int updateFirst(Query query, Update update,String collectionName){
		 int result = 0;
		 WriteResult writeResult=mongoTemplate.updateFirst(query, update, collectionName);
		 result= writeResult.getN();  
		 return result;
	}  
	
	/**
	 * 修改实体对象
	 * 如果query找到了符合条件的多行，则修改多行，如果没有找到，则不追加一行
	 * @param query
	 * @param update
	 * @param collectionName
	 */
	int updateMulti(Query query, Update update,String collectionName){
		 int result = 0;
		 WriteResult writeResult=mongoTemplate.updateMulti(query, update, collectionName);
		 result= writeResult.getN();  
		 return result;
	}	
	
	/**
	 * 删除根据条件
	 * @param query
	 * @param valueType
	 * @param collectionName
	 * @return
	 */
	int remove(Query query,Class<T> valueType,String collectionName){
		 int result = 0;
		 WriteResult writeResult=mongoTemplate.remove(query,valueType, collectionName);
		 result= writeResult.getN();
		 return result;
	}
	
	
	
	/**
	 * 判断记录是否存在
	 * @param query
	 * @param entityClass
	 * @param collectionName
	 * @return
	 */
	boolean exists(Query query, Class<?> entityClass, String collectionName) {
		 return mongoTemplate.exists(query, entityClass, collectionName);
	}

	/**
	 * 根据条件查询指定实体
	 * 
	 * @param query
	 * @return
	 */
	T findOne(Query query,Class<T> valueType,String collectionName){
		return mongoTemplate.findOne(query, valueType, collectionName);
	}

	/**
	 * 查询实体列表
	 * @param valueType
	 * @param collectionName
	 * @return
	 */
	List<T> findAll(Class<T> valueType,String collectionName){
		return mongoTemplate.findAll(valueType, collectionName);
	}

	/**
	 * 查询实体列表
	 * 
	 * @param query
	 * @return
	 */
	List<T> find(Query query,Class<T> valueType,String collectionName){
		return mongoTemplate.find(query,valueType, collectionName);
	}	

	/**
	 * 统计列表总记录数
	 * 
	 * @param query
	 * @return
	 */
	long count(Query query,Class<T> valueType,String collectionName){
		return mongoTemplate.count(query, valueType,collectionName);
	}

	/**
	 * 分页查询实体列表
	 * 
	 * @param page
	 * @param query
	 */
	/*
	List<T> selectByPage(PageVo<T> page, Query query,Class<T> valueType,String collectionName){
		query.skip((page.getPageNo()-1)*page.getPageSize()); //过滤掉多少数据
        query.limit(page.getPageSize());  //取多少数量   
        return  this.mongoTemplate.find(query, valueType,collectionName);		
	}
	*/
}
