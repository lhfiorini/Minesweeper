package deviget.minesweeper.model;

public class Cell {

	private boolean bFlag;
	private boolean bFlagged;
	private boolean bQuestionMarked;
	private boolean bRevealed;
	private int 	iAroundFlagAmount;
	
	// builder
	public Cell() {
		this.bFlag = false;
		this.bFlagged = false;
		this.bQuestionMarked = false;
		this.bRevealed = false;
		this.iAroundFlagAmount = 0;
	}
	
	// getters and setters
	public boolean isFlag() {
		return bFlag;
	}
	
	public void setFlag(boolean bFlag) {
		this.bFlag = bFlag;
	}
	
	public boolean isFlagged() {
		return bFlagged;
	}
	
	public void setFlagged(boolean bFlagged) {
		this.bFlagged = bFlagged;
	}
	
	public boolean isQuestionMarked() {
		return bQuestionMarked;
	}
	
	public void setQuestionMarked(boolean bQuestionMarked) {
		this.bQuestionMarked = bQuestionMarked;
	}
	
	public boolean isRevealed() {
		return bRevealed;
	}
	
	public void setRevealed(boolean bRevealed) {
		this.bRevealed = bRevealed;
	}
	
	public int getAroundFlagAmount() {
		return iAroundFlagAmount;
	}
	
	public void setAroundFlagAmount(int iAroundFlagAmount) {
		this.iAroundFlagAmount = iAroundFlagAmount;
	}
	
	// logical functions
	public void addAroundFlagAmount() {
		this.iAroundFlagAmount++;
	}

}
