import java.util.*;


enum PType{
	Regular,
	Queen
}

class Piece{

	PType type;
	int row;
	char column;
	
	public Piece(PType type, int row, char column){
		this.type=type;
		this.row=row;
		this.column=column;
	}
	
	public boolean equals(Piece p){
		if (type==p.type && row==p.row && column==p.column)
			return true;
		return false;
	}

}

class Board{
	
	private LinkedList<Piece> Red=new LinkedList<Piece>();
	private LinkedList<Piece> Green=new LinkedList<Piece>();
	
	boolean containsRedPiece(Piece p){
		for(Piece x : Red){
			if (x.equals(p))
				return true;
		}
		return false;
	}
	
	boolean containsGreenPiece(Piece p){
		for(Piece x:Green){
			if (x.equals(p))
				return true;
		}
		return false;
	}
	
	boolean positionSettled(int row, char column){
		for (Piece x:Red){
			if (x.row==row && x.column==column)
				return true;
		}
		for (Piece x:Green){
			if (x.row==row && x.column==column)
				return true;
		}
		return false;
	}
	
	void set3RowGame(){
		for (int i=1;i<9;++i){
			if (i==4 || i==5)
				continue;
			
			char j;
			
			if (i%2==1)
				j='A';
			else
				j='B';
			
			if (i<4)
				for(;j<'I';j+=2)
					Red.add(new Piece(PType.Regular,i,j));
			else
				for(;j<'I';j+=2)
					Green.add(new Piece(PType.Regular,i,j));
		}
	}
	
	void set2RowGame(){
		for (int i=1;i<9;++i){
			if (i>2 && i<7)
				continue;
			
			char j;
			
			if (i%2==1)
				j='A';
			else
				j='B';
			
			if (i<3)
				for(;j<'I';j+=2)
					Red.add(new Piece(PType.Regular,i,j));
			else
				for(;j<'I';j+=2)
					Green.add(new Piece(PType.Regular,i,j));
		}
	}
		
	void print(){
		for(int i=0;i<8;++i)
			System.out.print("---");
		System.out.println("--");
		for(int i=8;i>0;--i){
			System.out.print("|");
			for(char j='A';j<'I';++j){
				if(containsRedPiece(new Piece(PType.Regular,i,j)))
					System.out.print(" r ");
				else if (containsRedPiece(new Piece(PType.Queen,i,j)))
					System.out.print(" R ");
				else if (containsGreenPiece(new Piece(PType.Regular,i,j)))
					System.out.print(" g ");
				else if (containsGreenPiece(new Piece(PType.Queen,i,j)))
					System.out.print(" G ");
				else
					System.out.print(" _ ");
			}
			System.out.println("|");
		}
		for(int i=0;i<8;++i)
			System.out.print("---");
		System.out.println("--");
	}
	
	boolean movePiece(Piece x,int row, char column){
		if (positionSettled(row,column))
			return false;
		switch(x.type){
		case Regular:
			if (Red.contains(x)){
				
			}
			break;
		case Queen:
		
		}
			
				
	}
	
	public static void main(String[] args){
		Board x=new Board();
		x.set2RowGame();
		x.print();
	}
	
}