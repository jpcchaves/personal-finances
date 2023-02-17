package com.jpcchaves.finances.handler;

import com.jpcchaves.finances.common.DataConverter;
import com.jpcchaves.finances.domain.exception.ResourceBadRequestException;
import com.jpcchaves.finances.domain.exception.ResourceNotFoundException;
import com.jpcchaves.finances.domain.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

        String timestamp = DataConverter.convertDateToString(new Date());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.NOT_FOUND.value(), "Resource not found!", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleResourceBadRequestException(ResourceBadRequestException ex) {

        String timestamp = DataConverter.convertDateToString(new Date());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.BAD_REQUEST.value(), "Resource not found!", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleRequestException(Exception ex) {

        String timestamp = DataConverter.convertDateToString(new Date());
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Resource not found!", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String timestamp = DataConverter.convertDateToString(new Date());
        var msg = Objects
                .requireNonNull(
                        ex.getFieldError()).getDefaultMessage();
        ErrorResponse errorResponse = new ErrorResponse(timestamp, HttpStatus.BAD_REQUEST.value(), "Dados inválidos!", msg);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}
