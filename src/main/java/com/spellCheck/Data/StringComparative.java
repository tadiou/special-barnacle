package com.spellCheck.Data;

/**
 *
 * This is just a wrapper for the incoming JSON value to get mapped to.
 *
 */

public class StringComparative {
    private String phrase;

    private Boolean suggest;
 
    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Boolean getSuggest() {
        return suggest;
    }

    public void setSuggest(Boolean suggest) {
        this.suggest = suggest;
    }
}
