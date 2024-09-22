package com.ridango.game.cocktail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CocktailController {

    @Autowired
    private CocktailService cocktailService;

    @GetMapping("/cocktail-info")
    public ResponseEntity<CocktailInfo> getCocktailInfo() {
        return new ResponseEntity<>(new CocktailInfo(cocktailService.getCocktailName(), cocktailService.getListHints()), HttpStatus.OK);
    }
}
