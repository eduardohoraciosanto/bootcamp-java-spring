package com.example.bootcamp.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bootcamp.ping.controller.PingController;

@WebMvcTest(PingController.class)
@ContextConfiguration(classes = {PingController.class})
class PingControllerUnitTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void PingOK() throws Exception {
         
		this.mockMvc.perform(
            get("/ping")).
            andDo(print()).
            andExpect(status().isOk());
        
	}
    
}