package com.ridango;

import com.ridango.game.cocktail.CocktailService;
import com.ridango.game.player.PlayerService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CocktailGameTests {

    CocktailService cocktailService = new CocktailService();

    PlayerService playerService = new PlayerService();

    @Test
    void checkCocktailService() {
        /* We check that the getRandomCocktail method in class CocktailService takes a random cocktail via the API
        * and creates an object with all the necessary elements for our task. */

        cocktailService.getRandomCocktail();

        assertThat(cocktailService.getCocktail()).isNotEqualTo(null);

        assertThat(cocktailService.getCocktail().getListHints().size()).isEqualTo(6);

    }


}
