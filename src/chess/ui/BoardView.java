package chess.ui;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import chess.ChessPiece;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BoardView {

	/* Plusieurs éléments de ChessBoard doivent être déplacés vers BoardView pour réaliser le découplage.
	 * Entre autres : 
	 * 		- les constantes de taille (Done)
	 * 		- les proprietes startX, startY, boardPane, boardView (Done)
	 * 		- les methodes de conversion de coordonnees(gridToPane et panetoGrid) (Done)
	 * 		- la presque totalite du constructeur (Done ?)
	 */
	
	// Les cases ont un peu plus de 80 pixels
	public static final double squareSize = 80.3;

	// La bordure fait 80 pixels tout le tour
	public static final int borderSize = 80;

	// L'arrière plan est dans une fen�tre 800x800.
	public static final int sceneSize = 800;

	// La bordure fait 80 pixels tout le tour
	public static final int pieceDeltaX = 12;
	
	// La bordure fait 80 pixels tout le tour
	public static final int pieceDeltaY = 2;
	
	// Panneau d'interface représentant la planche de jeu
	private Pane boardPane;
	private ImageView boardView;
	private double startX;
	private double startY;
	
	
	// Constructeur : 
	public BoardView(int x, int y) {
		
		startX = x;
		startY = y;
		
		// Création de l'arrière-plan.
		Image boardImage;
		
		try {
			boardImage = new Image(new FileInputStream("images/board.jpg"));
		} 
		catch (FileNotFoundException e) {
			return;
		}

		
		boardView = new ImageView(boardImage);

		boardView.setX(x);
		boardView.setY(y);

		boardView.setFitHeight(sceneSize);
		boardView.setFitWidth(sceneSize);

		boardView.setPreserveRatio(true);

		boardPane = new Pane(boardView);
	}
	
	// Methode gridToPane :
	public Point2D gridToPane(ChessPiece piece, int x, int y) {

		if (x < 0 || x > 7 || y < 0 || y > 7) {
			throw new IllegalArgumentException("Piece out of grid: (" + x + "," + y + ")");
		}

		return new Point2D(startX + x * squareSize + borderSize + pieceDeltaX, startY + y * squareSize + borderSize + pieceDeltaY);

	}
	
	// Methode paneToGrid :
	// Convertit des coordonnées en pixels sur la fenêtre d'interface en coordonnées dans la grille de l'échiquier
	// Utilisé pour détecter qu'on a touché une case spécifique de la grille.
	public Point paneToGrid(double xPos, double yPos) {
		if (xPos < (borderSize + startX)) {
			xPos = borderSize + startX;
		}
		if (xPos > startX + sceneSize - borderSize) {
			xPos = startX + sceneSize - borderSize - (squareSize / 2);
		}
		if (yPos < borderSize + startY) {
			yPos = borderSize + startY;
		}
		if (yPos > startY + sceneSize - borderSize) {
			yPos = startY + sceneSize - borderSize - (squareSize / 2);
		}
		
		int xGridPos = (int) ((xPos - (startX + borderSize)) / squareSize);
		int yGridPos = (int) ((yPos - (startY + borderSize)) / squareSize);
		return new Point(xGridPos, yGridPos);
	}
	
	// Getter du panel :
	public Pane getPane() {
		return boardPane;
	}
	
	
}
