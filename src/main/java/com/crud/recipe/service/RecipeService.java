package com.crud.recipe.service;

import java.util.List;
import java.util.Map;

import com.crud.recipe.model.Recipe;

import org.springframework.http.ResponseEntity;

public interface RecipeService {
    public List<Recipe> findAllRecipes();
    public Recipe findByRecipeId(Long id);
    public Recipe findByRecipeName(String recipeName);
    public Recipe creatRecipe(Recipe recipe);
    public ResponseEntity<Object> updateRecipe(Recipe recipe);
    public Map<String, Boolean> deleteRecipeById(Long recipeId);
}
