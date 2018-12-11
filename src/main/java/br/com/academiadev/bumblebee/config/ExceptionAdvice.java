package br.com.academiadev.bumblebee.config;

import br.com.academiadev.bumblebee.exception.ConflictException;
import br.com.academiadev.bumblebee.exception.ObjectNotFoundException;
import br.com.academiadev.financas.dto.ValidationErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNaoEncontradoException(ObjectNotFoundException exception){
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(p -> p.getField(), FieldError::getDefaultMessage));

        return ValidationErrorDTO.builder().message("Não foi possível processar esta requisição.")
                .status(UNPROCESSABLE_ENTITY.value())
                .timestamp(System.currentTimeMillis())
                .erros(fieldErrors).build();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public String handleConflictException(ConflictException exception) {
        return exception.getMessage();
    }

}
