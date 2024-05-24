package com.example.bootcamp.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bootcamp.cart.controller.CartController;
import com.example.bootcamp.cart.entity.Cart;
import com.example.bootcamp.cart.service.CartService;
import com.example.bootcamp.item.service.ItemService;

@WebMvcTest(CartController.class)
@ContextConfiguration(classes = {CartController.class,ItemService.class, CartService.class})
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
            post("/cart")).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.ID").isString()).
			andExpect(jsonPath("$.ID").value(serviceCart.getID().toString()));
        
	}
    
}