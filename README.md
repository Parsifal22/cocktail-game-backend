# Game "Guess the cocktail"

## To build a project
* Java 1.17 or newer
* Gradle 7.5 or newer

## About default setup
* `gradle assemble` to initialize a project


## Assignment
  * Build a simple game of guess the cocktail using public API https://www.thecocktaildb.com/api.php

  * Game rules:
    * Random cocktail with instructions (strInstructions) is shown to the player together with number of letter in the name of the cocktail (e.g. "Mojito" -> "_ _ _ _ _ _")
    * Player must guess the name of the cocktail
    * Player can skip the round if he doesn't know the answer to get more hints about the cocktail
    * Player has 5 attempts to guess the name of the cocktail
    * If player answers correctly the game continues with a new random cocktail and score is increased by number of attempts left
    * If player answers wrongly or skips round the game shows:
      * Name of the cocktail with some new random letters revealed (e.g. "_ _ _ _ _ _" -> " _ _ j _ _ _" -> " _ _ j _ _ o" -> "M _ j _ _ o" -> "M _ ji _ o" -> "M _ jito" -> "Mojito") (For longer cocktails more letters can be revealed than one)
      * Reveal additional info about the cocktail (e.g. category, glass, ingredients, picture)
      * Number of attempts left
    * If player fails to guess the cocktail after 5 attempts the game ends and high score is updated
    * In one game same cocktail shouldn't appear twice

  * Resources:
    * Instructions on how to use the API are written here: https://www.thecocktaildb.com/api.php
