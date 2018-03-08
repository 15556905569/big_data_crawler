package com.jxl.jcrawler.model.hupupostnew;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface HupuNewDateRepository extends MongoRepository<HupuNewData, String>{
	List<HupuNewData> findByPostTitle(String postTitle);
}
