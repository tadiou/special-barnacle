package com.spellCheck.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spellCheck.Data.ComparedWord;
import com.spellCheck.Data.StringComparative;
import com.spellCheck.Service.SpellCheckService;

@RestController
@RequestMapping("/")
public class SpellCheckController {

  @Autowired
  private SpellCheckService spellCheckService;

  /*
   * Example @RequestBody
   *
   * { 
   * 	"phrase": "string method baaaad rite oaman goldbv poly nose be bi by cm", (space deliniated string)
   * 	"suggest": true (boolean)
   * }
   * 
   * It deals with punctuation as well
   * {
   * 	"phrase": "There was a wisteria vine blooming for the second time that summer on a wooden trellis before one window, into which sparrows came now and then in random gusts, making a dry vivid dusty sound before going away: and opposite Quentin, Miss Coldfield in the eternal black which she had worn for forty-three years now, whether for sister, father, or nothusband none knew, sitting so bolt upright in the straight hard chair that was so tall for her that her legs hung straight and rigid as if she had iron shinbones and ankles, clear of the floor with that air of impotent and static rage like children’s feet, and talking in that grim haggard amazed voice until at last listening would renege and hearing-sense self-confound and the long-dead object of her impotent yet indomitable frustration would appear, as though by outraged recapitulation evoked, quiet inattentive and harmless, out of the biding and dreamy and victorious dust",
   * 	"suggest": false
   * }
   * 
   */
  @RequestMapping(method = RequestMethod.POST,
                  consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody List<ComparedWord> postCheck(@RequestBody StringComparative body) {
      return spellCheckService.checkString(body);
  }
}