package deviget.minesweeper.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
	
	private String 		gameCode;
	private String		gameStatus;
	private GameBoard 	gameBoard;
	private int 		flagAmount;
	private int 		flagDetectedAmount;
	private Date 		startedDate;
	private Date		finishedDate;
	
	// builder
	public Game( int userID, int colAmount, int rowAmount, int flagAmount ) {
		
		this.gameCode = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date() ) + userID;
		this.gameStatus = "INITIALIZED";
		this.gameBoard = new GameBoard( colAmount, rowAmount );
		this.gameBoard.setFlags( flagAmount );
		this.flagAmount = flagAmount;
		this.flagDetectedAmount = 0;
		this.startedDate = null;
		this.finishedDate = null;
	}	
	
	// getters and setters
	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
	public String getGameStatus() {
		return gameStatus;
	}
	
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public int getFlagAmount() {
		return flagAmount;
	}
	
	public void setFlagAmount(int flagAmount) {
		this.flagAmount = flagAmount;
	}
	
	public int getFlagDetectedAmount() {
		return flagDetectedAmount;
	}
	
	public void setFlagDetectedAmount(int flagDetectedAmount) {
		this.flagDetectedAmount = flagDetectedAmount;
	}
	
	public Date getStartedDate() {
		return startedDate;
	}
	
	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
	
	// logical functions
	public void reveal( int col, int row ) {
		
		// check if the game is available
		if( !this.getGameStatus().equals("GAMEOVER") ) {
			// if it is the first clic of the game, the initial date must be set.
			if( this.startedDate == null )
				this.startedDate = new Date();
			
			// check the status update 
			this.gameBoard.setRevealed( col, row );
			if( this.gameBoard.isFlag( col, row ) ) {
				this.setGameStatus("GAMEOVER");
				this.finishedDate = new Date();
			}
			else
				this.setGameStatus("PLAYING");
		}
	}

	public void flag( int col, int row ) {
		
		// check if the game is available
		if( !this.getGameStatus().equals("GAMEOVER") ) {
			// check if the cell was revealed
			if( !this.gameBoard.isRevealed( col, row ) ) {
				if( this.gameBoard.isFlagged( col, row ) ) {
					this.gameBoard.setFlagged( col, row, false );
					this.flagDetectedAmount--;
					this.gameBoard.setQuestionMarked( col, row, true );
				}
				else {
					if( this.gameBoard.isQuestionMarked( col, row ) )
						this.gameBoard.setQuestionMarked( col, row, false );
					else {
						this.gameBoard.setFlagged( col, row, true );
						this.flagDetectedAmount++;
						if( this.flagAmount == this.flagDetectedAmount )
							if( this.gameBoard.checkEndGame() )
								this.setGameStatus("FINISHED");
					}
				}
			}
		}
	}
	
	public ResponseGrid getGame() {
		
		ResponseGrid rgResult = new ResponseGrid();
		rgResult.flagAmount = this.getFlagAmount();
		rgResult.flagDetectedAmount = this.getFlagDetectedAmount();
		rgResult.gameCode = this.getGameCode();
		rgResult.gameStatus = this.getGameStatus();
		rgResult.startedDate = this.getStartedDate();
		rgResult.setGameBoardSize( this.gameBoard.getColSize(), this.gameBoard.getRowSize() );
		
		int col = 0;
		while( col < this.gameBoard.getColSize() ) {
			int row = 0;
			while( row < this.gameBoard.getRowSize() ) {
				
				rgResult.setCellLabel( col, row, this.gameBoard.getCellLabel( col, row ) );
				
				// check if the game over to reveal all mines
				if( this.getGameStatus().equals("GAMEOVER") 
						&& this.gameBoard.isFlag( col, row ) )
					rgResult.setCellLabel( col, row, "MINE");
				
				row++;
			}
			col++;
		}
		
		return rgResult;
	}
}
