package com.spellCheck.Data;

import java.util.HashMap;

/**
 *
 * For each word that's passed in through {stringComparative}, we create a new
 * ComparedWord object that allows us to build out an array to send back via
 * JSON with all of the responses necessary.
 *
 */


public class ComparedWord {
  private String word;

  private Boolean correct;

  private HashMap<String, Double> recommended;

  public String getWord() {
      return word;
  }

  public void setWord(String word) {
      this.word = word;
  }

  public Boolean getCorrect() {
      return correct;
  }

  public void setCorrect(Boolean correct) {
      this.correct = correct;
  }

  public HashMap<String, Double> getRecommended() {
	  return recommended;
  }

  public void setRecommended(HashMap<String, Double> recommended) {
	  this.recommended = recommended;
  } 
}
