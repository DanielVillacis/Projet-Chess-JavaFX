package chess;

import java.awt.Point;

public class ChessGame {

	//Planche de jeu (incluant les pi�ces)
	private ChessBoard board;
	
	
	// Getter du ChessBoard
	public ChessBoard getBoard() {
		return board;
	}
	
	
	// Setter du ChessBoard
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	
	
	// Methode movePiece()
	public void movePiece(String string) {
		Point init = ChessUtils.convertAlgebraicPosition(string.substring(0, 2));
		Point end = ChessUtils.convertAlgebraicPosition(string.substring(3, 5));
		
		board.move(init, end);	
	}
	
	public boolean compareBoard(ChessBoard otherBoard) {
		
		if (board.equals(otherBoard)) {
			return true;
		}
		return false;		
	}
	
	public void loadBoard(String string) {
		
	}

}
