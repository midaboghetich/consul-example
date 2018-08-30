package com.boghetich.consul.travelserver.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class TravelServerController {


	private static final String[] countries = {"Austria","Italy","Belgium","Latvia","Bulgaria","Lithuania","Croatia","Luxembourg","Cyprus","Malta","Czech Republic","Netherlands","Denmark","Poland","Estonia","Portugal","Finland","Romania","France","Slovakia","Germany","Slovenia","Greece","Spain","Hungary","Sweden","Ireland","United Kingdom"};
	@GetMapping("/travel-hc")
	public ResponseEntity<String> healthcheck() {
		String message = "I'm alive! btw Im the server";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/travel")
	public String travel() {
		log.info("hey, got a call!");

		return getRandomCountry();
	}

	private String getRandomCountry() {
		Random r = new Random();
		int min = 0;
		int max = countries.length -1;
		int result = r.nextInt(max-min) + min;
		String country = countries[result];
		log.info("returning {}", country);
		return country;
	}
}
