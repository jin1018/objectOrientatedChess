//Object
//Oriented
//Chess

/* Sample Output:
Begin Chess Game. Moves Format: ex)P,A3
   A B C D E F G H 
  -----------------
8| r n b k q b n r |
7| p p p p p p p p |
6| . . . . . . . . |
5| . . . . . . . . |
4| . . . . . . . . |
3| . . . . . . . . |
2| P P P P P P P P |
1| R N B Q K B N R |
  -----------------
Player1. Enter your move: P,A3
   A B C D E F G H 
  -----------------
8| r n b k q b n r |
7| p p p p p p p p |
6| . . . . . . . . |
5| . . . . . . . . |
4| . . . . . . . . |
3| P . . . . . . . |
2| . P P P P P P P |
1| R N B Q K B N R |
  -----------------
Player2. Enter your move: 
*/


import java.util.Scanner;

public class chess{
	public static void main(String[] args) {
        //Board: contains the current state of the game
		Board chessboard = new Board(); //create board object
		Player1 white_player1 = new Player1(chessboard);//Player object (white moves first)
		Player2 black_player2 = new Player2(chessboard);//Player object
		System.out.print("Begin Chess Game. Moves Format: ex)P,A3\n");
		chessboard.display();

		Scanner in = new Scanner(System.in);
		while (chessboard.gameEnd ==false){//after each move, update chessboard.gameEnd (game ends when king is captured)
			//White's Turn
			System.out.print("Player1. Enter your move: ");
			String p1_move = System.console().readLine();
			white_player1.move(chessboard,p1_move);
			if (! black_player2.kingAvailable()){//after each move, update chessboard.gameEnd (game ends when king is captured)
				chessboard.gameEnd = true;
			}
			chessboard.display();
			
			//Black's Turn
			System.out.print("Player2. Enter your move: ");
			String p2_move = System.console().readLine();
			black_player2.move(chessboard, p2_move);
			if (! white_player1.kingAvailable()){//after each move, update chessboard.gameEnd (game ends when king is captured)
				chessboard.gameEnd = true;
			}
			chessboard.display();
		}
    }
}

class Board{
	private int boardLength = 8;// 8x8 board
	public char[][] boardArray = new char[boardLength][boardLength];
	public boolean gameEnd = false;
	public Board(){//initialize board
		for(int i=0; i < boardArray.length ; i++){
	    	for (int j=0; j < boardArray[i].length ; j++){
	    		boardArray[i][j] = '.';
	    	}
		}
	}

	public void display(){
		System.out.print("   A B C D E F G H \n");
		System.out.print("  -----------------\n");
		int row_num = 0;
		for (char[] row : boardArray){
			row_num ++;
			System.out.print( String.valueOf(9-row_num) + "| ");
			for (char col : row){
				System.out.print(col + " ");
			}
			System.out.print("|"); 
			System.out.println();
		}
		System.out.print("  -----------------\n");
	}
}

