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
import java.io.Console;
import java.util.*;

public class Board{
	
	private LinkedList<Piece> Red=new LinkedList<Piece>();
	private LinkedList<Piece> Green=new LinkedList<Piece>();
	private char colStart;
	private char colStop;
	private int rowStart;
	private int rowStop;
	
	Board(){
		rowStart=1;
		rowStop=8;
		colStart='A';
		colStop='H';
	}
	
	Board(int rowStart,int rowStop,char colStart,char colStop){
		this.rowStart=rowStart;
		this.rowStop=rowStop;
		this.colStart=colStart;
		this.colStop=colStop;
	}
	
	ColPiece checkMove(ArrayList<ColPiece> list,int row, char column){
		for (ColPiece x:list){
			if(x.piece!=null)
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
		int maxRowCol=Math.max(colStop-colStart, rowStop-rowStart);
		if (x.type==PType.Regular){
			if(x.row+1<=rowStop && x.column-1>=colStart && fieldState(x.row+1,(char)(x.column-1)).field==FType.Free)
				ret.add(new ColPiece(new Piece(PType.Blank ,x.row+1,(char)(x.column-1)),FType.Free));
			if(x.row+1<=rowStop && x.column+1<=colStop && fieldState(x.row+1,(char)(x.column+1)).field==FType.Free)
				ret.add(new ColPiece(new Piece(PType.Blank,x.row+1,(char)(x.column+1)),FType.Free));
			
			if(x.row+2<=rowStop && x.column-2>=colStart && fieldState(x.row+2,(char)(x.column-2)).field==FType.Free) {
				ColPiece toSmash=fieldState(x.row+1,(char)(x.column-1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row+2,(char)(x.column-2)),FType.Free));
				}
			}					
			if(x.row+2<=rowStop && x.column+2<=colStop && fieldState(x.row+2,(char)(x.column+2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row+1,(char)(x.column+1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row+2,(char)(x.column+2)),FType.Free));
				}
			}
					
