package deviget.minesweeper.model;

public class RequestParam {

	private int		col;
	private int 	colAmount;
	private int 	flagAmount;
	private int		row;
	private int 	rowAmount;
	private int 	userID;

	// builder
	public RequestParam() {
		this.col = 0;
		this.colAmount = 0;
		this.flagAmount = 0;
		this.row = 0;
		this.rowAmount = 0;
		this.userID = 0;
	}
	
	// getters and setters
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public int getColAmount() {
		return colAmount;
	}
	
	public void setColAmount(int colAmount) {
		this.colAmount = colAmount;
	}
	
	public int getFlagAmount() {
		return flagAmount;
	}

	public void setFlagAmount(int flagAmount) {
		this.flagAmount = flagAmount;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRowAmount() {
		return rowAmount;
	}
	
	public void setRowAmount(int rowAmount) {
		this.rowAmount = rowAmount;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	// logical functions
}
