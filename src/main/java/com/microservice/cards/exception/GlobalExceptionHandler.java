package com.microservice.cards.exception;

import com.microservice.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
// @RestControllerAdvice -- use when we want @ControllerAdvice + @ResponseBody (to send response in json format)
// extending ResponseEntityExceptionHandler to handle validation exception i.e. handleMethodArgumentNotValid which is a method in ResponseEntityExceptionHandler class.
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(CardAlreadyExistsException exception,
                                                                             WebRequest webRequest){
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false), // false means  don't include the request URI
                BAD_REQUEST,
                exception.getMessage(),
                now()
        );
        return new ResponseEntity<>(errorResponseDTO, BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                NOT_FOUND,
                exception.getMessage(),
                now()
        );
        return new ResponseEntity<>(errorResponseDTO,NOT_FOUND);
    }

    // will be executed when any runtime exception will arise and not handled in the business logic.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                now()
        );
        return new ResponseEntity<>(errorResponseDTO, INTERNAL_SERVER_ERROR);
    }

    // All the validations will be sent in error response due to this handler
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, BAD_REQUEST);
    }
}
