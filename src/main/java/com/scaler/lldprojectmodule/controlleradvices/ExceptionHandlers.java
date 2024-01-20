package com.scaler.lldprojectmodule.controlleradvices;

import com.scaler.lldprojectmodule.dtos.ArithematicExceptionDTO;
import com.scaler.lldprojectmodule.dtos.ExceptionDTO;
import com.scaler.lldprojectmodule.exceptions.CategoryNotFoundException;
import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException() {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Something has gone wrong");
        exceptionDTO.setDetail("Check the product id. It probably doesn't exist.");
        return ResponseEntity.ok(exceptionDTO);
    }
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithematicExceptionDTO> handleArithematicException() {
        ArithematicExceptionDTO arithematicExceptionDTO = new ArithematicExceptionDTO();
        arithematicExceptionDTO.setMessage("Something has gone wrong");
        return ResponseEntity.ok(arithematicExceptionDTO);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Something has gone wrong");
        exceptionDTO.setDetail(exception.getMessage());
        return ResponseEntity.ok(exceptionDTO);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleCategoryNotFoundException() {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage("Something has gone wrong");
        exceptionDTO.setDetail("Check the category id. It probably doesn't exist.");
        return ResponseEntity.ok(exceptionDTO);
    }
}
