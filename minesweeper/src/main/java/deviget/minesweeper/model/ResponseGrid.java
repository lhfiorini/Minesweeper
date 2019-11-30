package deviget.minesweeper.model;

import java.util.ArrayList;
import java.util.Date;

public class ResponseGrid {
	public String 						GameCode;
	public String						GameStatus;
	public int 							FlagAmount;
	public int 							FlagDetectedAmount;
	public Date 						StartedDate;
	public ArrayList<ResponseGridNode> 	ResponseGridNodes;
	
	// builder
	public ResponseGrid() {
		GameCode = "";
		GameStatus = "";
		FlagAmount = 0;
		FlagDetectedAmount = 0;
		StartedDate = new Date();
		ResponseGridNodes = null;
	}

	// getters and setters
	
	// logical functions
	public void setGameBoardSize(int iColSize, int iRowSize) {
		
		this.ResponseGridNodes = new ArrayList<ResponseGridNode>();
		for( int iApun = 0; iApun < iColSize; iApun++ )					
			this.ResponseGridNodes.add(new ResponseGridNode( iRowSize ));
	}

	public void setCellLabel(int iCol, int iRow, String strCellLabel) {
		this.ResponseGridNodes.get(iCol).CellsLabel.set(iRow, strCellLabel);
	}
}
