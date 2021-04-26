package com.chat.aibot.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TrainingData {
	
	@Id
	@GeneratedValue	 
	private long id;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Messages> messages;
	private int expressionCount;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Set<Expressions> expressions;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Messages> getMessages() {
		return messages;
	}
	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}
	public int getExpressionCount() {
		return expressionCount;
	}
	public void setExpressionCount(int expressionCount) {
		this.expressionCount = expressionCount;
	}
	public Set<Expressions> getExpressions() {
		return expressions;
	}
	public void setExpressions(Set<Expressions> expressions) {
		this.expressions = expressions;
	}

	@Override
	public String toString() {
		return "TrainingData [id=" + id + ", messages=" + messages + ", expressionCount=" + expressionCount
				+ ", expressions=" + expressions + "]";
	}
}