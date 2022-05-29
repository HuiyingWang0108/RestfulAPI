package com.crud.recipe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crud.recipe.model.Recipe;
import com.crud.recipe.service.RecipeService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public Map<String, List<String>> findAllRecipesName() {

        Map<String, List<String>> res = new HashMap<>();
        List<Recipe> recipes = recipeService.findAllRecipes();
        String kString = "recipes";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            list.add(recipes.get(i).getName());
        }
        res.put(kString, list);
        return res;

    }

    @GetMapping("/recipes/{id}")
    public Recipe findByRecipeId(@PathVariable(value = "id") Long recipeId) {

        return recipeService.findByRecipeId(recipeId);

    }

    @GetMapping("/recipes/details/{name}")
    public JSONObject findByRecipeName(@PathVariable(value = "name") String recipeName) {
        Recipe recipe = recipeService.findByRecipeName(recipeName);
        JSONObject obj = new JSONObject();
        JSONObject objDetail = new JSONObject();
        objDetail.put("ingredients", recipe.getIngredients());
        objDetail.put("numSteps", recipe.getInstructions().size());
        obj.put("details", objDetail);

        return obj;

    }

    @PostMapping("/recipes")
    public Recipe creatRecipe(@Validated @RequestBody Recipe recipe) {

        return recipeService.creatRecipe(recipe);

    }

    @PutMapping("/recipes")
    public void updateRecipe(@Validated @RequestBody Recipe recipe) {

        recipeService.updateRecipe(recipe);

    }

    @DeleteMapping("/recipes/{id}")
    public Map<String, Boolean> deleteRecipeById(@PathVariable(value = "id") Long recipeId) {
        return recipeService.deleteRecipeById(recipeId);
    }

}
