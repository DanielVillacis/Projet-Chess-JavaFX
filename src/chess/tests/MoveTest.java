package chess.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.ChessBoard;
import chess.ChessGame;

class MoveTest {

	
	
	
	
	
	// Premiere methode de test.
	@Test
	public void testBasicCollision() throws Exception { 
		ChessGame game = new ChessGame();
		game.loadBoard("boards/normalStart");
	    ChessBoard result= ChessBoard.readFromFile("boards/normalStart"); 
	    //Move tower over a pawn of the same color game.movePiece("a1-a2");
	    assertTrue(game.compareBoard(result)); 
	}


}
