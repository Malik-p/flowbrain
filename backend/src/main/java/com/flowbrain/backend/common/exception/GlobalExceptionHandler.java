package com.flowbrain.backend.common.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flowbrain.backend.common.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // ================= VALIDATION =================

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<List<String>>> handleValidationException(
                        MethodArgumentNotValidException ex) {

                List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getDefaultMessage())
                                .toList();

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>(
                                                false,
                                                "Validation Failed",
                                                errors));
        }

        // ================= NOT FOUND =================

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(
                        ResourceNotFoundException ex) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(
                                                false,
                                                ex.getMessage(),
                                                null));
        }

        // ================= BAD REQUEST =================

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ApiResponse<Object>> handleBadRequest(
                        BadRequestException ex) {

                return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>(
                                                false,
                                                ex.getMessage(),
                                                null));
        }

        // ================= FORBIDDEN =================

        @ExceptionHandler(ForbiddenException.class)
        public ResponseEntity<ApiResponse<Object>> handleForbidden(
                        ForbiddenException ex) {

                return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body(new ApiResponse<>(
                                                false,
                                                ex.getMessage(),
                                                null));
        }

        // ================= UNAUTHORIZED =================

        @ExceptionHandler(UnauthorizedException.class)
        public ResponseEntity<ApiResponse<Object>> handleUnauthorized(
                        UnauthorizedException ex) {

                return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body(new ApiResponse<>(
                                                false,
                                                ex.getMessage(),
                                                null));
        }

        // ================= FALLBACK =================

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Object>> handleException(
                        Exception ex) {

                return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponse<>(
                                                false,
                                                ex.getMessage(),
                                                null));
        }

}