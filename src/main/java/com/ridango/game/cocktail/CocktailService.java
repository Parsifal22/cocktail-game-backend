package com.ridango.game.cocktail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CocktailService {

    private String apiUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

    private Set<String> existingCocktails = new HashSet<>();

    Cocktail.Drink cocktail;

    public Cocktail.Drink getRandomCocktail() {
        do {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Cocktail> response = restTemplate.getForEntity(apiUrl, Cocktail.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                this.cocktail = Objects.requireNonNull(response.getBody()).getDrinks().get(0);
            } else {
                // Handle error cases: API rate limit exceeded, network error, etc.
                throw new RuntimeException("Error fetching cocktail from API");
            }
        } while (existingCocktails.contains(cocktail.getStrDrink())); // Ensure the cocktail name is not already in the set

        this.existingCocktails.add(cocktail.getStrDrink()); // Add the cocktail name to the set
        makeHintsList();
        return this.cocktail;
    }

    private void makeHintsList() {
        cocktail.initListHints();
        cocktail.addListHints("Instruction: " + this.cocktail.getStrInstructions());
        cocktail.addListHints("Glass: " + this.cocktail.getStrGlass());
        cocktail.addListHints("Category of cocktail: " + this.cocktail.getStrCategory());
        cocktail.addListHints("First Ingredient: " + this.cocktail.getStrIngredient1());
        cocktail.addListHints("Second Ingredient: " + this.cocktail.getStrIngredient2());
        cocktail.addListHints("Third Ingredient: " + this.cocktail.getStrIngredient3());
    }

    public Cocktail.Drink getCocktail() {
        return this.cocktail;
    }

    public String getCocktailName() {
        return cocktail.getStrDrink();
    }
}
