package com.example.bootcamp.interceptors;

import java.time.Duration;
import java.time.Instant;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
    private static Instant requestStart;

    @SuppressWarnings("null")
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        requestStart = Instant.now();
        log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + getParameters(request));

        return true;
    }

    @SuppressWarnings("null")
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            ex.printStackTrace();
        }
        long elapsedTime = Duration.between(requestStart, Instant.now()).toMillis();

        log.info("[requestDurationMilis]["+ elapsedTime +"][afterCompletion][" + request + "][exception: " + ex + "]"+
            "[X-Request-ID]["+getXRequestID(request)+"]");
    }

    private String getParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e != null) {
            posted.append("?");
        }
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            posted.append(request.getParameter(curr));
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
        if (ipAddr != null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }

    private String getXRequestID(HttpServletRequest request){
        Object xreqAttribute = request.getAttribute(XRequestIDInterceptor.xRequestAttributeName);
        
        return xreqAttribute.toString();
    }

}
