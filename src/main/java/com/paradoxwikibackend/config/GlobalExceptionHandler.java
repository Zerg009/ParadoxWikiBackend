package com.paradoxwikibackend.config;
import com.paradoxwikibackend.exception.EmailAlreadyRegisteredException;
import com.paradoxwikibackend.exception.DefaultResponse;
import com.paradoxwikibackend.exception.ParadoxNotFoundException;
import com.paradoxwikibackend.exception.UserNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<DefaultResponse> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex) {
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DefaultResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParadoxNotFoundException.class)
    public ResponseEntity<DefaultResponse> handleParadoxNotFoundException(ParadoxNotFoundException ex) {
        return new ResponseEntity<>(new DefaultResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<DefaultResponse> handleDataAccessException(DataAccessException ex) {
        return new ResponseEntity<>(new DefaultResponse("Error saving favorite paradox: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
