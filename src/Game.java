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
public class Game extends Thread{
	private TurnInfo gameTurnInfo;
	private GameInfo gameInfo;

	private Board gameBoard;
	private Timer gameTimer;
	private Play play;
	
	Game(Player playerRed, Player playerGreen){
		gameInfo=new GameInfo();
		gameInfo.setPlayerRed(playerRed);
		gameInfo.setPlayerGreen(playerGreen);
	}
	
	public void run(){
		gameBoard=new Board();
		gameTurnInfo=new TurnInfo();
		gameTimer=new Timer(gameTurnInfo);
		play=new Play(gameBoard,gameInfo,gameTurnInfo);
		play.run();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
