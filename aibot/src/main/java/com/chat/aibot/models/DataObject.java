package com.chat.aibot.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class DataObject {
	
	@Id
	private String id;
	private String name;
	private String description;
	@OneToOne(cascade = {CascadeType.ALL})
	private TrainingData trainingData;
	@OneToOne(cascade = {CascadeType.ALL})
	private Reply reply;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TrainingData getTrainingData() {
		return trainingData;
	}
	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}
	public Reply getReply() {
		return reply;
	}
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	public DataObject(String id, String name, String description, TrainingData trainingData, Reply reply) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.trainingData = trainingData;
		this.reply = reply;
	}
	
	public DataObject() {}
	@Override
	public String toString() {
		return "DataObject [id=" + id + ", name=" + name + ", description=" + description + ", trainingData="
				+ trainingData + ", reply=" + reply + "]";
	}

}
