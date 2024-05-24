package com.example.bootcamp.integration;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.bootcamp.cart.entity.CartRecord;
import com.example.bootcamp.ping.entity.Ping;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void pingEndpointOK() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/ping",
				Ping.class)).isNotNull();
	}


    @Test
	void createCartOK() throws Exception {
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/api/v1/cart", 
        null, 
        CartRecord.class)).isNotNull();
	}
}