package com.artilekt.bank.business;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client implements Serializable {

	private String firstName;
	private String lastName;
	private String driverLicense;
	
	
	public Client(String firstName, String lastName, String driverLicense) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.driverLicense = driverLicense;
	}
	
	public Client() {}

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverLicense == null) ? 0 : driverLicense.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (driverLicense == null) {
			if (other.driverLicense != null)
				return false;
		} else if (!driverLicense.equals(other.driverLicense))
			return false;
		return true;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDriverLicense() {
		return driverLicense;
	}
	
	
	public String getClientId() { return driverLicense; }

	@Override
	public String toString() {
		return "Client [id = " + getClientId() + "]";
	}
	
	
	
}
