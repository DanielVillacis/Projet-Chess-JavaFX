package chess;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardMemento {
	
	private ChessPiece[][] grid;
	private ChessBoard board; // Pas sur.
	
	// BoardMemento est une liste de PieceMemento.
	public ArrayList<PieceMemento> boardMem = new ArrayList<PieceMemento>();
	
	
	
	// Deplacement des methodes saveToFile et readFromFile de ChessBoard a BoardMemento.
	public static ChessBoard readFromFile(String fileName) throws Exception {
		return readFromFile(new File(fileName), 0, 0);
	}
				
	// Implementation de la methode readFromFile()
	public static ChessBoard readFromFile(File file, int x, int y) throws Exception {
		ChessBoard chessBoard = new ChessBoard(x,y);
		Scanner scan = new Scanner(file);
			
		while (scan.hasNextLine()) {
			chessBoard.putPiece(ChessPiece.readFromStream(scan.nextLine(), chessBoard));

		}
		scan.close();
		return chessBoard;
	}
				
	// Implementation de la methode saveToFile()
	public void saveToFile(File file) throws Exception {
		FileWriter writeFile = new FileWriter(file.getAbsoluteFile());
			
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				Point pos = new Point(i,j);
				if(!board.isEmpty(pos)) { // board.isEmpty pas sur.
					grid[i][j].saveToStream(writeFile);
				}
			}
		}
		writeFile.flush();
		writeFile.close();
	}

}
