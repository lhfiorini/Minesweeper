package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class GameBoardRow {
	
	ArrayList<Cell> gameBoardRowCell;
	
	// builder
	public GameBoardRow( int rowAmount ) {
		
		this.gameBoardRowCell = new ArrayList<Cell>();
		for( int apun = 0; apun < rowAmount; apun++ ) {
			Cell auxCell = new Cell();
			this.gameBoardRowCell.add( auxCell );
		}
	}
	
	// getters and setters
	
	// logical functions
}
