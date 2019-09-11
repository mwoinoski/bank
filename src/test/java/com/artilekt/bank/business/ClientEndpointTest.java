package com.artilekt.bank.business;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClientEndpointTest {
	private final static Client JOHN_SMITH = new Client("John", "Smith", "a001");
	private final static Client JANE_DOE   = new Client("Jane", "Doe",   "a002");
	private final static Client KATE_SMITH = new Client("Kate", "Smith", "a003");
	private final static Client JOHN_DOE   = new Client("John", "Doe",   "a004");
	
	private final static Client JOHN_DOE_COPY   = new Client("John", "Doe",   "a004");
	
	@Autowired
	private CRM crm;

	@Test
	public void addValidClients() {
		crm.addClient(JOHN_SMITH);
		crm.addClient(KATE_SMITH);
	}
	
	@Test(expected = ClientDuplicateException.class)
	public void addDuplicateClient() {
		crm.addClient(JOHN_SMITH);
		crm.addClient(KATE_SMITH);
		crm.addClient(JOHN_DOE);
		crm.addClient(JOHN_DOE_COPY);
	}

}
