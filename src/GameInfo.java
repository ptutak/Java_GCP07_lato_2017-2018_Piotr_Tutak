/* 
  Copyright 2017 Piotr Tutak
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
import java.util.ArrayList;

public class GameInfo {
	private GSType gameState;
	private ArrayList<ColPiece> boardState;
	
	private Player playerRed;
	private Player playerGreen;
	private Player winner;
	private int points;
	
	GameInfo(){
		gameState=GSType.GAME_PAUSE;
		winner=null;
		points=0;
	}
	
	public synchronized int getPoints() {
		return points;
	}

	public synchronized Player getPlayerRed() {
		return playerRed;
	}

	public synchronized Player getPlayerGreen() {
		return playerGreen;
	}

	public synchronized GSType getGameState() {
		return gameState;
	}

	public synchronized Player getWinner() {
		return winner;
	}

	public synchronized ArrayList<ColPiece> getBoardState() {
		return boardState;
	}
	
	public synchronized void setPoints(int points) {
		this.points = points;
	}
	
	public synchronized void setPlayerRed(Player playerRed) {
		this.playerRed = playerRed;
	}

	public synchronized void setPlayerGreen(Player playerGreen) {
		this.playerGreen = playerGreen;
	}
	
	public synchronized void setGameState(GSType gameState) {
		this.gameState = gameState;
	}

	public synchronized void setWinner(Player winner) {
		this.winner = winner;
	}
	public synchronized void setBoardState(ArrayList<ColPiece> boardState) {
		this.boardState = boardState;
	}
	
}
