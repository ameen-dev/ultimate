package com.chat.aibot.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.aibot.models.DataObject;

public interface DataObjectRepository extends MongoRepository<DataObject, String> {

	public DataObject findByName(String name);
	
}
