package com.spellCheck.Data;

import org.springframework.stereotype.Repository;

@Repository
public class Suggestion {
	String dictionaryWord;
	Double distance;
	
	public String getDictionaryWord() {
		return dictionaryWord;
	}
	
	public void setDictionaryWord(String dictionaryWord) {
		this.dictionaryWord = dictionaryWord;
	}
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	
}
