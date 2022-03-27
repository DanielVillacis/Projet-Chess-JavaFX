package chess.ui;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import chess.ChessBoard;
import chess.ChessPiece;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PieceView {
	
	/* Plusieurs elements de ChessPiece doivent etres deplaces ver PieceView. 
	 * Entres autres: 
	 * 		- la constante pieceSize (Done)
	 * 		- les constantes names et prefixes (Done)
	 * 		- l'atttribut board (Done)
	 * 		- l'attribut piecePane (Done)
	 * 		- la methode enableDragging(), contenant toute la gestion des actions de souris (Done)
	 * 		- une grande partie du constructeur
	 */
	
	
	// Taille d'une pièce dans l'interface
	private static double pieceSize = 75.0;
	
	// Utilisé pour générer les noms de fichiers contenant les images des pièces.
	private static final String names[] = { "pawn", "knight", "bishop", "rook", "queen", "king" };
	private static final String prefixes[] = { "w", "b" };
	
	// Référence à la planche de jeu. Utilisée pour déplacer la pièce.
	private ChessBoard board;

	
	// Panneau d'interface contenant l'image de la pièce
	private Pane piecePane;
	
	private Point2D pos;
	
	
	// Constructeur : 	
	public PieceView(ChessBoard b, ChessPiece piece) {

		board = b;
		
		Image pieceImage;
		
		
		try {
			pieceImage = new Image(new FileInputStream("images/" + prefixes[piece.getColor()] + names[piece.getType()] + ".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		ImageView pieceView = new ImageView(pieceImage);

		pieceView.setX(0);
		pieceView.setY(0);
		pieceView.setFitHeight(pieceSize);
		pieceView.setFitWidth(pieceSize);

		pieceView.setPreserveRatio(true);
		piecePane = new Pane(pieceView);
		enableDragging(piecePane, piece);
	}
	
	public PieceView(int x, int y, ChessBoard chessBoard) {
		new BoardView(x,y,chessBoard);
	}
	
	
	//Accesseurs divers
	public Pane getPane() {
		return piecePane;
	}
	
	
	// Methode enableDragging() : 
	// Gestionnaire d'événements pour le déplacement des pièces
	private void enableDragging(Node node, ChessPiece piece) {
		final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();

		// Lorsque la pièce est saisie, on préserve la position de départ
		node.setOnMousePressed(event -> {

			mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));
			pos = mouseAnchor.get();

		});

		// À chaque événement de déplacement, on déplace la pièce et on met à
		// jour la position de départ
		node.setOnMouseDragged(event -> {
			double deltaX = event.getSceneX() - mouseAnchor.get().getX();
			double deltaY = event.getSceneY() - mouseAnchor.get().getY();
			node.relocate(node.getLayoutX() + deltaX, node.getLayoutY() + deltaY);
			node.toFront();
			mouseAnchor.set(new Point2D(event.getSceneX(), event.getSceneY()));

		});

		// Lorsqu'on relâche la pièce, le mouvement correspondant est appliqué
		// au jeu d'échecs si possible.
		// L'image de la pièce est également centrée sur la case la plus proche.
		node.setOnMouseReleased(event -> {
			
			Point newGridPos = board.paneToGrid(event.getSceneX(), event.getSceneY());
			
			if(board.move(pos, mouseAnchor.get())) {
				Point2D newPos = board.gridToPane(newGridPos.x, newGridPos.y);
				node.relocate(newPos.getX(), newPos.getY());
				piece.setGridPos(newGridPos);
			}
			else {
				Point2D oldPos = board.gridToPane(piece.getGridX(), piece.getGridY());
				node.relocate(oldPos.getX(), oldPos.getY());
			}
			
		});
	}
	

}
