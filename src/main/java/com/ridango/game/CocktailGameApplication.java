package com.ridango.game;

import com.ridango.game.cocktail.CocktailInfo;
import com.ridango.game.cocktail.CocktailService;
import com.ridango.game.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CocktailGameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CocktailGameApplication.class, args);
	}

	@Autowired
	CocktailService cocktailService;

	@Autowired
	PlayerService playerService;

	@Override public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		CocktailInfo cocktailInfo;

		// Infinite loop to run the game indefinitely
		while (true){

			// Get a random cocktail from the API https://www.thecocktaildb.com/api.php and set the player object to its initial position
			playerService.updateCocktail(cocktailService.getRandomCocktail().getDrinks().get(0));
			cocktailInfo = new CocktailInfo(cocktailService.getCocktailName(), cocktailService.getListHints());


			while (playerService.getPlayer().getCurrentRound() < 50) {

				System.out.println("Attempts: " + playerService.getPlayer().getAttempts());
				System.out.println("Round: " + playerService.getPlayer().getCurrentRound());

				// Print the hints for the player. As attempts decrease, the number of hints displayed increases
				for (int i = 0; i < 6 - playerService.getPlayer().getAttempts(); i++) {
					System.out.println(cocktailInfo.getHints().get(i));
				}

				System.out.println(cocktailInfo.getCocktailName());

				System.out.println(playerService.getRevealCocktail());

				String guess = scanner.nextLine();

				if (guess.equalsIgnoreCase(cocktailInfo.getCocktailName())) {
					playerService.correctAnswerHandler();
					playerService.updateCocktail(cocktailService.getRandomCocktail().getDrinks().get(0));
					System.out.println("Correct!");
					System.out.println("Your score: " + playerService.getPlayer().getScore());
					cocktailInfo = new CocktailInfo(cocktailService.getCocktailName(), cocktailService.getListHints());
				}
				else {
					if (playerService.getPlayer().getAttempts() > 0) {
						System.out.println("Incorrect!");
						playerService.incorrectAnswerHandler(cocktailService.getCocktailName());
					}
					else {
						System.out.println("Game Over!");
						System.out.println("Right answer was: " + cocktailInfo.getCocktailName());
						playerService.gamOverHandler();
						playerService.updateCocktail(cocktailService.getRandomCocktail().getDrinks().get(0));
						cocktailInfo = new CocktailInfo(cocktailService.getCocktailName(), cocktailService.getListHints());
					}
				}

			}

			System.out.println("Game End");
			System.out.println("Your score: " + playerService.getPlayer().getScore());
			System.out.println("Recod: " + playerService.getPlayer().getHighestScore());
			playerService.gamOverHandler();

		}
	}
}
