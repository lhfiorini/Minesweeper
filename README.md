# Version 1.00
The first version of the structural code of the API was development (just a "hello word") in order to have a framework to work in and check that the 
In the case of the "server" it was built in java as programming language, with maven (it is better for implementation of dependencies and the compilation of the project) and with springboot as the application server.
In the case of the "client" it was built in java and maven as well. 

# Version 1.01
Package “deviget.minesweeper.model” was created to include all the class of data model for the API.
There is a “GameList” class to include all games created in the server. They are access through a unique GameCode generated when the client requests a new game.
The data model consists in the Game class, which contains several variable to manage the status of the game and a GameBoard which contains the cells and their status and values. 
There are specifics classes for the request and the response.
The client was update with 2 methods to generate a new game and to get the status of a game.
The following function of the game are include:
•	NewGame
•	OnClic
•	GetGame
