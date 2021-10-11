package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.entities.Recipes;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.repositories.RecipesRepository;

@Service
@Transactional  //use transactional to ensure ACID
public class RecipesServiceImpl implements RecipesService{

    @Autowired
    private RecipesRepository recipesRepository;
    
    public void setRecipesRepository(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }
    
    @Override
    public List<Recipes> retrieveRecipes() {
        List<Recipes> Recipes = recipesRepository.findAll();
        return Recipes;
    }
    
    @Override
    public Recipes getRecipes(Long recipesId) {
        Optional<Recipes> optEmp = recipesRepository.findById(recipesId);
        return optEmp.get();
    }
    
    @Override
    public Recipes createRecipes(Recipes recipes){
    	Recipes e = recipesRepository.save(recipes);
        return e;
    }
    
    @Override
    public void deleteRecipes(Long recipesId){
    	recipesRepository.deleteById(recipesId);
    }
    
    @Override
    public Recipes updateRecipes(Recipes recipes) {
        return recipesRepository.save(recipes);
    }
}