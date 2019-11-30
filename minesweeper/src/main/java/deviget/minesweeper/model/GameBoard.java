package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class GameBoard {
	
	private ArrayList<GameBoardRow> alGameBoardRow;


	// builder
	public GameBoard( int iColAmount, int iRowAmount ) {
		
		this.alGameBoardRow = new ArrayList<GameBoardRow>();
		for( int iApun = 0; iApun < iColAmount; iApun++ ) {
			GameBoardRow auxGameBoardRow = new GameBoardRow(iRowAmount);
			this.alGameBoardRow.add( auxGameBoardRow );
		}
	}
	
	// getters and setters
	public int getColSize() {
		return this.alGameBoardRow.size();
	}

	public int getRowSize() {
		return this.alGameBoardRow.get(0).alRowCell.size();
	}
	
	// logical functions
	public boolean isFlag(int iCol, int iRow) {
		
		return this.alGameBoardRow.get(iCol).alRowCell.get(iRow).isFlag();
	}

	public void setRevealed(int iCol, int iRow) {
		
		this.alGameBoardRow.get(iCol).alRowCell.get(iRow).setRevealed(true);
		if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow).getAroundFlagAmount() == 0 )
			this.revealNeighbors( iCol, iRow );
	}

	private void revealNeighbors(int iCol, int iRow) {
		
		// one cell can have up to 8 neighbors, all of them must be check if they can be revealed.
		
		// check if there is a row above
		if( iRow > 0 ) {
			// check if the neighbor above-right exists, then if it is not revealed lets check if it must be reveal
			if( iCol > 0 ) {
				if( !this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow-1).isRevealed() ) {
					this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow-1).setRevealed(true);
					if( this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow-1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( iCol-1, iRow-1 );
				}
			}
			
			// check if the neighbor above exists, then if it is not revealed lets check if it must be reveal
			if( !this.alGameBoardRow.get(iCol).alRowCell.get(iRow-1).isRevealed() ) {
				this.alGameBoardRow.get(iCol).alRowCell.get(iRow-1).setRevealed(true);
				if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow-1).getAroundFlagAmount() == 0 )
					this.revealNeighbors( iCol, iRow-1 );
			}
			
			// check if the neighbor above-left exists, then if it is not revealed lets check if it must be reveal
			if( iCol < this.alGameBoardRow.size() - 1 ) {
				if( !this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow-1).isRevealed() ) {
					this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow-1).setRevealed(true);
					if( this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow-1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( iCol+1, iRow-1 );
				}
			}
		}
		
		// check if the neighbor right exists, then if it is not revealed lets check if it must be reveal
		if( iCol > 0 ) {
			if( !this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow).isRevealed() ) {
				this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow).setRevealed(true);
				if( this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow).getAroundFlagAmount() == 0 )
					this.revealNeighbors( iCol-1, iRow );
			}
		}
		
		// check if the neighbor left exists, then if it is not revealed lets check if it must be reveal
		if( iCol < this.alGameBoardRow.size() - 1 ) {
			if( !this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow).isRevealed() ) {
				this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow).setRevealed(true);
				if( this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow).getAroundFlagAmount() == 0 )
					this.revealNeighbors( iCol+1, iRow );
			}
		}
		
		// check if there is a row below
		if( iRow < this.alGameBoardRow.get(iCol).alRowCell.size() - 1 ) {
			// check if the neighbor below-right exists, then if it is not revealed lets check if it must be reveal
			if( iCol > 0 ) {
				if( !this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow+1).isRevealed() ) {
					this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow+1).setRevealed(true);
					if( this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow+1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( iCol-1, iRow+1 );
				}
			}
			
			// check if the neighbor below exists, then if it is not revealed lets check if it must be reveal
			if( !this.alGameBoardRow.get(iCol).alRowCell.get(iRow+1).isRevealed() ) {
				this.alGameBoardRow.get(iCol).alRowCell.get(iRow+1).setRevealed(true);
				if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow+1).getAroundFlagAmount() == 0 )
					this.revealNeighbors( iCol, iRow+1 );
			}
			
			// check if the neighbor below-left exists, then if it is not revealed lets check if it must be reveal
			if( iCol < this.alGameBoardRow.size() - 1 ) {
				if( !this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow+1).isRevealed() ) {
					this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow+1).setRevealed(true);
					if( this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow+1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( iCol+1, iRow+1 );
				}
			}
		}
	}

	public void setFlags(int iFlagAmount) {
		
		int iCol, iRow, iApun;
		
		// locate random the flags
		iApun = 0;
		while( iApun< iFlagAmount ) {
			
			// calculate a random row and column where to set a flag
			iCol = (int) (Math.random() * this.alGameBoardRow.size());
			iRow = (int) (Math.random() * this.alGameBoardRow.get(iCol).alRowCell.size());
			
			// check if this cell is not a flag, to make it flag and update the neighbors
			if( !this.alGameBoardRow.get(iCol).alRowCell.get(iRow).isFlag() ) {
				this.alGameBoardRow.get(iCol).alRowCell.get(iRow).setFlag(true);
				this.updateNeighborsFlag(iCol, iRow);
				iApun++;
			}
		}
	}

	private void updateNeighborsFlag(int iCol, int iRow) {
		
		// one cell can have up to 8 neighbors, all of them must be check if they can be revealed.
		
		// check if there is a row above
		if( iRow > 0 ) {
			// check if the neighbor above-right exists and updated
			if( iCol > 0 )
				this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow-1).addAroundFlagAmount();
			
			// check if the neighbor above exists and updated
			this.alGameBoardRow.get(iCol).alRowCell.get(iRow-1).addAroundFlagAmount();
			
			// check if the neighbor above-left exists and updated
			if( iCol < this.alGameBoardRow.size() - 1 )
				this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow-1).addAroundFlagAmount();
		}
		
		// check if the neighbor right exists and updated
		if( iCol > 0 )
			this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow).addAroundFlagAmount();
		
		// check if the neighbor left exists and updated
		if( iCol < this.alGameBoardRow.size() - 1 )
			this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow).addAroundFlagAmount();
		
		// check if there is a row below
		if( iRow < this.alGameBoardRow.get(iCol).alRowCell.size() - 1 ) {
			// check if the neighbor below-right exists and updated
			if( iCol > 0 )
				this.alGameBoardRow.get(iCol-1).alRowCell.get(iRow+1).addAroundFlagAmount();
			
			// check if the neighbor below exists and updated
			this.alGameBoardRow.get(iCol).alRowCell.get(iRow+1).addAroundFlagAmount();
			
			// check if the neighbor below-left exists and updated
			if( iCol < this.alGameBoardRow.size() - 1 )
				this.alGameBoardRow.get(iCol+1).alRowCell.get(iRow+1).addAroundFlagAmount();
		}
	}

	public String getCellLabel(int iCol, int iRow) {
		
		String strResult = "";
		
		// checking if the cell was revealed
		if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow).isRevealed() ) {
			if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow).getAroundFlagAmount() != 0 )
				strResult = String.valueOf(this.alGameBoardRow.get(iCol).alRowCell.get(iRow).getAroundFlagAmount());
			else
				strResult = "EMPTY";
		}
		else {
			if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow).isFlagged() )
				strResult = "FLAGGED";
			
			if( this.alGameBoardRow.get(iCol).alRowCell.get(iRow).isQuestionMarked() )
				strResult = "QUESTIONMARKED";
		}
		
		return strResult;
	}
}
