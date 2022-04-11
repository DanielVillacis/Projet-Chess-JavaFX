package chess;

import java.io.FileWriter;

public class PieceMemento {

	// Attributs de PieceMemento:
	// conserve le type de piece, la couleur et la position sur la plance
	private int type;
	private int color;	
	private int gridPosX;
	private int gridPosY;
	
	public PieceMemento() {
		
	}
	
	// Deplacement des methodes readFromStream et saveToStream de ChessPiece vers PieceMemento.
	public static ChessPiece readFromStream(String line, ChessBoard board) {
		String position = line.substring(0,2);
		String color = line.substring(3,5);
		
		return new ChessPiece(color, position, board);
	}
	
	
	public void saveToStream(FileWriter file) throws Exception{
		try {
			String ret = 	ChessUtils.makeAlgebraicPosition(gridPosX, gridPosY) 
					+ "-" 
					+ ChessUtils.makePieceName(color, type) 
					+ "\n";
			file.write(ret);
		} 
		catch (Exception errorMsg) {
			throw errorMsg;
		}
	}
	
}
