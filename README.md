## Hangman game API ##

This repo contains a API version of the hangman game. The continuous deployment is done using github actions, 
runnning project build, test, sonarqube amalisys and heroku deployment.

### Link to access swagger locally ###

[swagger UI](http://127.0.1.1:8081/hangman/swagger-ui/)

### Project sonarcloud analisys ###

[sonarcloud](https://sonarcloud.io/dashboard?id=lcssimonini_hangman-game)

### Endpoint to access project ###

https://simo-hangman-game.herokuapp.com/

### Run project ###

Before running the .jar, execute:

`./gradlew clean build`

The project will be built, and the binary jar file will be placed on build/libs folder

To build the docker image and run the image as a container, run the following command:

`./run.sh`

## Play the game ##

A POST request to the `/hangman/game` creates a new game that is sored in memory, the game response looks like this:

```
{
    "gameId": "3aa28235-b98b-447e-a8c3-1a4d1d482eba",
    "gameStatus": "_____",
    "wrongGuessesCount": 0,
    "hits": 0,
    "gameResult": "ONGOING"
}
```
- gameId - used to find the game when playing;
- gameStatus - a simple representation of the letters that are already correct;
- wrongGuessesCount - count of wrong guesses;
- hits - number of correctly guessed letters;
- gameResult - ONGOING, WON, LOST. A game does not receive updates unless is on the ONGOING status.


After creating a new game, you can guess the letters sending a POST request to the `/hangman/game/{gameId}` endpoint with the payload:

```
{"letter": "n"}
```

The game payload gets updated as the player tries more letters:

```
{
  "gameId": "3aa28235-b98b-447e-a8c3-1a4d1d482eba",
  "gameStatus": "CLO_D",
  "wrongGuessesCount": 4,
  "hits": 4,
  "gameResult": "ONGOING"
}
```
 When you win or loose, the game us not updated anymore:


```
{
  "gameId": "3aa28235-b98b-447e-a8c3-1a4d1d482eba",
  "gameStatus": "CLOUD",
  "wrongGuessesCount": 4,
  "hits": 5,
  "gameResult": "WON"
}
```

