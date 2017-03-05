import java.util.*;


enum PType{
	Blank,
	Regular,
	Queen
}

enum FType{
	Free,
	Red,
	Green
}

enum MType{
	Bad,
	Move,
	Kill
}

class Piece{

	PType type;
	int row;
	char column;
	
	Piece(PType type, int row, char column){
		this.type=type;
		this.row=row;
		this.column=column;
	}
	
	boolean equals(Piece p){
		if (type==p.type && row==p.row && column==p.column)
			return true;
		return false;
	}

}

class Board{
	
	private LinkedList<Piece> Red=new LinkedList<Piece>();
	private LinkedList<Piece> Green=new LinkedList<Piece>();
	
	boolean containsPiece(LinkedList<Piece> list,Piece p){
		for(Piece x : list){
			if (x.equals(p))
				return true;
		}
		return false;
	}
	
	FType fieldState(int row, char column){
		for (Piece x:Red){
			if (x.row==row && x.column==column)
				return FType.Red;
		}
		for (Piece x:Green){
			if (x.row==row && x.column==column)
				return FType.Green;
		}
		return FType.Free;
	}
	
	LinkedList<Piece> validMove(Piece x){
		LinkedList<Piece> ret=new LinkedList<Piece>();
		if (x.type==PType.Regular){
			if(containsPiece(Red, x)){
				if(x.row+1<9 && x.column-1>='A' && fieldState(x.row+1,(char)(x.column-1))==FType.Free)
					ret.add(new Piece(PType.Blank ,x.row+1,(char)(x.column-1)));
				if(x.row+1<9 && x.column+1<='H' && fieldState(x.row+1,(char)(x.column+1))==FType.Free)
					ret.add(new Piece(PType.Blank,x.row+1,(char)(x.column+1)));
			}
			else{
				if(x.row-1>0 && x.column-1>='A' && fieldState(x.row-1,(char)(x.column-1))==FType.Free)
					ret.add(new Piece(PType.Blank ,x.row-1,(char)(x.column-1)));
				if(x.row-1>0 && x.column+1<='H' && fieldState(x.row-1,(char)(x.column+1))==FType.Free)
					ret.add(new Piece(PType.Blank,x.row-1,(char)(x.column+1)));
			}
			if(x.row+2<9 && x.column-2>='A' && fieldState(x.row+1,(char)(x.column-1))==FType.Green)
				ret.add(new Piece(x.type,x.row+2,(char)(x.column-2)));
			if(x.row+2<9 && x.column+2<='H' && fieldState(x.row+1,(char)(x.column+1))==FType.Green)
				ret.add(new Piece(x.type,x.row+2,(char)(x.column+2)));
			if(x.row-2>0 && x.column-2>='A' && fieldState(x.row-1,(char)(x.column-1))==FType.Green)
				ret.add(new Piece(x.type,x.row-2,(char)(x.column-2)));
			if(x.row-2>0 && x.column+2<='H' && fieldState(x.row-1,(char)(x.column+1))==FType.Green)
				ret.add(new Piece(x.type,x.row-2,(char)(x.column+2)));
		} 
		else {
			boolean valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row+dist<9 && x.column-dist>='A' && fieldState(x.row+dist,(char)(x.column-dist))==FType.Free)
					if (valid)
						ret.add(new Piece(PType.Blank,x.row+dist,(char)(x.column-dist)));
					else
						ret.add(new Piece(x.type,x.row+dist,(char)(x.column-dist)));
				else{
					if (valid){
						valid=false;
						continue;	
					}
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row+dist<9 && x.column+dist<='H' && fieldState(x.row+dist,(char)(x.column+dist))==FType.Free)
					if (valid)
						ret.add(new Piece(PType.Blank,x.row+dist,(char)(x.column+dist)));
					else
						ret.add(new Piece(x.type,x.row+dist,(char)(x.column+dist)));
					
				else{
					if (valid){
						valid=false;
						continue;	
					}
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row-dist>0 && x.column+dist<='H' && fieldState(x.row-dist,(char)(x.column+dist))==FType.Free)
					if (valid)
						ret.add(new Piece(PType.Blank,x.row-dist,(char)(x.column+dist)));
					else
						ret.add(new Piece(x.type,x.row-dist,(char)(x.column+dist)));
				else{
					if (valid){
						valid=false;
						continue;	
					}
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row-dist>0 && x.column-dist>='A' && fieldState(x.row-dist,(char)(x.column-dist))==FType.Free)
					if (valid)
						ret.add(new Piece(PType.Blank,x.row-dist,(char)(x.column-dist)));
					else
						ret.add(new Piece(x.type,x.row-dist,(char)(x.column-dist)));
				else{
					if (valid){
						valid=false;
						continue;	
					}
					else
						break;
				}
			}	
		}
		return ret;
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
				if(containsPiece(Red, new Piece(PType.Regular,i,j)))
					System.out.print(" r ");
				else if (containsPiece(Red, new Piece(PType.Queen,i,j)))
					System.out.print(" R ");
				else if (containsPiece(Green, new Piece(PType.Regular,i,j)))
					System.out.print(" g ");
				else if (containsPiece(Green, new Piece(PType.Queen,i,j)))
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
	
	 MType movePiece(Piece x,int row, char column){
		if (fieldState(row,column)!=FType.Free)
			return MType.Bad;
		LinkedList<Piece> vMove=validMove(x);
		if (containsPiece(vMove,new Piece(PType.Blank,row,column))){
			x.row=row;
			x.column=column;
			return MType.Move;					
		}
		else if (containsPiece(vMove,new Piece(x.type,row,column))){
			
		}
		return false;
	}
	
	public static void main(String[] args){
		Board x=new Board();
		x.set2RowGame();
		x.print();
	}
	
}