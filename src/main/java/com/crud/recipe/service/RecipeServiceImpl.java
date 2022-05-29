package com.crud.recipe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.crud.recipe.exception.RecipeNotFoundException;
import com.crud.recipe.model.Recipe;
import com.crud.recipe.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAllRecipes() {

        return recipeRepository.findAll();
        
    }

    @Override
    public Recipe findByRecipeId(Long id) {
        
        return recipeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id %d not found", id)));

    }

    @Override
    public Recipe findByRecipeName(String recipeName) {

        return recipeRepository.findByRecipeName(recipeName)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with name %d not found", recipeName)));
    }

    @Override
    public Recipe creatRecipe(Recipe recipe) {

        Optional<Recipe> recipeDB = recipeRepository.findByRecipeName(recipe.getName());

        if (recipeDB.isPresent()) {
            throw new IllegalArgumentException("error: Recipe already exists");
        }

        return recipeRepository.save(recipe);

    }

    @Override
    public ResponseEntity<Object> updateRecipe(Recipe recipe) {

        Optional<Recipe> recipeDB = recipeRepository.findByRecipeName(recipe.getName());

        if (Objects.isNull(recipeDB)) throw new RecipeNotFoundException();

        Recipe newRecipe = recipeDB.get();
        newRecipe.setName(recipe.getName());
        newRecipe.setIngredients(recipe.getIngredients());
        newRecipe.setInstructions(recipe.getInstructions());
        recipeRepository.save(newRecipe);
        return new ResponseEntity<>("Recipe is updated successfully", HttpStatus.OK);
        
    }

    @Override
    public Map<String, Boolean> deleteRecipeById(Long recipeId) {

        Optional<Recipe> recipeDB = recipeRepository.findById(recipeId);

        if (Objects.isNull(recipeDB)) throw new RecipeNotFoundException();

        recipeRepository.deleteById(recipeId);

        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);
        return res;
    }
    
}
