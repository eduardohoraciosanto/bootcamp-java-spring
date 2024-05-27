package com.example.bootcamp.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bootcamp.cart.controller.CartController;
import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.cart.service.CartService;
import com.example.bootcamp.item.entity.Item;
import com.example.bootcamp.item.service.ItemService;

@WebMvcTest(CartController.class)
@ContextConfiguration(classes = { CartController.class, ItemService.class, CartService.class })
class CartControllerUnitTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartService cartServiceMock;

	@MockBean
	private ItemService itemServiceMock;

	@Test
	void createCartOK() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.newCart()).thenReturn(serviceCart);

		this.mockMvc.perform(
				post("/cart")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.ID").isString())
				.andExpect(jsonPath("$.ID").value(serviceCart.getID().toString()));

	}

	@Test
	void getCartOK() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(serviceCart);

		this.mockMvc.perform(
				get("/cart/" + serviceCart.getID().toString())).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.ID").isString())
				.andExpect(jsonPath("$.ID").value(serviceCart.getID().toString()));

	}

	@Test
	void getCartNotFound() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

		this.mockMvc.perform(
				get("/cart/" + serviceCart.getID().toString())).andDo(print()).andExpect(status().isNotFound());

	}

	@Test
	void deleteCartOK() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(serviceCart);

		// Mockito syntax suitable for void returns
		Mockito.doNothing()
				.when(cartServiceMock)
				.deleteCart((UUID) notNull());

		this.mockMvc.perform(
				delete("/cart/" + serviceCart.getID().toString())).andDo(print()).andExpect(status().isOk());

	}

	@Test
	void deleteCartNotFound() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

		this.mockMvc.perform(
				delete("/cart/" + serviceCart.getID().toString())).andDo(print()).andExpect(status().isNotFound());

	}

	@Test
	void addItemToCartOK() throws Exception {
		Cart serviceCart = new Cart();
		Item serviceItem = new Item();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(serviceCart);

		when(itemServiceMock.createItem(anyFloat(), anyString(), anyInt())).thenReturn(serviceItem);

		when(cartServiceMock.addItem(
				(UUID) notNull(), (Item) notNull())).thenReturn(serviceCart);

		JSONObject itemObject = new JSONObject();
		itemObject.put("name", "apple");
		itemObject.put("price", 5.2);
		itemObject.put("quantity", 2);

		this.mockMvc.perform(
				post("/cart/" + serviceCart.getID().toString() + "/item")
						.contentType(MediaType.APPLICATION_JSON)
						.content(itemObject.toString()))
				.andExpect(status().isOk());

	}

	@Test
	void addItemToCartNotFound() throws Exception {
		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(null);

		JSONObject itemObject = new JSONObject();
		itemObject.put("name", "apple");
		itemObject.put("price", 5.2);
		itemObject.put("quantity", 2);

		this.mockMvc.perform(
				post("/cart/" + UUID.randomUUID().toString() + "/item")
						.contentType(MediaType.APPLICATION_JSON)
						.content(itemObject.toString()))
				.andExpect(status().isNotFound());

	}

	@Test
	void deleteItemFromCartOK() throws Exception {
		Cart serviceCart = new Cart();
		Item serviceItem = new Item();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(serviceCart);

		when(itemServiceMock.getItem(any(UUID.class))).thenReturn(serviceItem);

		when(cartServiceMock.removeItem(
				(UUID) notNull(), (Item) notNull())).thenReturn(serviceCart);

		// Mockito syntax suitable for void returns
		Mockito.doNothing()
				.when(itemServiceMock)
				.deleteItemById((UUID) notNull());
		
		this.mockMvc.perform(
				delete("/cart/" + serviceCart.getID().toString() + "/item/"+UUID.randomUUID().toString()))
				.andExpect(status().isOk());

	}

	@Test
	void deleteItemFromCartCartNotFound() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenThrow(new IllegalArgumentException());
		
		this.mockMvc.perform(
				delete("/cart/" + serviceCart.getID().toString() + "/item/"+UUID.randomUUID().toString()))
				.andExpect(status().isNotFound());

	}
	

	@Test
	void deleteItemFromCartItemNotFound() throws Exception {
		Cart serviceCart = new Cart();

		when(cartServiceMock.getCart(
				(UUID) notNull())).thenReturn(serviceCart);

		when(itemServiceMock.getItem(any(UUID.class))).thenThrow(new IllegalArgumentException());
		
		this.mockMvc.perform(
				delete("/cart/" + serviceCart.getID().toString() + "/item/"+UUID.randomUUID().toString()))
				.andExpect(status().isNotFound());

	}
}