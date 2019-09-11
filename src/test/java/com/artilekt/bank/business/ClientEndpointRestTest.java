package com.artilekt.bank.business;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClientEndpointRestTest {
    @LocalServerPort
    private int serverPort;

    @Autowired
    private CRM crm;
    
    @Before
    public void init(){
        RestAssured.port = serverPort;
        crm.addClient(new Client("Vlad", "Olenin", "a001"));
        crm.addClient(new Client("Dmitri", "Olenin", "a002"));
    }

    @Test
    public void findAllClientsPositive() throws Exception {
         given()
        .when()
            .get("/clients")
        .then()
            .statusCode(200)
            .body("firstName", hasItems("Vlad", "Dmitri"),
            	  "lastName", hasItem("Olenin"));
    }
    
    @Test
    public void findClientByFirstName() throws Exception {
		    given()
		   .when()
		       .get("/clients?firstName=Vlad")
		   .then()
		       .statusCode(200)
		       .body("firstName", hasItem("Vlad"),
		       	     "lastName", hasItem("Olenin"),
		             "firstName", not(hasItem("Dmitri")));
    }
    
    @Test
    public void createClient() throws Exception {
        given()
        	.contentType("application/json")
        	.body(new Client("James", "Smith", "b001"))
        .when()
        	.post("/clients").
	    then().
	        statusCode(200);

        Client client = get("/clients/b001").as(Client.class);
        assertThat(client.getLastName()).isEqualTo("Smith");
    }

    

}
