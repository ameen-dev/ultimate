package com.chat.aibot.models;

import java.util.List;

public class Response {

	 private List <Entities> entities = null;
	 private List <Intents> intents = null;

	public List<Entities> getEntities() {
		return entities;
	}
	public void setEntities(List<Entities> entities) {
		this.entities = entities;
	}
	public List<Intents> getIntents() {
		return intents;
	}
	public void setIntents(List<Intents> intents) {
		this.intents = intents;
	}

	@Override
	public String toString() {
		return "Response [entities=" + entities + ", intents=" + intents + "]";
	}
}
