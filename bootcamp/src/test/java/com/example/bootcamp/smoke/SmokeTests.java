package com.example.bootcamp.smoke;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bootcamp.cart.controller.CartController;
import com.example.bootcamp.ping.controller.PingController;

@SpringBootTest
class SmokeTests {

	@Autowired
	private CartController cartController;

    @Autowired
	private PingController pingController;

	@Test
	void contextLoads() throws Exception {
		assertThat(cartController).isNotNull();
        assertThat(pingController).isNotNull();
	}
}