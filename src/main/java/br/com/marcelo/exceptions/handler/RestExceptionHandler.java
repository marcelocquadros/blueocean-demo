package br.com.marcelo.exceptions.handler;

import br.com.marcelo.constants.ErrorCodes;
import br.com.marcelo.exceptions.ExceptionResponse;
import br.com.marcelo.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        logger.error("An unexpected error occur {}", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ErrorCodes.INTERNAL_SERVER_ERROR, ex.getMessage());

        request.getDescription(false);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Invalid Arguments ", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.INVALID_REQUEST, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        logger.error("MethodArgumentNotValid ", ex);

        final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(f -> {
            errors.add(f.getField() +" : " +f.getDefaultMessage());
        });

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ErrorCodes.VALIDATION_FAILED, errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

    }



    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
        logger.error("Product Not found", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ErrorCodes.PRODUCT_NOT_FOUND, ex.getMessage());
        //request.getDescription(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

}