package com.boghetich.consul.travelclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TravelDiscoveryService {

	private static int instance = 0;

	private final DiscoveryClient discoveryClient;

	public TravelDiscoveryService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	public Optional<URI> travelServiceUrl() {
		List<ServiceInstance> services = discoveryClient.getInstances("travel-server");

		log.info("got {} active services", services.size());
		if(services.size() == 0) {
			return Optional.empty();
		}
		URI uri = services.get(getInstanceNumber(services.size())).getUri();
		log.info("using url: {}", uri);
		return Optional.of(uri);
	}

	private int getInstanceNumber(int numOfService) {
		if (instance+1 > numOfService -1 ) {
			instance = 0;
		} else {
			instance++;
		}
		return instance;
	}
}
