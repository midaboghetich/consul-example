package com.boghetich.consul.travelclient.web;

import com.boghetich.consul.travelclient.service.TravelDiscoveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;

@RestController
public class MainController {

	private final TravelDiscoveryService discoveryService;
	private final RestTemplate restTemplate = new RestTemplate();

	public MainController(TravelDiscoveryService discoveryService) {
		this.discoveryService = discoveryService;
	}

	@GetMapping("/travel-to")
	public String travel() throws RestClientException,
			ServiceUnavailableException {
		URI service = discoveryService.travelServiceUrl()
				.map(s -> s.resolve("/travel"))
				.orElseThrow(ServiceUnavailableException::new);
		return restTemplate.getForEntity(service, String.class)
				.getBody();
	}

	@GetMapping("/travel-hc")
	public ResponseEntity<String> healthcheck() {
		String message = "I'm alive! btw Im the client";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
