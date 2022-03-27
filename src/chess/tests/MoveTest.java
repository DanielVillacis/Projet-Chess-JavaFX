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
	    //Move tower over a pawn of the same color 
	    game.movePiece("a1-a2");
	    assertTrue(game.compareBoard(result)); 
	}
	
	
	// Tests du mouvement des pieces individuelles.
	@Test
	public void testMouvementPawn() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/onePawn");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/onePawnTest1"); 
		game.movePiece("e4-e5");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementKnight() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneKnight");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/oneKnightTest1"); 
		game.movePiece("e4-f6");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementBishop() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneBishop");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/oneBishopTest1"); 
		game.movePiece("e4-c6");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementRook() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneRook");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/oneRookTest1"); 
		game.movePiece("e4-b4");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementKing() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneKing");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/oneKingTest1"); 
		game.movePiece("e4-d4");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementQueen() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneQueen");
		ChessBoard result = ChessBoard.readFromFile("boards/saves/oneQueenTest1"); 
		game.movePiece("e4-e7");
		assertTrue(game.compareBoard(result));
	}
	
	
	// Test des mouvement illegaux pour chaque piece.
	@Test
	public void testMouvementIllegauxPawn() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/onePawn");
		ChessBoard result = ChessBoard.readFromFile("boards/onePawn"); 
		game.movePiece("e4-e3");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementIllegauxKnight() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneKnight");
		ChessBoard result = ChessBoard.readFromFile("boards/oneKnight"); 
		game.movePiece("e4-e5");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementIllegauxBishop() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneBishop");
		ChessBoard result = ChessBoard.readFromFile("boards/oneBishop");
		game.movePiece("e4-e6");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementIllegauxRook() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneRook");
		ChessBoard result = ChessBoard.readFromFile("boards/oneRook");
		game.movePiece("e4-d5");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementIllegauxKing() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneKing");
		ChessBoard result = ChessBoard.readFromFile("boards/oneKing");
		game.movePiece("e4-e8");
		assertTrue(game.compareBoard(result));
	}
	
	@Test
	public void testMouvementIllegauxQueen() throws Exception {
		ChessGame game = new ChessGame();
		game.loadBoard("boards/oneQueen");
		ChessBoard result = ChessBoard.readFromFile("boards/oneQueen"); 
		game.movePiece("e4-f6");
		assertTrue(game.compareBoard(result));
	}


}
