package com.springboot.mongo.dao;

import com.springboot.mongo.model.AliYun;
import org.springframework.stereotype.Repository;
import java.util.List;



/**
 * 阿里云测试
 * @author admin
 *
 */
@Repository
public class AliYunDao extends MongoBaseDAO<AliYun> {

	/**
	 * 集合名称
	 */
	private final static String COLLECTION_NAME = "AliYunTest";
	
	/**
	 * 插入多个
	 */
	public void insertMulti(List<AliYun> batchList){
        insertMulti(batchList,COLLECTION_NAME);  
	}	
	
	public void insert(AliYun entity){
		insert(entity, COLLECTION_NAME);
	}
}
