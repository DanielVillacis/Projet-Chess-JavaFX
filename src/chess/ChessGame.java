package chess;

import java.awt.Point;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ChessGame {

	//Planche de jeu (incluant les pi�ces)
	private ChessBoard board;
	
	
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
	
	
	// NEW
	public void loadBoard(File file, int x, int y) {
		try {
			board = ChessBoard.readFromFile(file, x, y);
			board.getUI().toBack();
		}
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Error reading file", ButtonType.OK);
			alert.showAndWait();
	        return;
		}
	}
	
	//Charge une planche de jeu à partir d'un fichier.(NEW)
    public void loadBoard(String  path) {
       File file = new File(path);
      loadBoard(file, 0,0);
    }
    
    
    //Sauvegarde la planche de jeu actuelle dans un fichier.
  	public void saveBoard(File file) {

  		try {
  			board.saveToFile(file);
  		} catch (Exception e) {
  			Alert alert = new Alert(AlertType.ERROR, "Error writing file", ButtonType.OK);
  			alert.showAndWait();
  			return;
  		}
  	}
  	
  	
  	//Démarre l'enregistrement des mouvements du jeu dans un fichier de script.
  	public void saveScript(File file) {

  		try {
  			throw new Exception("Pas implanté!");
  		} catch (Exception e) {
  			Alert alert = new Alert(AlertType.ERROR, "Error writing file", ButtonType.OK);
  			alert.showAndWait();
  			return;
  		}
  	}
  	
  	
  	//Charge un fichier de script 
  	public void loadScript(File file) {

  		try {
  			throw new Exception("Pas implanté!");
  		} catch (Exception e) {
  			Alert alert = new Alert(AlertType.ERROR, "Error reading file", ButtonType.OK);
  			alert.showAndWait();
  			return;
  		}
  	}
  	
  	
	// Getter du ChessBoard
	public ChessBoard getBoard() {
		return board;
	}

}
