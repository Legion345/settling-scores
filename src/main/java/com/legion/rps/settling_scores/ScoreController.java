package com.legion.rps.settling_scores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ScoreController {

	@GetMapping("/health-check")
	public String getHealthCheck() {
		return "Situation Normal All Fired Up!";
	}
}
