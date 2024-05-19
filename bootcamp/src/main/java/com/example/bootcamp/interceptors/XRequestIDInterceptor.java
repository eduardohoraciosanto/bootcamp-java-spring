package com.example.bootcamp.interceptors;

import java.time.Duration;
import java.time.Instant;
import java.util.Enumeration;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class XRequestIDInterceptor implements HandlerInterceptor {
    //xRequestAttributeName to be used when setting/getting headers/attributes 
    public static final String xRequestAttributeName = "X-Request-ID";

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String xreqidStr = UUID.randomUUID().toString();

        request.setAttribute(xRequestAttributeName, xreqidStr);
        response.addHeader(xRequestAttributeName, xreqidStr);
        return true;
    }

}
