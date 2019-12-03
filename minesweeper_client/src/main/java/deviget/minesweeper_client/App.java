package deviget.minesweeper_client;

import java.util.Date;

import deviget.minesweeper_client.model.MinesweeperAPIClient;
import deviget.minesweeper_client.model.ResponseGrid;

public class App 
{
	
    public static void main( String[] args ) throws Exception {
    	String gameCode, gameStatus;
    	MinesweeperAPIClient minesweeperClient;
    	
        App mainApp = new App();
        minesweeperClient = new MinesweeperAPIClient("http://minesweeperapi.sa-east-1.elasticbeanstalk.com/minesweeper/game");
        
        // define a new game
        gameCode = minesweeperClient.newGame(1500, 5, 5, 2);
        System.out.println( "the new game code is: " + gameCode);
        mainApp.printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        
        // reveal several cells
        gameStatus = minesweeperClient.revealCell( gameCode, 1, 3 );
        System.out.println( "Reveal on 1-3:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        gameStatus = minesweeperClient.revealCell( gameCode, 3, 4 );
        System.out.println( "Reveal on 3-4:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        gameStatus = minesweeperClient.revealCell( gameCode, 2, 3 );
        System.out.println( "Reveal on 2-4:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        gameStatus = minesweeperClient.revealCell( gameCode, 0, 2 );
        System.out.println( "Reveal on 0-2:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        gameStatus = minesweeperClient.revealCell( gameCode, 3, 3 );
        System.out.println( "Reveal on 2-3:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
        
        // flag a cell
        minesweeperClient.flagCell( gameCode, 0, 0 );
        System.out.println( "Flag on 0-0:" );
        printGameBoard( minesweeperClient.getGameBoard( gameCode ) );
    }

	private static void printGameBoard( ResponseGrid responseGrid ) {
  
    	System.out.println( "Game Code/Satus: " + responseGrid.gameCode + "/" + responseGrid.gameStatus );
        System.out.println( "Flag Detected: " + responseGrid.flagDetectedAmount + "/" + responseGrid.flagAmount );
        if( responseGrid.startedDate != null )
        	System.out.println( "Time Tracking: " + getTimeTracking(responseGrid.startedDate) );
        else
        	System.out.println( "Time Tracking: " + 0 );

        if( responseGrid.responseGridNodes.size() > 0 ) {
        	String boardCells = "";
        	for( int row = 0; row < responseGrid.responseGridNodes.get(0).cellsLabel.size(); row++ ) {
        		String rowCells = "";
        		for( int col = 0; col < responseGrid.responseGridNodes.size(); col++ ) {
        			if( responseGrid.responseGridNodes.get(col).cellsLabel.get(row).isEmpty() )
        				rowCells += "[     ]" + "\t";
        			else
        				rowCells += responseGrid.responseGridNodes.get(col).cellsLabel.get(row) + "\t";
        		}
        		boardCells += rowCells + "\n";
        	}
        	System.out.println( boardCells );
        }
        else
        	System.out.println( "No board defined." );
        
        System.out.println();
    }
    
    private static String getTimeTracking( Date startedDate ) {
    	Date actualDate = new Date();
 
        //in milliseconds
		long diff = actualDate.getTime() - startedDate.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
        
		return diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.";
    }
   
}
