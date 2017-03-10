import java.util.*;

class TimeInfo {
	private long gameTime;
	private long remainTurnTime;
	private Player turnPlayer;
	private boolean timerOn;
	
	public TimeInfo(){
		timerOn=true;
	}
	public synchronized long getGameTime() {
		return gameTime;
	}
	public synchronized long getRemainTurnTime() {
		return remainTurnTime;
	}
	public synchronized Player getTurnPlayer() {
		return turnPlayer;
	}
	public synchronized void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}
	public synchronized void setRemainTurnTime(long remainTurnTime) {
		this.remainTurnTime = remainTurnTime;
	}
	public synchronized void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}
	public synchronized boolean isTimerOn() {
		return timerOn;
	}
	public synchronized void setTimerOn(boolean timerOn) {
		this.timerOn = timerOn;
	}
}

class GameInfo {
	private ArrayList<ColPiece> boardState;

	public synchronized ArrayList<ColPiece> getBoardState() {
		return boardState;
	}

	public synchronized void setBoardState(ArrayList<ColPiece> boardState) {
		this.boardState = boardState;
	}
	
}
