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
class Play extends Thread {
	private GameInfo gameInfo;
	private TurnInfo turnInfo;
	private Board gameBoard;
	
	Play(Board gameBoard, GameInfo gameInfo, TurnInfo turnInfo){
		this.gameBoard=gameBoard;
		this.turnInfo=turnInfo;
		this.gameInfo=gameInfo;
	}
	
	void gameWin(){
		gameInfo.setGameState(GSType.GAME_WON);
		if (turnInfo.getActivePlayer()==gameInfo.getPlayerRed()){
			gameInfo.setWinner(gameInfo.getPlayerGreen());
			int points=0;
			for (ColPiece x:gameBoard.boardState()){
				if (x.field==FType.GREEN){
					if (x.piece.type==PType.PAWN)
						points+=1;
					else
						points+=2;
				}
			}
			gameInfo.setPoints(points);
		}
		else {
			gameInfo.setWinner(gameInfo.getPlayerRed());
			int points=0;
			for (ColPiece x:gameBoard.boardState()){
				if (x.field==FType.RED){
					if (x.piece.type==PType.PAWN)
						points+=1;
					else
						points+=2;
				}
			}
			gameInfo.setPoints(points);
		}
	}
	void nextTurn(){
		if (gameInfo.getPlayerGreen()==turnInfo.getActivePlayer())
			turnInfo.setActivePlayer(gameInfo.getPlayerRed());
		else
			turnInfo.setActivePlayer(gameInfo.getPlayerGreen());
	}
	public void run(){
		while(!turnInfo.isTimerOn()){
			try{
				sleep(10);
			} catch (InterruptedException e){}
		}
		while (gameInfo.getGameState()==GSType.GAME_RUNNING){
			if (turnInfo.getRemainTurnTime()<=0)
				gameWin();
			else{
				Move move=turnInfo.getActivePlayer().getMove();
				if (move.moveFrom.field==FType.GREEN && turnInfo.getActivePlayer()==gameInfo.getPlayerRed())
					continue;
				if (move.moveFrom.field==FType.RED && turnInfo.getActivePlayer()==gameInfo.getPlayerGreen())
					continue;
				switch(gameBoard.movePiece(move.moveFrom.piece, move.moveTo.piece.row, move.moveTo.piece.column)){
				case MOVE:
					nextTurn();
					break;
				case KILL:
					if (move.moveFrom.piece.type==PType.PAWN && move.moveTo.piece.row==gameBoard.getRowStop() && turnInfo.getActivePlayer()==gameInfo.getPlayerRed() && move.moveTo.piece.type==PType.QUEEN){
						nextTurn();
						break;
					}
					else if (move.moveFrom.piece.type==PType.PAWN && move.moveTo.piece.row==gameBoard.getRowStart() && turnInfo.getActivePlayer()==gameInfo.getPlayerGreen() && move.moveTo.piece.type==PType.QUEEN){
						nextTurn();
						break;
					}
					continue;
				case BAD:
					continue;
				}
			}
		}
		
	}
}
