package com.jpcchaves.finances.security;

import com.google.gson.JsonObject;
import com.jpcchaves.finances.common.DataConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Set response code
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Set response content type to JSON
        response.setContentType("application/json;charset=UTF-8");

        // Create response content
        JsonObject obj = new JsonObject();

        obj.addProperty("timestamp", DataConverter.convertDateToString(new Date()));
        obj.addProperty("status", HttpServletResponse.SC_FORBIDDEN);
        obj.addProperty("title", "Acesso negado!");
        obj.addProperty("message", "Você não possui autorização para acessar esse recurso!");

        // Add content to the response
        response.getWriter().write(obj.toString());
    }
}

