package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Recipes;
import jp.co.axa.apidemo.services.RecipesService;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @GetMapping("/")
    public List<Recipes> getRecipes() {
        List<Recipes> recipes = recipesService.retrieveRecipes();
        return recipes;
    }
    
    @GetMapping("/{recipesId}")
    //@Cacheable(value = {"employee"}, key = "#employeeId") //can add more parameters ---> condition = "",unless = "",keyGenerator = "" 
    public Recipes getRecipes(@PathVariable(name="recipesId")Long recipesId) {
    	return recipesService.getRecipes(recipesId);
    }

    @PostMapping("/")
    public Recipes createRecipes(@RequestBody Recipes recipes){
    	Recipes e = recipesService.createRecipes(recipes);
        System.out.println("Employee Saved Successfully");
        return e;
    }

    @DeleteMapping("/{recipesId}")
    //@CacheEvict(value = {"employee"}, beforeInvocation = true,key="#employeeId")
    public String deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
    	recipesService.deleteRecipes(employeeId);
        System.out.println("Employee Deleted Successfully");
        return "success";
    }

    @PatchMapping("/{recipesId}")
    //@CachePut(value = {"employee"}, key = "#employeeId")
    public Recipes updateEmployee(@RequestBody Recipes recipes,
                               @PathVariable(name="recipesId")Long recipesId){
    	//get record from employeeService
    	Recipes emp = recipesService.getRecipes(recipesId);
    	if(emp == null)
    		emp = recipesService.getRecipes(1L);
        if(emp != null){
        	//set data to emp
        	emp.setTitle(recipes.getTitle());
        	emp.setIngredients(recipes.getIngredients());
        	emp.setServes(recipes.getServes());
        	emp.setMaking_time(recipes.getMaking_time());
        	emp.setCost(recipes.getCost());
        	//update record
        	recipesService.updateRecipes(emp);
        }
        return emp;
    }

}