class Player{
	pieces king1; pieces queen1; pieces rook1; pieces rook2; pieces bishop1; pieces bishop2; pieces knight1; pieces knight2;
	pieces pawn1; pieces pawn2; pieces pawn3; pieces pawn4; pieces pawn5; pieces pawn6; pieces pawn7; pieces pawn8;
	public void move(Board associatedBoard, String move_String){//after each move, update chessboard.gameEnd
		//System.out.println("Move entered is: " + move_String);
		//Input format: ex) P,A3
		char piece = move_String.charAt(0);
		char row = move_String.charAt(3);
		char column = move_String.charAt(2);
		//System.out.println("Piece entered is: " + piece);
		//System.out.println("Row entered is: " + row);
		//System.out.println("Column entered is: " + column);

		int col=100;
		switch(column){
			case 'A': col = 0; break;
			case 'B': col = 1; break;
			case 'C': col = 2; break;
			case 'D': col = 3; break;
			case 'E': col = 4; break;
			case 'F': col = 5; break;
			case 'G': col = 6; break;
			case 'H': col = 7; break;
			//default: System.out.println("line 90 error");//ToDo: handle error
		}
		switch(piece){
			case 'P': 
				//get current location
				int currentX = pawn1.x_location;//temporary - assume we are moving pawn1. Todo: public void checkWhichPiece(String move_String)
				int currentY = pawn1.y_location;//temporary - assume we are moving pawn1. Todo: public void checkWhichPiece(String move_String)
				//System.out.println(String.valueOf(currentX));
				//System.out.println(String.valueOf(currentY));

				//pawn1.x_location=8-row;
				//pawn1.y_location=col;
				//System.out.println(String.valueOf(8-Character.getNumericValue(row)));
				//System.out.println(String.valueOf(col));
				associatedBoard.boardArray[currentX][currentY]='.';
				associatedBoard.boardArray[8-Character.getNumericValue(row)][col]='P';
				//ToDo: specific details on rules for P(pawn)
			//ToDo: case K, Q, R, B, N
		}
	}
	public boolean kingAvailable(){
		int currentKingX = king1.x_location;
		int currentKingy = king1.y_location;
		//System.out.println(String.valueOf(currentKingX));
		//System.out.println(String.valueOf(currentKingy));
		if ((currentKingX>=0 || currentKingX<=7) && (currentKingy>=0 || currentKingy<=7)){
			return true;
		}
		else{
			return false;
		}
	}
	/*ToDo
	public void isValidInput_MoveStringFormat(String move_String){
	}
	*/
	/*ToDo
	public void isValidMove(String move_String){
	}
	*/
	/*ToDo: distinguish pawn1,2,3,...8, rook1, rook2, ...
	public void checkWhichPiece(String move_String){
	}
	*/
}

class Player1 extends Player{
	public Player1 (Board associatedBoard){
		//initialize: set up pieces in the correct locations
		king1 = new pieces(associatedBoard, 7,4,'K');
		queen1 = new pieces(associatedBoard, 7,3,'Q');
		rook1 = new pieces(associatedBoard, 7,0,'R');
		rook2 = new pieces(associatedBoard, 7,7,'R');
		bishop1 = new pieces(associatedBoard, 7,2,'B');
		bishop2 = new pieces(associatedBoard, 7,5,'B');
		knight1 = new pieces(associatedBoard, 7,1,'N');
		knight2 = new pieces(associatedBoard, 7,6,'N');
		pawn1 = new pieces(associatedBoard, 6,0,'P');
		pawn2 = new pieces(associatedBoard, 6,1,'P');
		pawn3 = new pieces(associatedBoard, 6,2,'P');
		pawn4 = new pieces(associatedBoard, 6,3,'P');
		pawn5 = new pieces(associatedBoard, 6,4,'P');
		pawn6 = new pieces(associatedBoard, 6,5,'P');
		pawn7 = new pieces(associatedBoard, 6,6,'P');
		pawn8 = new pieces(associatedBoard, 6,7,'P');
	}
}

class Player2 extends Player{
	public Player2 (Board associatedBoard){
		//initialize: set up pieces in the correct locations
		king1 = new pieces(associatedBoard, 0,3,'k');
		queen1 = new pieces(associatedBoard, 0,4,'q');
		rook1 = new pieces(associatedBoard, 0,0,'r');
		rook2 = new pieces(associatedBoard, 0,7,'r');
		bishop1 = new pieces(associatedBoard, 0,2,'b');
		bishop2 = new pieces(associatedBoard, 0,5,'b');
		knight1 = new pieces(associatedBoard, 0,1,'n');
		knight2 = new pieces(associatedBoard, 0,6,'n');
		pawn1 = new pieces(associatedBoard, 1,0,'p');
		pawn2 = new pieces(associatedBoard, 1,1,'p');
		pawn3 = new pieces(associatedBoard, 1,2,'p');
		pawn4 = new pieces(associatedBoard, 1,3,'p');
		pawn5 = new pieces(associatedBoard, 1,4,'p');
		pawn6 = new pieces(associatedBoard, 1,5,'p');
		pawn7 = new pieces(associatedBoard, 1,6,'p');
		pawn8 = new pieces(associatedBoard, 1,7,'p');
	}
}

class pieces{
	//public boolean survived = true;
	public int x_location;
	public int y_location;

	public pieces(Board associatedBoard, int x, int y, char c){
		associatedBoard.boardArray[x][y] = c;
		this.x_location=x;
		this.y_location=y;
	}
}

