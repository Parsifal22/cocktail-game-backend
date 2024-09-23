package com.ridango.game.player;

import com.ridango.game.cocktail.Cocktail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlayerService {

    @Autowired
    private Player player;

    boolean[] revealedLetters;
    List<String> revealCocktail;

    Set<String> existingCocktails = new HashSet<>();

    public Player getPlayer() {
        return this.player;
    }

    // Method to update the player's cocktail with a new random drink
    public void updateCocktail(Cocktail.Drink newCocktail) {
        Cocktail.Drink cocktail;
        do {
            cocktail = newCocktail;
        } while (existingCocktails.contains(cocktail.getStrDrink())); // Ensure the cocktail name is not already in the set

        existingCocktails.add(cocktail.getStrDrink()); // Add the cocktail name to the set

        setRevealCocktail(cocktail.getStrDrink().length());

        resetAttempts();
    }

    // Method to handle an incorrect answer scenario
    public void incorrectAnswerHandler(String cocktailName) {
        getRevealedCocktailName(cocktailName);
        decrementAttempts();
    }

    // Method to handle a correct answer scenario
    public void correctAnswerHandler() {
        increaseScore();
        resetAttempts();
        incrementCurrentRound();
    }


    // Method to handle the game over scenario
    public void gamOverHandler() {
        updateHighestScore();
        resetAttempts();
        resetScore();
        resetCurrentRound();

    }

    // Method to get the revealed cocktail name
    public String getRevealCocktail(){
        StringBuilder cocktail = new StringBuilder();

        for(int i = 0; i < this.revealCocktail.size(); i++){
            cocktail.append(revealCocktail.get(i));
        }

        return cocktail.toString();
    }

    // Method to decrement the player's attempts
    private void decrementAttempts() {
        Integer result = player.getAttempts() - 1;
        System.out.println(result);
        player.setAttempts(result);
    }

    private void resetAttempts() {
        player.setAttempts(5);
    }

    private void resetScore() {player.setScore(0);}

    // Method to get the revealed cocktail name
    public void getRevealedCocktailName(String cocktailName) {

        Random random = new Random();
        int lettersToReveal = cocktailName.length() / 5;

        int count = 0;
        while(count < lettersToReveal) {
            int randomNumber = random.nextInt(cocktailName.length());
            if (!(this.revealedLetters[randomNumber])) {
                this.revealCocktail.set(randomNumber, String.valueOf(cocktailName.charAt(randomNumber)));
                this.revealedLetters[randomNumber] = true;
                count++;
            }
        }

        player.setHiddenCocktailName(this.revealCocktail);

    }

    // Method to update the highest score
    private void updateHighestScore() {
        if (player.getScore() > player.getHighestScore()) {
            player.setHighestScore(player.getScore());
            System.out.println("New highest score: " + player.getScore());
        }
    }

    private void incrementCurrentRound() {
        player.setCurrentRound(player.getCurrentRound() + 1);
    }

    private void resetCurrentRound() {

        player.setCurrentRound(1);
    }

    private void increaseScore() {
        Integer newScore = player.getScore() + player.getAttempts();
        player.setScore(newScore);
    }

    // Method to set the revealed cocktail name
    private void setRevealCocktail(int newCoctailLength){

        revealCocktail = new ArrayList<String>();

        for (int i = 0; i < newCoctailLength; i++) {
            this.revealCocktail.add("_");
        }

        player.setHiddenCocktailName(revealCocktail);

        this.revealedLetters = new boolean[newCoctailLength];
    }
}

