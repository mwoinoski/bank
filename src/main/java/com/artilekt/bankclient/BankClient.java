package com.artilekt.bankclient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.artilekt.bank.business.Client;


@SpringBootApplication
public class BankClient {

	private static final Logger log = LoggerFactory.getLogger(BankClient.class);
	private static final String ROOT_URI = "http://localhost:8080";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	public static void main(String args[]) {
		(new SpringApplicationBuilder(BankClient.class)).web(false).run(args);
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Client client = restTemplate.postForObject("/clients", 
													   new Client("Jane", "Doe", "a0018"),
													   Client.class);
			log.info("created client: "+client.toString());
			client = restTemplate.postForObject("/clients", 
										        new Client("John", "Smith", "b0001"),
					                            Client.class);
			log.info("created client: "+client.toString());
			
			List<Client> clients = restTemplate.getForObject("/clients", List.class);
			log.info("retrieved clients: "+clients);
		};
	}
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
	   return new RestTemplateBuilder()
	        .rootUri(ROOT_URI)
	        .basicAuthorization(USERNAME, PASSWORD);
	}


}
