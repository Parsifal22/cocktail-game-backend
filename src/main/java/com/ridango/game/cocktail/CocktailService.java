package com.ridango.game.cocktail;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CocktailService {
    @Value("${cocktail.api.url}")
    private String apiUrl;

    Cocktail.Drink cocktail;

    List<String> listHints;

    public Cocktail getRandomCocktail() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cocktail> response = restTemplate.getForEntity(apiUrl, Cocktail.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            this.cocktail = response.getBody().getDrinks().get(0);
            makeHintsList();
            return response.getBody();
        } else {
            // Handle error cases: API rate limit exceeded, network error, etc.
            throw new RuntimeException("Error fetching cocktail from API");
        }
    }

    private void makeHintsList() {
        listHints = new ArrayList<>();
        listHints.add("Instruction: " + this.cocktail.getStrInstructions());
        listHints.add("Glass: " + this.cocktail.getStrGlass());
        listHints.add("Category of cocktail: " + this.cocktail.getStrCategory());
        listHints.add("First Ingredient: " + this.cocktail.getStrIngredient1());
        listHints.add("Second Ingredient: " + this.cocktail.getStrIngredient2());
        listHints.add("Third Ingredient: " + this.cocktail.getStrIngredient3());
    }


    public List<String> getListHints() {
        return this.listHints;
    }

    public String getCocktailName() {
        return cocktail.getStrDrink();
    }
}
