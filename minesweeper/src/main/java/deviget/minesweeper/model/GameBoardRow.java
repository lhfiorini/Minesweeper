package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class GameBoardRow {
	
	ArrayList<Cell> alRowCell;
	
	// builder
	public GameBoardRow( int iRowAmount ) {
		
		this.alRowCell = new ArrayList<Cell>();
		for( int iApun = 0; iApun < iRowAmount; iApun++ ) {
			Cell auxCell = new Cell();
			this.alRowCell.add( auxCell );
		}
	}
	
	// getters and setters
	
	// logical functions
}
