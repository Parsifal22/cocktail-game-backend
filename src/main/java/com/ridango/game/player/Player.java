package com.ridango.game.player;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Player {
    private Integer score;
    private Integer attempts;

    private List<String> hiddenCocktailName;
    private Integer currentRound;
    private Integer highestScore;

    public Player() {
        this.score = 0;
        this.attempts = 5;
        this.currentRound = 1;
        this.highestScore = 0;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getAttempts() {
        return this.attempts;
    }

    public Integer getHighestScore() {
        return this.highestScore;
    }

    public void setHighestScore(Integer newHighestScore) {
        this.highestScore = newHighestScore;
    }

    public List<String> getHiddenCocktailName() {
        return this.hiddenCocktailName;
    }

    public void setHiddenCocktailName(List<String> newCocktailName){
        this.hiddenCocktailName = newCocktailName;
    }

    public Integer getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Integer currentRound) {
        this.currentRound = currentRound;
    }
}
