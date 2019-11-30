package deviget.minesweeper.model;

import java.util.ArrayList;

public class ResponseGridNode {
	public ArrayList<String> CellsLabel;
	
	public ResponseGridNode( int iRowSize ) {
		this.CellsLabel = new ArrayList<String>();
		for( int iApun = 0; iApun < iRowSize; iApun++ )
			this.CellsLabel.add("");
	}
}
