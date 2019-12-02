package deviget.minesweeper_client.model;

import java.util.ArrayList;
import java.util.Date;

import deviget.minesweeper_client.model.ResponseGridNode;

public class ResponseGrid {
	public String 						gameCode;
	public String						gameStatus;
	public int 							flagAmount;
	public int 							flagDetectedAmount;
	public Date 						startedDate;
	public ArrayList<ResponseGridNode>  responseGridNodes;
	
	// builder
	public ResponseGrid() {
		gameCode = "";
		gameStatus = "";
		flagAmount = 0;
		flagDetectedAmount = 0;
		startedDate = new Date();
		responseGridNodes = new ArrayList<ResponseGridNode>();
	}
}
