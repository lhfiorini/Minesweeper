package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class ResponseGrid {
	public String 						gameCode;
	public String						gameStatus;
	public int 							flagAmount;
	public int 							flagDetectedAmount;
	public Date 						startedDate;
	public ArrayList<ResponseGridNode> 	responseGridNodes;
	
	// builder
	public ResponseGrid() {
		gameCode = "";
		gameStatus = "";
		flagAmount = 0;
		flagDetectedAmount = 0;
		startedDate = new Date();
		responseGridNodes = null;
	}

	// getters and setters
	
	// logical functions
	public void setGameBoardSize(int colAmount, int rowAmount) {
		
		this.responseGridNodes = new ArrayList<ResponseGridNode>();
		for( int apun = 0; apun < colAmount; apun++ )					
			this.responseGridNodes.add(new ResponseGridNode( rowAmount ));
	}

	public void setCellLabel(int col, int row, String cellLabel) {
		this.responseGridNodes.get(col).cellsLabel.set(row, cellLabel);
	}
}
