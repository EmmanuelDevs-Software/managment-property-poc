package com.chadwick.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelsList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorsList = manv.getBindingResult().getFieldErrors();
        for(FieldError fieldError: fieldErrorsList){
            errorModel = new ErrorModel();
            errorModel.setCode(fieldError.getField());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModelsList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
