package jp.co.axa.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Recipes;
//use default Jpa method
@Repository
public interface RecipesRepository extends JpaRepository<Recipes,Long> {
}
