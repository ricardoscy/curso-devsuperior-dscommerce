package com.devsuperior.dscommerce.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> fieldErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldName, String message){
        fieldErrors.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getFieldErrors() {
        return fieldErrors;
    }
}
