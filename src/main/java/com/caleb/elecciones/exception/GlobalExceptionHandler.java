package com.caleb.elecciones.exception;

import com.caleb.elecciones.response.GenericResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public GenericResponse<Object> handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;
        GenericResponse<Object> genericResponse = null;

        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");

        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        if (exception instanceof VotoExistenteException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "El usuario ya voto");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
            genericResponse = new GenericResponse<>(false, errorDetail, "Ocurrió un error");
        }

        return genericResponse;
    }
}