			if(x.row-2>=rowStart && x.column-2>=colStart && fieldState(x.row-2,(char)(x.column-2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row-1,(char)(x.column-1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row-2,(char)(x.column-2)),FType.Free));
				}
			}
					
			if(x.row-2>=rowStart && x.column+2<=colStop && fieldState(x.row-2,(char)(x.column+2)).field==FType.Free){
				ColPiece toSmash=fieldState(x.row-1,(char)(x.column+1));
				if (toSmash.field!=toMove.field){
					ret.add(toSmash);
					ret.add(new ColPiece(new Piece(x.type,x.row-2,(char)(x.column+2)),FType.Free));
				}
			}
		}
		else {
			boolean valid=true;
			for(int dist=1;dist<maxRowCol;++dist){
				if (x.row+dist<=rowStop && x.column-dist>=colStart && fieldState(x.row+dist,(char)(x.column-dist)).field==FType.Free)
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
					if (x.row+dist+1<=rowStop && x.column-dist-1>=colStart && fieldState(x.row+dist+1,(char)(x.column-dist-1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<maxRowCol;++dist){
				if (x.row+dist<=rowStop && x.column+dist<=colStop && fieldState(x.row+dist,(char)(x.column+dist)).field==FType.Free)
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
					if (x.row+dist+1<=rowStop && x.column+dist+1<=colStop && fieldState(x.row+dist+1,(char)(x.column+dist+1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<maxRowCol;++dist){
				if (x.row-dist>=rowStart && x.column+dist<=colStop && fieldState(x.row-dist,(char)(x.column+dist)).field==FType.Free)
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
					if (x.row-dist-1>=rowStart && x.column+dist+1<=colStop && fieldState(x.row-dist-1,(char)(x.column+dist+1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}
			valid=true;
			for(int dist=1;dist<maxRowCol;++dist){
				if (x.row-dist>=rowStart && x.column-dist>=colStart && fieldState(x.row-dist,(char)(x.column-dist)).field==FType.Free)
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
					if (x.row-dist-1>=rowStart && x.column-dist-1>=colStart && fieldState(x.row-dist-1,(char)(x.column-dist-1)).field==FType.Free)
						ret.add(toSmash);
					else
						break;
				}
			}	
		}
		return ret;
	}
	
	void set4RowGame(){
		for (int i=rowStart;i<=rowStop;++i){
			if (i>4 && i<rowStop-3)
				continue;
			
			char j;
			
			if (i%2==1)
				j='A';
			else
				j='B';
			
			if (i<(rowStart+rowStop)/2)
				for(;j<=colStop;j+=2)
					Red.add(new Piece(PType.Regular,i,j));
			else
				for(;j<=colStop;j+=2)
					Green.add(new Piece(PType.Regular,i,j));
		}
	}
	
	void set3RowGame(){
		for (int i=rowStart;i<=rowStop;++i){
			if (i>3 && i<rowStop-2)
				continue;
			
			char j;
			
			if (i%2==1)
				j='A';
			else
				j='B';
			
			if (i<(rowStart+rowStop)/2)
				for(;j<=colStop;j+=2)
					Red.add(new Piece(PType.Regular,i,j));
			else
				for(;j<=colStop;j+=2)
					Green.add(new Piece(PType.Regular,i,j));
		}
	}
	
	void set2RowGame(){
		for (int i=rowStart;i<=rowStop;++i){
			if (i>2 && i<rowStop-1)
				continue;
			
			char j;
			
			if (i%2==1)
				j='A';
			else
				j='B';
			
			if (i<(rowStart+rowStop)/2)
				for(;j<=colStop;j+=2)
					Red.add(new Piece(PType.Regular,i,j));
			else
				for(;j<=colStop;j+=2)
					Green.add(new Piece(PType.Regular,i,j));
		}
	}
		
	
	MType movePiece(Piece piece,int row, char column){
		if (fieldState(row,column).field!=FType.Free)
			return MType.Bad;
		ArrayList<ColPiece> vMove=validMove(piece);
		ColPiece move=checkMove(vMove,row,column);
		if (move!=null){
			if (move.piece.type!=PType.Blank){
				ColPiece toRemove=null;
				for (ColPiece x:vMove){
					if (x==move)
						break;
					if (x.field!=FType.Free)
						toRemove=x;
				}
				piece.row=row;
				piece.column=column;
				if (toRemove.field==FType.Green)
					Green.remove(toRemove.piece);
				else
					Red.remove(toRemove.piece);
				return MType.Kill;
			}
			else{
				piece.row=row;
				piece.column=column;
				return MType.Move;					
			}
		}
		return MType.Bad;
	}

	void print(){
		System.out.print("  ");
		for (char i=colStart;i<=colStop;++i)
			System.out.print(" "+i+" ");
		System.out.println(" ");
		System.out.print(" -");
		for(int i=colStart;i<=colStop;++i)
			System.out.print("---");
		System.out.println("-");
		for(int i=rowStop;i>=rowStart;--i){
			System.out.print(i+"|");
			for(char j=colStart;j<=colStop;++j){
				if(Red.contains(new Piece(PType.Regular,i,j)))
					System.out.print(" r ");
				else if (Red.contains(new Piece(PType.Queen,i,j)))
					System.out.print(" R ");
				else if (Green.contains(new Piece(PType.Regular,i,j)))
					System.out.print(" g ");
				else if (Green.contains(new Piece(PType.Queen,i,j)))
					System.out.print(" G ");
				else
					System.out.print(" _ ");
			}
			System.out.println("|");
		}
		System.out.print(" -");
		for(int i=colStart;i<=colStop;++i)
			System.out.print("---");
		System.out.println("-");
	}
	
	public static void main(String[] args){
		Board x=new Board();
		x.set3RowGame();
		x.print();
		while(true){
			Console tmp2=System.console();
			String tmp=tmp2.readLine();
			if (tmp.equals(new String("koniec")))
				break;
			
			int aRow=(int)tmp.charAt(0)-(int)('0');
			char aColumn=tmp.charAt(1);
			int bRow=(int)tmp.charAt(3)-(int)('0');
			char bColumn=tmp.charAt(4);
			
			ColPiece toMove=x.fieldState(aRow,aColumn);
			if (toMove.piece!=null){
				MType done=x.movePiece(toMove.piece, bRow, bColumn);
				switch(done){
				case Bad:
					System.out.println("Zly ruch");
					break;
				case Move:
					System.out.println("Ruch z poz.: "+aRow+aColumn+"\nNa poz.: "+bRow+bColumn);
					break;
				case Kill:
					System.out.println("Ruch z poz.: "+aRow+aColumn+"\nNa poz.: "+bRow+bColumn+"\nZbicie!");
				}
			}
			x.print();
		}
	}
}