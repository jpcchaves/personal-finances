package com.jpcchaves.finances.security;

import com.google.gson.JsonObject;
import com.jpcchaves.finances.common.DataConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create response content
        JsonObject obj = new JsonObject();

        obj.addProperty("timestamp", DataConverter.convertDateToString(new Date()));
        obj.addProperty("status", HttpServletResponse.SC_UNAUTHORIZED);
        obj.addProperty("title", "Acesso negado!");
        obj.addProperty("message", "Você não possui autorização para acessar esse recurso!");

        res.getWriter().write(obj.toString());

    }
}
