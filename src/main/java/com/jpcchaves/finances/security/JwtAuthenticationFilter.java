package com.jpcchaves.finances.security;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jpcchaves.finances.common.DataConverter;
import com.jpcchaves.finances.domain.model.ErrorResponse;
import com.jpcchaves.finances.domain.model.User;
import com.jpcchaves.finances.dto.user.LoginRequestDto;
import com.jpcchaves.finances.dto.user.LoginResponseDto;
import com.jpcchaves.finances.dto.user.UserResponseDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/v1/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws AuthenticationException, IOException {

        User user = (User) authResult.getPrincipal();
        String token = jwtUtil.generateToken(authResult);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoto(user.getPhoto());
        userResponseDto.setInactivationDate(user.getInactivationDate());
        userResponseDto.setCreatedAt(user.getCreatedAt());

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken("Bearer: " + token);
        loginResponseDto.setUser(userResponseDto);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().write(new Gson().toJson(loginResponseDto));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        String timestamp = DataConverter.convertDateToString(new Date());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.UNAUTHORIZED.value(), "Unauthorized", failed.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().write(new Gson().toJson(errorResponse));
    }
}
