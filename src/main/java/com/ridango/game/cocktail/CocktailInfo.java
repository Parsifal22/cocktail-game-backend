package com.ridango.game.cocktail;

import java.util.List;

public class CocktailInfo {
    private String cocktailName;
    private List<String> hints;

    private List<String> hiddenCocktailName;

    public CocktailInfo(String cocktailName, List<String> hints) {
        this.cocktailName = cocktailName;
        this.hints = hints;
    }

    public String getCocktailName() {
        return this.cocktailName;
    }

    public List<String> getHints() {
        return hints;
    }

}
