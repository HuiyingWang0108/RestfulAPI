package com.crud.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeExceptionController {

    @ExceptionHandler(value = RecipeNotFoundException.class)
    public ResponseEntity<Object> recipeNotFoundException(RecipeNotFoundException exception) {
        return new ResponseEntity<>("Recipe does not exist", HttpStatus.NOT_FOUND);
     }
}
