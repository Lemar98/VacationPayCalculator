package ru.lemar98.vacationpaycalculator.validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GeneralValidationHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger("ValidationHandler");

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.put("error", error.getDefaultMessage()));

        if (!errors.isEmpty())
            logger.info("Following errors will be sent as response: {}", errors);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
