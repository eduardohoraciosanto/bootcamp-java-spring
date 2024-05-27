package com.example.bootcamp.integration;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

		ResponseEntity<Ping> pRes = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/ping", HttpMethod.GET,
				HttpEntity.EMPTY, Ping.class);

		assertThat(pRes.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	@SuppressWarnings("null") // the cart won't be null
	void createCartOK() throws Exception {

		ResponseEntity<CartRecord> cRes = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart", HttpMethod.POST,
				HttpEntity.EMPTY, CartRecord.class);

		assertThat(cRes.getStatusCode().value()).isEqualTo(200);
		assertThat(cRes.getBody().ID()).isNotNull();
	}

	@Test
	@SuppressWarnings("null") // the cart won't be null
	void getCartOK() throws Exception {
		// Create cart to then get it
		ResponseEntity<CartRecord> cartCreateRes = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart", HttpMethod.POST,
				HttpEntity.EMPTY, CartRecord.class);

		ResponseEntity<CartRecord> cRes = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart/" + cartCreateRes.getBody().ID(), HttpMethod.GET,
				HttpEntity.EMPTY, CartRecord.class);

		assertThat(cRes.getStatusCode().value()).isEqualTo(200);
		assertThat(cRes.getBody().ID()).isEqualTo(cartCreateRes.getBody().ID());
	}

	@Test
	void deleteCartOK() throws Exception {
		// creating cart to then delete it
		CartRecord c = this.restTemplate.postForObject("http://localhost:" + port + "/api/v1/cart",
				null,
				CartRecord.class);

		ResponseEntity<Void> resp = this.restTemplate.exchange("http://localhost:" + port + "/api/v1/cart/" + c.ID(),
				HttpMethod.DELETE,
				HttpEntity.EMPTY, Void.class);

		assertThat(resp.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	@SuppressWarnings("null") // the cart won't be null
	void addItemToCartOK() throws Exception {
		// creating cart to then add an item to it
		ResponseEntity<CartRecord> cResCreate = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart", HttpMethod.POST,
				HttpEntity.EMPTY, CartRecord.class);

		assertThat(cResCreate.getStatusCode().value()).isEqualTo(200);

		// create the HttpEntity to post as body
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject itemObject = new JSONObject();
		itemObject.put("name", "apple");
		itemObject.put("price", 5.2);
		itemObject.put("quantity", 2);

		HttpEntity<String> request = new HttpEntity<String>(itemObject.toString(), headers);

		ResponseEntity<CartRecord> cResAdd = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart/" + cResCreate.getBody().ID() + "/item", HttpMethod.POST,
				request, CartRecord.class);

		assertThat(cResAdd.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	@SuppressWarnings("null") // the cart won't be null
	void deleteItemfromCartOK() throws Exception {
		// creating cart to then add an item to it, finally we'll remove the item
		ResponseEntity<CartRecord> cResCreate = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart", HttpMethod.POST,
				HttpEntity.EMPTY, CartRecord.class);

		assertThat(cResCreate.getStatusCode().value()).isEqualTo(200);
		String cartID = cResCreate.getBody().ID().toString();

		// create the HttpEntity to post as body
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject itemObject = new JSONObject();
		itemObject.put("name", "apple");
		itemObject.put("price", 5.2);
		itemObject.put("quantity", 2);

		HttpEntity<String> request = new HttpEntity<String>(itemObject.toString(), headers);

		ResponseEntity<CartRecord> cResAdd = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart/" + cartID + "/item", HttpMethod.POST,
				request, CartRecord.class);

		assertThat(cResAdd.getStatusCode().value()).isEqualTo(200);

		String itemID = cResAdd.getBody().items().getFirst().getID().toString();

		// finally delete the item
		ResponseEntity<CartRecord> cResItemDelete = this.restTemplate.exchange(
				"http://localhost:" + port + "/api/v1/cart/" + cartID + "/item/" + itemID, HttpMethod.DELETE,
				HttpEntity.EMPTY, CartRecord.class);

		assertThat(cResItemDelete.getStatusCode().value()).isEqualTo(200);
	}

}