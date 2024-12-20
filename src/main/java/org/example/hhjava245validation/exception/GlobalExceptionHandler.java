package org.example.hhjava245validation.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //Hier speichern wir gleich alle Fehlermeldungen und die jeweiligen Felder
        //Eine Map kann später ausgezeichnet in ein JSON umgewandelt werden, da bei Key-Value basiert sind.
        Map<String, String> validationErrors = new HashMap<>();
        //Alle Fehler der Validierung auslesen und in eine Liste speichern
        List<ObjectError> validErrors = ex.getBindingResult().getAllErrors();

        validErrors.forEach(error -> {
            //"casting", also Umwandeln (bitte wirklich nur dann machen wenn unbedingt notwendig, da Fehlerquelle!)
            //von allen Fehlern (Field-/Objecterrors) in FieldErrors
            String fieldName = ((FieldError) error).getField();
            //Auslesen der Fehlermeldung des jeweiligen Feldes
            String errorMessage = error.getDefaultMessage();
            //Abspeichern der jeweiligen Feld+Fehlermeldungskombination in der Map
            validationErrors.put(fieldName, errorMessage);
        });
        //Rückgabe der Map als ResponseEntity "verpackt". Diesen Schritt hatten wir bei unseren ExceptionHandlern
        //mit @ResponseStatus erledigt. Hier machen wir das ganze händisch.
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
