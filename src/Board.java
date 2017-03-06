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

class ColPiece{
	Piece piece;
	FType field;
	ColPiece(Piece piece,FType field){
		this.piece=piece;
		this.field=field;
	}
}


class Board{
	
	private LinkedList<Piece> Red=new LinkedList<Piece>();
	private LinkedList<Piece> Green=new LinkedList<Piece>();
	
	Piece containsPiece(LinkedList<Piece> list,Piece p){
		for(Piece x : list){
			if (x.equals(p))
				return x;
		}
		return null;
	}
	
	ColPiece checkMove(ArrayList<ColPiece> list,int row, char column){
		for (ColPiece x:list){
			if (x.piece.row==row && x.piece.column==column && x.field==FType.Free)
				return x;
		}
		return null;
	}
	
	ColPiece fieldState(int row, char column){
		for (Piece x:Red){
			if (x.row==row && x.column==column)
				return new ColPiece(x,FType.Red);
		}
		for (Piece x:Green){
			if (x.row==row && x.column==column)
				return new ColPiece(x,FType.Green);
		}
		return new ColPiece(null,FType.Free);
	}
	
	ArrayList<ColPiece> validMove(Piece x){
		ColPiece toMove=fieldState(x.row,x.column);
		ArrayList<ColPiece> ret=new ArrayList<ColPiece>();
		if (x.type==PType.Regular){
			if(x.row+1<9 && x.column-1>='A' && fieldState(x.row+1,(char)(x.column-1)).field==FType.Free)
				ret.add(new ColPiece(new Piece(PType.Blank ,x.row+1,(char)(x.column-1)),FType.Free));
			if(x.row+1<9 && x.column+1<='H' && fieldState(x.row+1,(char)(x.column+1)).field==FType.Free)
				ret.add(new ColPiece(new Piece(PType.Blank,x.row+1,(char)(x.column+1)),FType.Free));
			
			if(x.row+2<9 && x.column-2>='A' && fieldState(x.row+2,(char)(x.column-2)).field==FType.Free) {
				ColPiece toSmash=fieldState(x.row+1,(char)(x.column-1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row+2,(char)(x.column-2)),FType.Free));
				}
			}					
			if(x.row+2<9 && x.column+2<='H' && fieldState(x.row+2,(char)(x.column+2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row+1,(char)(x.column+1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row+2,(char)(x.column+2)),FType.Free));
				}
			}
					
			if(x.row-2>0 && x.column-2>='A' && fieldState(x.row-2,(char)(x.column-2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row-1,(char)(x.column-1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row-2,(char)(x.column-2)),FType.Free));
				}
			}
					
			if(x.row-2>0 && x.column+2<='H' && fieldState(x.row-2,(char)(x.column+2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row-1,(char)(x.column+1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row-2,(char)(x.column+2)),FType.Free));
				}
			}
		}
		else {
			boolean valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row+dist<9 && x.column-dist>='A' && fieldState(x.row+dist,(char)(x.column-dist)).field==FType.Free)
					if (valid)
						ret.add(new ColPiece(new Piece(PType.Blank,x.row+dist,(char)(x.column-dist)),FType.Free));
					else
						ret.add(new ColPiece(new Piece(x.type,x.row+dist,(char)(x.column-dist)),FType.Free));
				else{
					ColPiece toSmash=fieldState(x.row+dist,(char)(x.column-dist));
					if (toSmash.field==toMove.field)
						break;
					if (!valid)
						break;
					valid=false;
					if (x.row+dist+1<9 && x.column-dist-1>='A' && fieldState(x.row+dist+1,(char)(x.column-dist-1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row+dist<9 && x.column+dist<='H' && fieldState(x.row+dist,(char)(x.column+dist)).field==FType.Free)
					if (valid)
						ret.add(new ColPiece(new Piece(PType.Blank,x.row+dist,(char)(x.column+dist)),FType.Free));
					else
						ret.add(new ColPiece(new Piece(x.type,x.row+dist,(char)(x.column+dist)),FType.Free));
					
				else{
					ColPiece toSmash=fieldState(x.row+dist,(char)(x.column+dist));
					if (toSmash.field==toMove.field)
						break;
					if (!valid)
						break;
					valid=false;
					if (x.row+dist+1<9 && x.column+dist+1<='H' && fieldState(x.row+dist+1,(char)(x.column+dist+1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row-dist>0 && x.column+dist<='H' && fieldState(x.row-dist,(char)(x.column+dist)).field==FType.Free)
					if (valid)
						ret.add(new ColPiece(new Piece(PType.Blank,x.row-dist,(char)(x.column+dist)),FType.Free));
					else
						ret.add(new ColPiece(new Piece(x.type,x.row-dist,(char)(x.column+dist)),FType.Free));
				else{
					ColPiece toSmash=fieldState(x.row-dist,(char)(x.column+dist));
					if (toSmash.field==toMove.field)
						break;
					if (!valid)
						break;
					valid=false;
					if (x.row-dist-1>0 && x.column+dist+1<='H' && fieldState(x.row-dist-1,(char)(x.column+dist+1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<8;++dist){
				if (x.row-dist>0 && x.column-dist>='A' && fieldState(x.row-dist,(char)(x.column-dist)).field==FType.Free)
					if (valid)
						ret.add(new ColPiece(new Piece(PType.Blank,x.row-dist,(char)(x.column-dist)),FType.Free));
					else
						ret.add(new ColPiece(new Piece(x.type,x.row-dist,(char)(x.column-dist)),FType.Free));
				else{
					ColPiece toSmash=fieldState(x.row-dist,(char)(x.column-dist));
					if (toSmash.field==toMove.field)
						break;
					if (!valid)
						break;
					valid=false;
					if (x.row-dist-1>0 && x.column-dist-1>='A' && fieldState(x.row-dist-1,(char)(x.column-dist-1)).field==FType.Free)
						ret.add(toSmash);
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
		
	
	MType movePiece(Piece x,int row, char column){
		if (fieldState(row,column).field!=FType.Free)
			return MType.Bad;
		ArrayList<ColPiece> vMove=validMove(x);
		ColPiece move=checkMove(vMove,row,column);
		if (move!=null){
			if (move.piece.type!=PType.Blank){
				int i=vMove.indexOf(move)-1;
				while (vMove.get(i).field==FType.Free)
					--i;
				ColPiece toRemove=vMove.get(i);
				x.row=row;
				x.column=column;
				if (toRemove.field==FType.Green)
					Green.remove(toRemove.piece);
				else
					Red.remove(toRemove.piece);
				return MType.Kill;
			}
			else{
				x.row=row;
				x.column=column;
				return MType.Move;					
			}
		}
		return MType.Bad;
	}

	void print(){
		for(int i=0;i<8;++i)
			System.out.print("---");
		System.out.println("--");
		for(int i=8;i>0;--i){
			System.out.print("|");
			for(char j='A';j<'I';++j){
				if(containsPiece(Red, new Piece(PType.Regular,i,j))!=null)
					System.out.print(" r ");
				else if (containsPiece(Red, new Piece(PType.Queen,i,j))!=null)
					System.out.print(" R ");
				else if (containsPiece(Green, new Piece(PType.Regular,i,j))!=null)
					System.out.print(" g ");
				else if (containsPiece(Green, new Piece(PType.Queen,i,j))!=null)
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
	
	public static void main(String[] args){
		Board x=new Board();
		x.set2RowGame();
		x.print();
	}
	
}