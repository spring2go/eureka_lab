package io.spring2go.timeclient;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class TimeClientApplication {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public static void main(String[] args) {
		SpringApplication.run(TimeClientApplication.class, args);
	}
	
	@GetMapping
	public String getTime() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}
	
	@GetMapping("/getTimeViaEurekaClient")
	public String getTimeViaEurekaClient() {
		List<ServiceInstance> instances = discoveryClient.getInstances("time-service");
		
		String result = "no instance available";
		
		if (instances != null && instances.size() > 0) {
			ServiceInstance instance = instances.get(0);
			
			 // Invoke server based on host and port. 
			 // Example using RestTemplate.    
			 URI productUri = URI.create(String
			   .format("http://%s:%s",
			    instance.getHost(), instance.getPort()));
			 
			 result = restTemplate.getForObject(productUri, String.class);
		}
		
		return result;
	}
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
