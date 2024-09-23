package com.ridango.game.cocktail;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {
    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public static class Drink {
        private String strDrink;
        private String strDrinkThumb;
        private String strInstructions;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;

        private List<String> listHints;

        private String strCategory;
        private String strGlass;

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public List<String> getListHints() {
            return this.listHints;
        }
        public void initListHints() {
            this.listHints = new ArrayList<>();
        }
        public void addListHints(String value) {
            this.listHints.add(value);
        }

    }
}
