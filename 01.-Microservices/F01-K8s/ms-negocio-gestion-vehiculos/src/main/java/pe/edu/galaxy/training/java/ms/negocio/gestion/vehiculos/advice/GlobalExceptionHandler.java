package pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pe.edu.galaxy.training.java.ms.negocio.gestion.vehiculos.exceptions.ResourceNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, request.getDescription(false));
    }
}
