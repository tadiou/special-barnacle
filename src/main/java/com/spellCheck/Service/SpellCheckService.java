package com.spellCheck.Service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spellCheck.Data.ComparedWord;
import com.spellCheck.Data.LocalDictionary;
import com.spellCheck.Data.StringComparative;
import com.spellCheck.Data.Suggestion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by dan on 9/23/16.
 */
@Service
public class SpellCheckService {

	private static final double distanceThreshold = 0.95;

    /**
     * So, given our stringComparative object, which we procure from */
    public List<ComparedWord> checkString(StringComparative stringComparative) {
		String cleanPhrase = cleanString(stringComparative.getPhrase());
        List<String> words = Arrays.asList(cleanPhrase.split(" "));

        return words.stream().map(word -> compare(word, stringComparative.getSuggest()))
                    .collect(Collectors.toList());
    }

    public ComparedWord compare(String word, Boolean suggest) {
    	ComparedWord comparison = new ComparedWord();

    	comparison.setRecommended(new HashMap<String, Double>());
    	comparison.setWord(word);

    	comparison.setCorrect(false);

        for(String dictionaryWord: LocalDictionary.words()) {
    		if(word.equals(dictionaryWord)){
    			comparison.setCorrect(true);
    		} else if(suggest)  {
                Map<String, Double> recommended = comparison.getRecommended();
    			Suggestion suggestion = suggest(word, dictionaryWord);
    			if(suggestion != null) {
        			recommended.put(suggestion.getDictionaryWord(), suggestion.getDistance());
        			comparison.setRecommended(recommended);
    			}
    		}
    	}

        return comparison;
    }

    public String cleanString(String word) {
    	return word.replaceAll("\\p{P}", "").toLowerCase();
    }
    
    public Suggestion suggest(String word, String dictionaryWord) {
    	Suggestion suggestion = new Suggestion();
    	double distance = StringUtils.getJaroWinklerDistance(word, dictionaryWord);
    		
    	if(distance >= distanceThreshold) {
    		suggestion.setDictionaryWord(dictionaryWord);
    		suggestion.setDistance(distance);
    		return suggestion;
    	}
    	
    	return null;
    }
}


