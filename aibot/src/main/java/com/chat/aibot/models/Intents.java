package com.chat.aibot.models;

public class Intents implements Comparable<Intents>{
	
	private double confidence;
	private String name;

	public double getConfidence() {
		return confidence;
	}
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Intents [confidence=" + confidence + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Intents i) {
		if (i.getConfidence()<this.confidence) {
			return 1;
		}else if (i.getConfidence()>this.confidence) {
			return -1;
		}
		return 0;
	}
}
