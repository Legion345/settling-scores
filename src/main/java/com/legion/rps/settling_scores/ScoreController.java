package com.legion.rps.settling_scores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin
public class ScoreController {

	static Score score = new Score(0, 0, 0);

	@PutMapping("/score")
	public Score replaceScore(@RequestBody Score newScore) {
		score = newScore;
		return score;
	}
	
	@DeleteMapping("/score")
	public void deleteScore() {
		score = null;
	}

	@PatchMapping("/score/wins")
	public Score updateWins(@RequestParam(name="new-value")int newValue) {
		score.wins = newValue;
		return score;
	}

	@GetMapping("/health-check")
	public String getHealthCheck() {
		return "Situation Normal All Fired Up!";
	}


	@GetMapping("/score")
	public Score getScore() {
		return score;
	}
	
	@GetMapping("/score/{winslossesorties}")
	public int getWinsLossesOrTies(@PathVariable String winslossesorties) {
		if (winslossesorties.equalsIgnoreCase("wins")) {
			return score.wins;
		}
		if (winslossesorties.equalsIgnoreCase("ties")) {
			return score.ties;
		}
		return score.losses;
	}

	@PostMapping("/score/{increasewinslossesorties}")
	public int postWinsLossesOrTies(@PathVariable String increasewinslossesorties) {
		if (increasewinslossesorties.equalsIgnoreCase("wins")) {
			score.wins++;
			return score.wins;
		}
		if (increasewinslossesorties.equalsIgnoreCase("ties")) {
			score.ties++;
			return score.ties;
		}
		score.losses++;
		return score.losses;
	}

}
