package com.example.bootcamp.ping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bootcamp.interceptors.LoggingInterceptor;
import com.example.bootcamp.ping.entity.Ping;

@RestController
public class PingController {
    private static final String version = System.getenv("HASH");
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    @GetMapping("/ping")
	public Ping greeting() {
        log.info("PING Controller Called!");
		return new Ping(version);
	}
}
