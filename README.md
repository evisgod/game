# Game Of Three

## Overview 

Game of Three is a game with two independent units – the players communicating with each other using an API. 

When a player starts, it incepts a random (whole) number and sends it to the second player as an approach of starting the game. The receiving player can now always choose between adding one of​ `{­1, 0, 1}` ​to get to a number that is divisible by​ `3`. Divide it by three. The resulting whole number is then sent back to the original sender. The same rules are applied until one player reaches the number​ `1` (after the division).


## Implementation 

### Technology Used:
- Spring Boot
- Java8
- Maven
- Spring Events

For the application’s purposes, we need three tables. 
*DB Structure:* 

![alt tag](https://github.com/evisgod/game/blob/master/DB_Structure.jpeg)

The game table stores game details, the move table contains a list of all player moves for each particular game, and the player table stores all the player-specific data.

REST API's created corresponding to each and every function of the game w.r.t. domain like GameController, MoveController and PlayerController.

I tried to keep it simple and stupid covering almost every features of the game.

Once a game server is started and both players joined a game using their own instance, first player will trigger the move by sending a random number to second player over REST API and same things follows w.r.t. to Move event until number reaches `1`.

## Build and Run

### Configuration

- To configure the game server and players go to resources/application.properties files of the respective project.
- We can build the game-of-three server and player client using maven build.

       mvn clean install


## Run

- Start the game-of-three application first. It will be the server on default port `9090`.


       java -jar game-of-three-0.0.1-SNAPSHOT.jar

- Start the first player and default port will be `9091`.

     
      java -jar player-0.0.1-SNAPSHOT.jar

- Start the second player and default port will be `9092`. 
      
     
      java -jar player-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/player2.properties

## Documentation

I have used swagger2 for API documentation and it will be accessible on: 

#### Game Server
    http://localhost:9090/swagger-ui.html

#### Player 1: 
    http://localhost:9091/swagger-ui.html

#### Player 2:
    http://localhost:9092/swagger-ui.html


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
