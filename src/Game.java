
public class Game {
	private TimeInfo gameTimeInfo;
	private GameInfo gameInfo;

	private Board gameBoard;
	private Timer gameTimer;
	private Player playerRed;
	private Player playerGreen;
	
	Game(){
		gameInfo=new GameInfo();
		gameBoard=new Board(gameInfo);
		gameTimeInfo=new TimeInfo();
		gameTimer=new Timer(gameTimeInfo);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
