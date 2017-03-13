
public class Move {
	public ColPiece moveFrom;
	public ColPiece moveTo;
	public PMType playerMove;
	Move(){
		playerMove=null;
		moveFrom=null;
		moveTo=null;
	}
	Move(PMType playerMove){
		this.playerMove=playerMove;
	}
	Move(PMType playerMove,ColPiece moveFrom, ColPiece moveTo){
		this.playerMove=playerMove;
		this.moveFrom=moveFrom;
		this.moveTo=moveTo;
	}
}
