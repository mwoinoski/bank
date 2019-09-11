package com.artilekt.bank.endpoints;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.artilekt.bank.business.Account;
import com.artilekt.bank.business.Bank;
import com.artilekt.bank.business.CRM;
import com.artilekt.bank.business.Client;
import com.artilekt.bank.dto.AccountCreationRequest;

@RestController
@RequestMapping("/clients")
public class ClientEndpoint {
	@Autowired
	private CRM crm;
	
	@PostMapping
	public Client createClient(@RequestBody Client client) {
		crm.addClient(client);
		return client;
	}
	
//	@GetMapping
//	public List<Client> listClients() {
//		return crm.getClients();
//	}
	
//	@GetMapping(params = "driverLicense")
//	public List<Client> searchClientsByDriverLicense(@RequestParam(name = "driverLicense", required=true) String driverLicense) {
//		return crm.searchClientsByDriverLicense(driverLicense);
//	}
	
	@GetMapping
	public List<Client> searchClients(@RequestParam(required=false) Map<String, String> searchAttributes) {
		
		Predicate<Client> searchCriteria = client -> {
			return  (searchAttributes.get("driverLicense") == null || client.getDriverLicense().equalsIgnoreCase(searchAttributes.get("driverLicense"))) &&
					(searchAttributes.get("firstName") == null || client.getFirstName().equalsIgnoreCase(searchAttributes.get("firstName"))) &&
					(searchAttributes.get("lastName") == null || client.getLastName().equalsIgnoreCase(searchAttributes.get("lastName")));
		};
		return crm.searchClients(searchCriteria);
	}
	
	@GetMapping(value="/{clientId}")
	public Client getAccount(@PathVariable(name = "clientId", required = true) String clientId) {
		return crm.findClientById(clientId);
	}

}
