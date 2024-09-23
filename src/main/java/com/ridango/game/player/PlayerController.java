package com.ridango.game.player;

import com.ridango.game.cocktail.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    CocktailService cocktailService;

    @GetMapping("/random-new-cocktail")
    public ResponseEntity<Player> getNewCocktail() {
        playerService.updateCocktail(cocktailService.getRandomCocktail());
        return new ResponseEntity<Player>(playerService.getPlayer(), HttpStatus.OK);

    }

    @PostMapping("/correct-answer")
    public ResponseEntity<Player> correctAnswerHandler(@RequestBody String answer) {
        playerService.correctAnswerHandler();
        playerService.updateCocktail(cocktailService.getRandomCocktail());
        return new ResponseEntity<Player>(playerService.getPlayer(), HttpStatus.OK);

    }

    @PostMapping("/incorrect-answer")
    public ResponseEntity<Player> incorrectAnswerHandler(@RequestBody String answer) {
        playerService.incorrectAnswerHandler(cocktailService.getCocktailName());
        return new ResponseEntity<Player>(playerService.getPlayer(), HttpStatus.OK);

    }

    @PostMapping("/game-over")
    public ResponseEntity<Player> gameOverHandler(@RequestBody String answer) {
        playerService.gamOverHandler();
        playerService.updateCocktail(cocktailService.getRandomCocktail());
        return new ResponseEntity<Player>(playerService.getPlayer(), HttpStatus.OK);

    }


}
