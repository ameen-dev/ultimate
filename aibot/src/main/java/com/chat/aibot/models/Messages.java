package com.chat.aibot.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Messages {
	
	@Id
	private String id;
	private String text;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "Messages [id=" + id + ", text=" + text + "]";
	}
}
