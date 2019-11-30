package deviget.minesweeper.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
	
	private String 		strGameCode;
	private String		strGameStatus;
	private GameBoard 	gbGameBoard;
	private int 		iFlagAmount;
	private int 		iFlagDetectedAmount;
	private Date 		dStartedDate;
	private Date		dFinishedDate;
	
	// builder
	public Game( int iUserID, int iColAmount, int iRowAmount, int iFlagAmount ) {
		
		this.strGameCode = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date() ) + iUserID;
		this.strGameStatus = "INITIALIZED";
		this.gbGameBoard = new GameBoard( iColAmount, iRowAmount );
		this.gbGameBoard.setFlags( iFlagAmount );
		this.iFlagAmount = iFlagAmount;
		this.iFlagDetectedAmount = 0;
		this.dStartedDate = null;
		this.dFinishedDate = null;
	}	
	
	// getters and setters
	public String getGameCode() {
		return strGameCode;
	}

	public void setGameCode(String strGameCode) {
		this.strGameCode = strGameCode;
	}
	
	public String getGameStatus() {
		return strGameStatus;
	}
	
	public void setGameStatus(String strGameStatus) {
		this.strGameStatus = strGameStatus;
	}
	
	public int getFlagAmount() {
		return iFlagAmount;
	}
	
	public void setFlagAmount(int iFlagAmount) {
		this.iFlagAmount = iFlagAmount;
	}
	
	public int getFlagDetectedAmount() {
		return iFlagDetectedAmount;
	}
	
	public void setFlagDetectedAmount(int iFlagDetectedAmount) {
		this.iFlagDetectedAmount = iFlagDetectedAmount;
	}
	
	public Date getStartedDate() {
		return dStartedDate;
	}
	
	public void setStartedDate(Date dStartedDate) {
		this.dStartedDate = dStartedDate;
	}
	
	// logical functions
	public void onClic( int iCol, int iRow ) {
		
		// check if the game is available
		if( !this.getGameStatus().equals("GAMEOVER") ) {
			// if it is the first clic of the game, the initial date must be set.
			if( this.dStartedDate == null )
				this.dStartedDate = new Date();
			
			// check the status update 
			this.gbGameBoard.setRevealed( iCol, iRow );
			if( this.gbGameBoard.isFlag( iCol, iRow ) ) {
				this.setGameStatus("GAMEOVER");
				this.dFinishedDate = new Date();
			}
			else
				this.setGameStatus("PLAYING");
		}
	}

	public void onRightButton( int iCol, int iRow ) {
		
		// check if the game is available
		if( !this.getGameStatus().equals("GAMEOVER") ) {
			// check if the cell was revealed
			if( !this.gbGameBoard.isRevealed( iCol, iRow ) ) {
				if( this.gbGameBoard.isFlagged( iCol, iRow ) ) {
					this.gbGameBoard.setFlagged( iCol, iRow, false );
					this.iFlagDetectedAmount--;
					this.gbGameBoard.setQuestionMarked( iCol, iRow, true );
				}
				else {
					if( this.gbGameBoard.isQuestionMarked( iCol, iRow ) )
						this.gbGameBoard.setQuestionMarked( iCol, iRow, false );
					else {
						this.gbGameBoard.setFlagged( iCol, iRow, true );
						this.iFlagDetectedAmount++;
						if( this.iFlagAmount == this.iFlagDetectedAmount )
							if( this.gbGameBoard.checkEndGame() )
								this.setGameStatus("FINISHED");
					}
				}
			}
		}
	}
	
	public ResponseGrid getGame() {
		
		ResponseGrid rgResult = new ResponseGrid();
		rgResult.FlagAmount = this.getFlagAmount();
		rgResult.FlagDetectedAmount = this.getFlagDetectedAmount();
		rgResult.GameCode = this.getGameCode();
		rgResult.GameStatus = this.getGameStatus();
		rgResult.StartedDate = this.getStartedDate();
		rgResult.setGameBoardSize( this.gbGameBoard.getColSize(), this.gbGameBoard.getRowSize() );
		
		int iCol = 0;
		while( iCol < this.gbGameBoard.getColSize() ) {
			int iRow = 0;
			while( iRow < this.gbGameBoard.getRowSize() ) {
				
				rgResult.setCellLabel( iCol, iRow, this.gbGameBoard.getCellLabel( iCol, iRow ) );
				
				// check if the game over to reveal all mines
				if( this.getGameStatus().equals("GAMEOVER") 
						&& this.gbGameBoard.isFlag( iCol, iRow ) )
					rgResult.setCellLabel( iCol, iRow, "MINE");
				
				iRow++;
			}
			iCol++;
		}
		
		return rgResult;
	}
}
