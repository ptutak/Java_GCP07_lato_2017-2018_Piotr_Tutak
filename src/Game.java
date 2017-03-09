
public class Game {

	public Info gameInfo;

	private Board gameBoard;
	private Timer gameTimer;
	private Player playerRed;
	private Player playerGreen;
	
	Game(){
		gameBoard=new Board();
		gameTimer=new Timer();
		gameInfo=new Info();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
