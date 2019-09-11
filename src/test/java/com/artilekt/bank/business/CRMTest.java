package com.artilekt.bank.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CRMTest {
	private CRM crm;
	
	private final static Client JOHN_SMITH = new Client("John", "Smith", "a001");
	private final static Client JANE_DOE   = new Client("Jane", "Doe",   "a002");
	private final static Client KATE_SMITH = new Client("Kate", "Smith", "a003");
	private final static Client JOHN_DOE   = new Client("John", "Doe",   "a004");


	@Before
	public void setup() {
		crm = new CRM();
		crm.addClient(JOHN_DOE);
		crm.addClient(KATE_SMITH);
		crm.addClient(JOHN_SMITH);
		crm.addClient(JANE_DOE);
	}
	

    @Parameters(name = "searchCrmByValidDrLic({0}): client = {1}, size = {2}")
    public static Collection<Object[]> licToClient() {
		return Arrays.asList(new Object[][] {
			{ "a001", JOHN_SMITH, 1 }, { "a002", JANE_DOE, 1 }, { "a003", KATE_SMITH, 1 }, { "a004", JOHN_DOE, 1 }  
		});
    }
    
    @Parameter(0)
    public String drLic;
    @Parameter(1)
    public Client client;
    @Parameter(2)
    public int count;
    
	@Test
	public void searchCrmByValidDrLic() {
		Client cl = crm.findClientByDrLic(drLic);
		
		assertThat(cl).isEqualTo(client);
	}	
	
}
