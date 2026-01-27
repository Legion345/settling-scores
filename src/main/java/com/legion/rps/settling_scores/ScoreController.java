package com.legion.rps.settling_scores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin
public class ScoreController {

	static Score score = new Score(30, 20, 10);	

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
/*
	@GetMapping("/score/wins")
	public int getWins() {
		return score.wins;
	}
	
	@GetMapping("/score/losses")
	public int getLosses() {
		return score.losses;
	}

	@GetMapping("/score/ties")
	public int getTies() {
		return score.ties;
	}*/
}
