# SpellCheck

This repository will go private by Friday.

### Concerns:

Use Spring, build a rest service to specifically handle JSON entities containing a string of data to check each individual word and given a boolean flag possibly return suggestions using a Jaro-Winkler algorithm implementation (imported from org.apache.commons's commons-lang3 package). I decided against implementing my own algorithm (and there are a few I was reaching for) because you don't need to reinvent the wheel. There's plenty of great packages out there that handle 85% of the cases that I'm looking for, and Jaro-Winkler (something I've used in the past), while a bit slower than a Levenshtein implementation, had a nice accuracy to it.

Data Structures:
### Comparison Segment
- LocalDictionary - A Java Class that acted as a wrapper for the US.dic file that was provided
- ComparedWord - Each individual word had to have a class to hold nested suggestion classes, whether or not it was correct, and should be easy to cache.
- StringComparative - Matched the JSON that was accepted from the request. Carried knowledge of the string to be changed and whether or not suggestions were requested.

### Suggestion Segment
- Suggestion - Each individual word that's checked has multiple suggestions that can be attributed to it. This is the lowest heriditary bit of information provided.

###Things I'd change

If I was going to deploy this, I've already cleaned up some possible issues with input validation. Other things I'd look for are caching individual words (you know, similar to how elasticsearch works) based on frequency. As they're created, we create cached elements that already have suggestions posted to them. Another thing would be if there's no suggestions, and suggestions are asked for, to run them again with a lower threshold until some results are found. 

Ideally I'd have put some of these in the database as well. By caching the values, creating a join table that matches words with suggestions, we'd be able to leverage SQL to improve performance as well as reading from a file is wildly slow.

Amusingly I wrote most of this out Friday afternoon, and spent most of Saturday morning trying to figure out why I couldn't get it to work in Intellij. Literally, googled the hell out of it. Asked my friend who also uses intellij to screenshare with me, because it seemed as if there was a configuration issue or just my general ignorance to some of the inner workings of it. Certain spring packages weren't being found. It was a wild wild time. I put it in Spring Tool Suite and somehow it managed to start up like a champion and work pretty well.

(Update: I booted it up on intellij on my laptop as opposed to my desktop, and it ran just fine. So, I'm assuming my desktop's configuration of intellij is a bit borked, and needs to be cleaned and rebuild. So, there should be no Eclipse/Intellij related tomfoolery involved. It was something about `[ERROR] class file for org.springframework.core.env.EnvironmentCapable not found`. Which seemed ridiculous. Because there was no reason that spring core wouldn't be loaded. It was in my external files. I spent more time debugging that than actually working on this, which was frustrating.)

Not very often to I write code, and have to do very little to actually change it. Even in Ruby. I think I spent another couple of hours trying to improve somethings, get some of the O runtime down (because I was iterating through the dictionary twice on each word lookup). I spent probably as much time diving into Apache, Core Java and Spring documentation as I did writing it, that's for sure. And the manditory 3 hour refresher of the different collection interfaces in Java. That too.

By hour 4 (of non-configuration changes), I was actually feeling much more confident in what I was doing, knowing where to go, how to put things together. Kind of like riding a bike.

Thanks for Reading!

Dan

Sample Input (ContentType application/JSON).

```
{
	"phrase": "do this her val toam corse rohel buddy",
	"suggest": true
}
```

Output
```
[
  {
    "word": "do",
    "correct": true,
    "recommended": {}
  },
  {
    "word": "this",
    "correct": true,
    "recommended": {
      "thais": 0.95,
      "thins": 0.95
    }
  },
  {
    "word": "her",
    "correct": true,
    "recommended": {}
  },
  {
    "word": "val",
    "correct": true,
    "recommended": {}
  },
  {
    "word": "toam",
    "correct": false,
    "recommended": {}
  },
  {
    "word": "corse",
    "correct": false,
    "recommended": {
      "core": 0.95,
      "cores": 0.95,
      "coarse": 0.96,
      "course": 0.96,
      "corpse": 0.96,
      "corset": 0.97
    }
  },
  {
    "word": "rohel",
    "correct": false,
    "recommended": {}
  },
  {
    "word": "buddy",
    "correct": true,
    "recommended": {}
  }
]
```
