package io.github.bapadua.demo.handlers;

import io.github.bapadua.demo.model.ApiError;
import oracle.jdbc.OracleDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError invalidMethodException(MethodArgumentNotValidException ex) {
        List<String> errorList = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ApiError(errorList);
    }

    @ExceptionHandler(OracleDatabaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError invalidMethodException(OracleDatabaseException ex) {
        return new ApiError(ex.getMessage());
    }
    //OracleDatabaseException
}
