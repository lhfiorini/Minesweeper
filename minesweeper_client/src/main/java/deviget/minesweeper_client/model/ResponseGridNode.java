package deviget.minesweeper_client.model;

import java.util.ArrayList;

public class ResponseGridNode {
	public ArrayList<String> cellsLabel;
	
	public ResponseGridNode( int rowAmount ) {
		this.cellsLabel = new ArrayList<String>();
		for( int apun = 0; apun < rowAmount; apun++ )
			this.cellsLabel.add("");
	}
}
