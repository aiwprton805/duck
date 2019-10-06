package com.magistr.duck.mvc;

import com.magistr.duck.common.exceptions.ProfileGroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice(annotations = RestController.class)
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProfileGroupNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProfileGroupNotFoundException() {
        ResponseEntity<ErrorMessage> responseEntity = new ResponseEntity<>(new ErrorMessage("Bad token"), HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    class ErrorMessage {
        private String message;

        private ErrorMessage() {
        }

        private ErrorMessage(String message) {
            this.message = message;
        }

        private String getMessage() {
            return message;
        }

        private void setMessage(String message) {
            this.message = message;
        }
    }
    //inner class
}
//class

