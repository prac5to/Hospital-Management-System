package com.example.hospitalimanagement.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
@ExceptionHandler(IllegalStateException.class)
public ResponseEntity<String> handleSlotTaken(IllegalStateException ex)
{
return ResponseEntity.badRequest().body(ex.getMessage());
}
}
