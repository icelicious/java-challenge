package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Recipes;

import java.util.List;

public interface RecipesService {
	/*****************************read*****************************************/
    public List<Recipes> retrieveRecipes();
    public Recipes getRecipes(Long id);    
    /*****************************create****************************************/
    public Recipes createRecipes(Recipes Recipes);
    /*****************************delete****************************************/
    public void deleteRecipes(Long id);
    /*****************************update****************************************/
    public Recipes updateRecipes(Recipes Recipes);
}