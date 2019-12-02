package deviget.minesweeper.model;

public class Cell {

	private boolean flag;
	private boolean flagged;
	private boolean questionMarked;
	private boolean revealed;
	private int 	aroungFlagAmount;
	
	// builder
	public Cell() {
		this.flag = false;
		this.flagged = false;
		this.questionMarked = false;
		this.revealed = false;
		this.aroungFlagAmount = 0;
	}
	
	// getters and setters
	public boolean isFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	
	public boolean isQuestionMarked() {
		return questionMarked;
	}
	
	public void setQuestionMarked(boolean questionMarked) {
		this.questionMarked = questionMarked;
	}
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
	
	public int getAroundFlagAmount() {
		return aroungFlagAmount;
	}
	
	public void setAroundFlagAmount(int aroungFlagAmount) {
		this.aroungFlagAmount = aroungFlagAmount;
	}
	
	// logical functions
	public void addAroundFlagAmount() {
		this.aroungFlagAmount++;
	}

}
