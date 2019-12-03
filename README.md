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

# Version 3.01
The client was optimized in order to independent the functions to interact with server in one class separated from the function to print the result.
The server was published in AWS under the following url:
http://minesweeperapi.sa-east-1.elasticbeanstalk.com/minesweeper



Decisions taken and important notes
As it was required, the design and implementation of a RESTful API for the minesweeper game was done. It was structured with a hashtable in order to organize all the games by gameCode and accessible in any moment. Each game has same variables to manage the status and a structure to represent the board of the game. The builder of a game requires the number of columns, rows, mines and a userID. The userID and the date of the moment when the builder is executed is used to create the gameCode which will be the ID of the game all the time. The mines are located randomly inside the board defined.

The API client was done in Java and the functions to interact with the server are group under the MinesweeperAPIClient class to be included in any other project.

The server was published under the following URL in AWS and from now on we will call it “urlAWS” with the help of Elastic Beanstalk that facilitates the creation and administration of application environments:
http://minesweeperapi.sa-east-1.elasticbeanstalk.com/minesweeper

The main functionalities are:
1.	Build a new game:
a.	url:		“urlAWS/game”
b.	RESTful type: 	POST
c.	parameters: 	usedID, cols number, rows number and mines number
d.	return: 		gameCode

2.	Get the game status:
a.	url:		“urlAWS/game/{gameCode}”
b.	RESTful type:	GET
c.	parameters: 	gameCode in the url.
d.	return: 		full gameStatus

3.	Reveal a cell of the game:
a.	url:		“urlAWS/game/{gameCode}/reveal”
b.	RESTful type:	PUT
c.	parameters: 	gameCode in the url + col number, row number
d.	return: 		gameStatus.

4.	Flag a cell of the game:
a.	url:		“urlAWS/game/{gameCode}/flag”
b.	RESTful type:	PUT
c.	parameters: 	gameCode in the url + col number, row number
d.	return: 		gameStatus

The full gameStatus contemplates the gameCode, the gameStatus, the date/time of the first move, the number of mines detected, the number of mines to be detected and the actual board situation. The board situation is the set of all the cells situation represent by “” when the cell is initialized, “EMPTY” when the cell is revealed and empty, “MINE” when the cell is revealed and contains a mine, “FLAGGED” when the cell was flagged and “QUESTIONMARKED” when the cell was question marked.
The gamStatus is a text the represent if the game was built but not started yet ("INITIALIZED"), if the player can continue playing (“PLAYING”) or not (“GAMEOVER”) and if the game is finished successfully (“FINISHED”).

Below are the important notes and decisions made for each point requested:
* When a cell with no adjacent mines is revealed, all adjacent squares will be revealed (and repeat)
It was solved under the “urlAWS/game/{gameCode}/reveal” and requires a column and row number in the body parameters to identify the cell to be revealed.
* Ability to 'flag' a cell with a question mark or red flag
It was solved under the “urlAWS/game/{gameCode}/flag” and requires a column and row number in the body parameters to identify the cell to be flagged. When you call this functions on a cell not flagged it will be flagged, if the cell was flagged it will be question marked and if the cell was question marked it will be unflagged. It is a sequential change of “flag”.
* Detect when game is over
Each time a cell is reveal or flagged, the game calculate de actual status of the game and it is return as result of the function. If you reveal a cell with a mine inside, the game is over.
* Persistence
The persistence was not done due to lack of time. I would have done it with a SQL DB and the help of hibernate to be able to abstract from the connection with de DB. I would have started with the DB model as a master table with all the gameCode and its attribute to represent them, another table with the gameboard status saving each cell not empty in a record in order not to be limited by the fields of the table. Both tables should be connected with a FK of the gameCode and also a master table of userID and attributes may be necessary. 
The following dependencies are required in the pom file of the server to manage the persistence as I mention:
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-c3p0</artifactId>
			<version>5.4.2.Final</version>
		</dependency>
		<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
			<version>7.1.4.jre8-preview</version>
			<scope>runtime</scope>
		</dependency>
* Time tracking
When a the first cell is reveal or flagged the date/time is saved so that the time tracking of the game can be calculated at any moment.
* Ability to start a new game and preserve/resume the old ones
As a hashtable was defined to manage all the game by their gameCode, a new game can be started at any moment and an old game can be resumed when it is desired.
* Ability to select the game parameters: number of rows, columns, and mines
Those parameters are part of the builder parameters.
* Ability to support multiple users/accounts
As the userID is part of the gameCode, multiple users/accounts is supported.


