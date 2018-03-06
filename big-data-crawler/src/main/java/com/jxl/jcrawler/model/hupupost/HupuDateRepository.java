package com.jxl.jcrawler.model.hupupost;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface HupuDateRepository extends MongoRepository<HupuPost, String>{
	List<HupuPost> findByPostTitle(String postTitle);
}
