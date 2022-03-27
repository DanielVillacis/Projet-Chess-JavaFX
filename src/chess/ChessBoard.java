package chess;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import chess.ui.BoardView;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


//Représente la planche de jeu avec les pièces.


public class ChessBoard {

	// Grille de jeu 8x8 cases. Contient des références aux piéces présentes sur
	// la grille.
	// Lorsqu'une case est vide, elle contient une pièce spéciale
	// (type=ChessPiece.NONE, color=ChessPiece.COLORLESS).
	private ChessPiece[][] grid;

	// Appel a BoardView afin d'avoir access aux aciennes proprietes de ChessBoard.
	private BoardView boardView;

	
	public ChessBoard(int x, int y) {
		
		// Creation d'une nouvelle board
		boardView = new BoardView(x,y, this);

		// Initialise la grille avec des pièces vides.
		grid = new ChessPiece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = new ChessPiece(i, j, this);
			}
		}
	}


	// Place une pièce vide dans la case
	public void clearSquare(int x, int y) {
		grid[x][y] = new ChessPiece(x, y, this);
	}

	
	// Place une pièce sur le planche de jeu.
	public void putPiece(ChessPiece piece) {

		Point2D pos = boardView.gridToPane(piece.getGridX(), piece.getGridY());
		piece.getUI().relocate(pos.getX(), pos.getY());
		getUI().getChildren().add(piece.getUI());
		grid[piece.getGridX()][piece.getGridY()] = piece;
	}


	public Pane getUI() {
		return boardView.getPane();
	}
	

	//Les cases vides contiennent une pièce spéciale
	public boolean isEmpty(Point pos) {
		return (grid[pos.x][pos.y].getType() == ChessUtils.TYPE_NONE);
	}

	
	//Vérifie si une coordonnée dans la grille est valide
	public boolean isValid(Point pos) {
		return (pos.x >= 0 && pos.x <= 7 && pos.y >= 0 && pos.y <= 7);
	}

	
	//Vérifie si les pièces à deux positions dans la grille sont de la même couleur.
	public boolean isSameColor(Point pos1, Point pos2) {
		return grid[pos1.x][pos1.y].getColor() == grid[pos2.x][pos2.y].getColor();
	}
	
	
	//Effectue un mouvement à partir de la notation algébrique des cases ("e2-b5" par exemple)
	public void algebraicMove(String move){
		if(move.length()!=5){
			throw new IllegalArgumentException("Badly formed move");
		}
		String start = move.substring(0,2);
		String end = move.substring(3,5);
		move(ChessUtils.convertAlgebraicPosition(start),ChessUtils.convertAlgebraicPosition(end));
	}
	
	
	//Effectue un mouvement sur l'échiqier. Quelques règles de base sont implantées ici.
	public boolean move(Point gridPos, Point newGridPos) {
		
		ChessPiece toMove = getPiece(gridPos);
		
		if (!toMove.verifyMove(gridPos, newGridPos)) {
			return false;
		}
		
		//Vérifie si les coordonnées sont valides
		if (!isValid(newGridPos)) {
			return false;
		}
	

		//Si la case destination est vide, on peut faire le mouvement
		else if (isEmpty(newGridPos)) {
			assignSquare(newGridPos, toMove);
			clearSquare(gridPos);
			return true;
		}

		//Si elle est occuppé par une pièce de couleur différente, alors c'est une capture
		else if (!isSameColor(gridPos, newGridPos)) {			
			removePiece(newGridPos);
			assignSquare(newGridPos, toMove);
			return true;
		}

		return false;
	}
	
	
	public void assignSquare(Point gridPos, ChessPiece piece) {
		grid[gridPos.x][gridPos.y] = grid[piece.getGridX()][piece.getGridY()];;
		grid[gridPos.x][gridPos.y].setGridPos(gridPos);
	}
	
	
	public void clearSquare(Point gridPos) {
		grid[gridPos.x][gridPos.y]= new ChessPiece(gridPos.x, gridPos.y, this);
	}
	
	
	public void removePiece(Point point) {
		getUI().getChildren().remove(grid[point.x][point.y].getUI());
		clearSquare(point);
	}
	
	
	public ChessPiece getPiece(Point point) {
		return grid[point.x][point.y];
	}
	
	
	// Ajout de la methode ChessBoard.move :
	public boolean move(Point2D gridPos, Point2D newGridPos) {

		Point init = boardView.paneToGrid(gridPos.getX(), gridPos.getY());
		Point end = boardView.paneToGrid(newGridPos.getX(), newGridPos.getY());

		return move(init, end);
	}
	
	
	//Fonctions de lecture et de sauvegarde d'échiquier dans des fichiers. À implanter.
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
				if(!isEmpty(pos)) {
					grid[i][j].saveToStream(writeFile);
				}
			}
		}
		writeFile.flush();
		writeFile.close();
	}
	
	
	public Point paneToGrid(double x, double y) {
		return boardView.paneToGrid(x, y);
	}
	
	
	public Point2D gridToPane(int x, int y) {
		return boardView.gridToPane(x, y);
	}
	
	
	// Implementation de la methode equals() pour le ChessBoard
	@Override
	public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		
		ChessBoard otherBoard = (ChessBoard) obj;
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(!grid[i][j].equals(otherBoard.grid[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

}
