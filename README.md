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

# Version 2.00
New method in the controller to manage the onClic and onRightButton of the client.
First game played through Postman in a board of 5x5 with 2 mines. Both “GAMEOVER” and “FINISHED” status were tested.

# Version 3.00
New version of the client to play with the API minesweeper. As an example, it is set a play of 2 mines in a board of 5 col and 5 rows, then 5 movements and 1 flag are realized. As the mines are located randomly sometimes the flag movement it is not the right one. Below is an example of the result when the cell is not revealed before flagging:
Flag on 0-0:
Game Code/Satus: 20191202_184153_1500/PLAYING
Flag Detected: 1/2
Time Tracking: 0 days, 0 hours, 0 minutes, 0 seconds.
FLAGGED	2	EMPTY	EMPTY	EMPTY	
[     ]	2	EMPTY	EMPTY	EMPTY	
1		1	EMPTY	EMPTY	EMPTY	
EMPTY		EMPTY	EMPTY	EMPTY	EMPTY	
EMPTY		EMPTY	EMPTY	EMPTY	EMPTY
