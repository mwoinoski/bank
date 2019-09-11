package com.artilekt.bank.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CRM {
	private List<Client> clients = new ArrayList<>();

	public void addClient(Client client) {
		if (contains(client))
			throw new ClientDuplicateException("Client ["+client+"] already exists");
		clients.add(client);
	}

	public Client findClientByDrLic(String drLic) {
		return findClientById(drLic);
	}
	
	public Client findClientById(String clientId) {
		Optional<Client> cl = clients.stream().filter(client -> client.getClientId().equals(clientId)).findFirst();
		if (cl.isPresent())   return cl.get();
			
		throw new ClientNotFoundException("Client ["+clientId+"] not found");
	}

	public List<Client> searchClients(Predicate<Client> searchCriteria) {
		return clients.stream()
					.filter(searchCriteria::test)
					.collect(Collectors.toList());
	}

	public List<Client> listAllClients() {
		return Collections.unmodifiableList(clients);
	}

	
	public boolean contains(Client client) {
		return clients.contains(client);
	}

}
