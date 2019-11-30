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
	
	// builder
	public Game( int iUserID, int iColAmount, int iRowAmount, int iFlagAmount ) {
		
		this.strGameCode = new SimpleDateFormat("yyyyMMdd_HHmmss_").format(new Date() ) + iUserID;
		this.strGameStatus = "INITIALIZED";
		this.gbGameBoard = new GameBoard( iColAmount, iRowAmount );
		this.gbGameBoard.setFlags( iFlagAmount );
		this.iFlagAmount = iFlagAmount;
		this.iFlagDetectedAmount = 0;
		this.dStartedDate = null;
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
	public String onClic( int iCol, int iRow ) {
		
		// if it is the first clic of the game, the initial date must be set.
		if( this.dStartedDate == null )
			this.dStartedDate = new Date();
		
		if( this.gbGameBoard.isFlag( iCol, iRow ) )
			this.setGameStatus("GAMEOVER");
		else {
			this.gbGameBoard.setRevealed( iCol, iRow );
			this.setGameStatus("PLAYING");
		}
		
		return this.getGameStatus();
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
				iRow++;
			}
			iCol++;
		}
		
		return rgResult;
	}
}
