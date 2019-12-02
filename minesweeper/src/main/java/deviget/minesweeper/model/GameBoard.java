package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class GameBoard {
	
	private ArrayList<GameBoardRow> gameBoardRow;


	// builder
	public GameBoard( int colAmount, int rowAmount ) {
		
		this.gameBoardRow = new ArrayList<GameBoardRow>();
		for( int apun = 0; apun < colAmount; apun++ ) {
			GameBoardRow auxGameBoardRow = new GameBoardRow(rowAmount);
			this.gameBoardRow.add( auxGameBoardRow );
		}
	}
	
	// getters and setters
	public int getColSize() {
		return this.gameBoardRow.size();
	}

	public int getRowSize() {
		return this.gameBoardRow.get(0).gameBoardRowCell.size();
	}
	
	// logical functions
	public boolean isFlag(int col, int row) {
		
		return this.gameBoardRow.get(col).gameBoardRowCell.get(row).isFlag();
	}

	public void setRevealed(int col, int row) {
		
		this.gameBoardRow.get(col).gameBoardRowCell.get(row).setRevealed(true);
		if( this.gameBoardRow.get(col).gameBoardRowCell.get(row).getAroundFlagAmount() == 0 )
			this.revealNeighbors( col, row );
	}

	private void revealNeighbors(int col, int row) {
		
		// one cell can have up to 8 neighbors, all of them must be check if they can be revealed.
		
		// check if there is a row above
		if( row > 0 ) {
			// check if the neighbor above-right exists, then if it is not revealed lets check if it must be reveal
			if( col > 0 ) {
				if( !this.gameBoardRow.get(col-1).gameBoardRowCell.get(row-1).isRevealed() ) {
					this.gameBoardRow.get(col-1).gameBoardRowCell.get(row-1).setRevealed(true);
					if( this.gameBoardRow.get(col-1).gameBoardRowCell.get(row-1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( col-1, row-1 );
				}
			}
			
			// check if the neighbor above exists, then if it is not revealed lets check if it must be reveal
			if( !this.gameBoardRow.get(col).gameBoardRowCell.get(row-1).isRevealed() ) {
				this.gameBoardRow.get(col).gameBoardRowCell.get(row-1).setRevealed(true);
				if( this.gameBoardRow.get(col).gameBoardRowCell.get(row-1).getAroundFlagAmount() == 0 )
					this.revealNeighbors( col, row-1 );
			}
			
			// check if the neighbor above-left exists, then if it is not revealed lets check if it must be reveal
			if( col < this.gameBoardRow.size() - 1 ) {
				if( !this.gameBoardRow.get(col+1).gameBoardRowCell.get(row-1).isRevealed() ) {
					this.gameBoardRow.get(col+1).gameBoardRowCell.get(row-1).setRevealed(true);
					if( this.gameBoardRow.get(col+1).gameBoardRowCell.get(row-1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( col+1, row-1 );
				}
			}
		}
		
		// check if the neighbor right exists, then if it is not revealed lets check if it must be reveal
		if( col > 0 ) {
			if( !this.gameBoardRow.get(col-1).gameBoardRowCell.get(row).isRevealed() ) {
				this.gameBoardRow.get(col-1).gameBoardRowCell.get(row).setRevealed(true);
				if( this.gameBoardRow.get(col-1).gameBoardRowCell.get(row).getAroundFlagAmount() == 0 )
					this.revealNeighbors( col-1, row );
			}
		}
		
		// check if the neighbor left exists, then if it is not revealed lets check if it must be reveal
		if( col < this.gameBoardRow.size() - 1 ) {
			if( !this.gameBoardRow.get(col+1).gameBoardRowCell.get(row).isRevealed() ) {
				this.gameBoardRow.get(col+1).gameBoardRowCell.get(row).setRevealed(true);
				if( this.gameBoardRow.get(col+1).gameBoardRowCell.get(row).getAroundFlagAmount() == 0 )
					this.revealNeighbors( col+1, row );
			}
		}
		
		// check if there is a row below
		if( row < this.gameBoardRow.get(col).gameBoardRowCell.size() - 1 ) {
			// check if the neighbor below-right exists, then if it is not revealed lets check if it must be reveal
			if( col > 0 ) {
				if( !this.gameBoardRow.get(col-1).gameBoardRowCell.get(row+1).isRevealed() ) {
					this.gameBoardRow.get(col-1).gameBoardRowCell.get(row+1).setRevealed(true);
					if( this.gameBoardRow.get(col-1).gameBoardRowCell.get(row+1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( col-1, row+1 );
				}
			}
			
			// check if the neighbor below exists, then if it is not revealed lets check if it must be reveal
			if( !this.gameBoardRow.get(col).gameBoardRowCell.get(row+1).isRevealed() ) {
				this.gameBoardRow.get(col).gameBoardRowCell.get(row+1).setRevealed(true);
				if( this.gameBoardRow.get(col).gameBoardRowCell.get(row+1).getAroundFlagAmount() == 0 )
					this.revealNeighbors( col, row+1 );
			}
			
			// check if the neighbor below-left exists, then if it is not revealed lets check if it must be reveal
			if( col < this.gameBoardRow.size() - 1 ) {
				if( !this.gameBoardRow.get(col+1).gameBoardRowCell.get(row+1).isRevealed() ) {
					this.gameBoardRow.get(col+1).gameBoardRowCell.get(row+1).setRevealed(true);
					if( this.gameBoardRow.get(col+1).gameBoardRowCell.get(row+1).getAroundFlagAmount() == 0 )
						this.revealNeighbors( col+1, row+1 );
				}
			}
		}
	}

	public void setFlags(int flagAmount) {
		
		int col, row, apun;
		
		// locate random the flags
		apun = 0;
		while( apun< flagAmount ) {
			
			// calculate a random row and column where to set a flag
			col = (int) (Math.random() * this.gameBoardRow.size());
			row = (int) (Math.random() * this.gameBoardRow.get(col).gameBoardRowCell.size());
			
			// check if this cell is not a flag, to make it flag and update the neighbors
			if( !this.gameBoardRow.get(col).gameBoardRowCell.get(row).isFlag() ) {
				this.gameBoardRow.get(col).gameBoardRowCell.get(row).setFlag(true);
				this.updateNeighborsFlag(col, row);
				apun++;
			}
		}
	}

	private void updateNeighborsFlag(int col, int row) {
		
		// one cell can have up to 8 neighbors, all of them must be check if they can be revealed.
		
		// check if there is a row above
		if( row > 0 ) {
			// check if the neighbor above-right exists and updated
			if( col > 0 )
				this.gameBoardRow.get(col-1).gameBoardRowCell.get(row-1).addAroundFlagAmount();
			
			// check if the neighbor above exists and updated
			this.gameBoardRow.get(col).gameBoardRowCell.get(row-1).addAroundFlagAmount();
			
			// check if the neighbor above-left exists and updated
			if( col < this.gameBoardRow.size() - 1 )
				this.gameBoardRow.get(col+1).gameBoardRowCell.get(row-1).addAroundFlagAmount();
		}
		
		// check if the neighbor right exists and updated
		if( col > 0 )
			this.gameBoardRow.get(col-1).gameBoardRowCell.get(row).addAroundFlagAmount();
		
		// check if the neighbor left exists and updated
		if( col < this.gameBoardRow.size() - 1 )
			this.gameBoardRow.get(col+1).gameBoardRowCell.get(row).addAroundFlagAmount();
		
		// check if there is a row below
		if( row < this.gameBoardRow.get(col).gameBoardRowCell.size() - 1 ) {
			// check if the neighbor below-right exists and updated
			if( col > 0 )
				this.gameBoardRow.get(col-1).gameBoardRowCell.get(row+1).addAroundFlagAmount();
			
			// check if the neighbor below exists and updated
			this.gameBoardRow.get(col).gameBoardRowCell.get(row+1).addAroundFlagAmount();
			
			// check if the neighbor below-left exists and updated
			if( col < this.gameBoardRow.size() - 1 )
				this.gameBoardRow.get(col+1).gameBoardRowCell.get(row+1).addAroundFlagAmount();
		}
	}

	public String getCellLabel(int col, int row) {
		
		String result = "";
		
		// checking if the cell was revealed
		if( this.gameBoardRow.get(col).gameBoardRowCell.get(row).isRevealed() ) {
			if( this.gameBoardRow.get(col).gameBoardRowCell.get(row).getAroundFlagAmount() != 0 )
				result = String.valueOf(this.gameBoardRow.get(col).gameBoardRowCell.get(row).getAroundFlagAmount());
			else
				result = "EMPTY";
		}
		else {
			if( this.gameBoardRow.get(col).gameBoardRowCell.get(row).isFlagged() )
				result = "FLAGGED";
			
			if( this.gameBoardRow.get(col).gameBoardRowCell.get(row).isQuestionMarked() )
				result = "QUESTIONMARKED";
		}
		
		return result;
	}

	public boolean isRevealed(int col, int row) {
		return this.gameBoardRow.get(col).gameBoardRowCell.get(row).isRevealed();
	}

	public boolean isFlagged(int col, int row) {
		return this.gameBoardRow.get(col).gameBoardRowCell.get(row).isFlagged();
	}

	public void setFlagged(int col, int row, boolean bFlagged) {
		this.gameBoardRow.get(col).gameBoardRowCell.get(row).setFlagged( bFlagged );
	}

	public void setQuestionMarked(int col, int row, boolean bQuestionMarked) {
		this.gameBoardRow.get(col).gameBoardRowCell.get(row).setQuestionMarked( bQuestionMarked );
	}

	public boolean isQuestionMarked(int col, int row) {
		return this.gameBoardRow.get(col).gameBoardRowCell.get(row).isQuestionMarked();
	}

	public boolean checkEndGame() {
		
		boolean result = true;
		int col = 0;
		while( col < this.getColSize() ) {
			int row = 0;
			while( row < this.getRowSize() ) {
				result = result && (this.isFlag(col, row) == this.isFlagged(col, row));
				row++;
			}
			col++;
		}
		
		return result;
	}
}
