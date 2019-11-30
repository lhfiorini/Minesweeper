package deviget.minesweeper.model;

public class RequestParam {

	private int		iCol;
	private int 	iColAmount;
	private int 	iFlagAmount;
	private int		iRow;
	private int 	iRowAmount;
	private int 	iUserID;
	private String 	strGameCode;

	// builder
	public RequestParam() {
		this.iColAmount = 0;
		this.iFlagAmount = 0;
		this.iRowAmount = 0;
		this.iUserID = 0;
	}
	
	// getters and setters
	public int getCol() {
		return iCol;
	}

	public void setCol(int iCol) {
		this.iCol = iCol;
	}
	
	public int getColAmount() {
		return iColAmount;
	}
	
	public void setColAmount(int iColAmount) {
		this.iColAmount = iColAmount;
	}
	
	public int getFlagAmount() {
		return iFlagAmount;
	}

	public void setFlagAmount(int iFlagAmount) {
		this.iFlagAmount = iFlagAmount;
	}
	
	public int getRow() {
		return iRow;
	}

	public void setRow(int iRow) {
		this.iRow = iRow;
	}
	
	public int getRowAmount() {
		return iRowAmount;
	}
	
	public void setRowAmount(int iRowAmount) {
		this.iRowAmount = iRowAmount;
	}
	
	public int getUserID() {
		return iUserID;
	}
	
	public void setUserID(int iUserID) {
		this.iUserID = iUserID;
	}
	
	public String getGameCode() {
		return strGameCode;
	}

	public void setGameCode(String strGameCode) {
		this.strGameCode = strGameCode;
	}
	
	// logical functions
}
