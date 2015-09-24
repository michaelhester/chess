/*Board.java*/

/**
 * Represents a Board configuration of a game of Checkers61bl
 * @author
 */

public class Board {

  /**
   *  Define any variables associated with a Board object here.  These
   *  variables MUST be private.
   */
	
  private Piece[][] pieces;
  private boolean shouldBeEmpty;
  private boolean hasMadeMove;
  private boolean hasSelectedPiece;
  private Piece selectedPiece;
  private int selectedX;
  private int selectedY;
  private int currentSide;
  private int firePiecesLeft;
  private int waterPiecesLeft;
  private boolean usedBomb;

  /**
   * Constructs a new Board
   * @param  shouldBeEmpty if true, add no pieces
   */
  public Board(boolean shouldBeEmpty) {
    this.shouldBeEmpty = shouldBeEmpty;
    this.pieces = new Piece[8][8];
    this.hasSelectedPiece = false;
    this.selectedX = -1;
    this.selectedY = -1;
    this.firePiecesLeft = 12;
    this.waterPiecesLeft = 12;
    this.hasMadeMove = false;
    this.currentSide = 0;
    setStartingPieces();
  }
  
  public void setStartingPieces() {
	  for (int i = 0; i < 8; i++) {
		  for (int j = 0; j < 8; j++) {
			  if (shouldBeEmpty) {
				  pieces[i][j] = null;
			  } else if (j < 3 || j > 4) {
				  if (i % 2 == 0) {
					  if (j == 0) {
						  pieces[i][j] = new Piece(0, this, "fire normal", false);
					  } else if (j == 2) {
						  pieces[i][j] = new BombPiece(0, this, "fire bomb", false);
					  } else if (j == 6) {
						  pieces[i][j] = new ShieldPiece(1, this, "water shield", false);
					  } else {
						  pieces[i][j] = null;
					  }
				  } else {
				      if (j == 1) {
				    	  pieces[i][j] = new Piece(0, this, "fire shield", false);
				      } else if (j == 5) {
				    	  pieces[i][j] = new BombPiece(1, this, "water bomb", false);
				      } else if (j == 7) {
				    	  pieces[i][j] = new ShieldPiece(1, this, "water normal", false);
				      } else {
				    	  pieces[i][j] = null;
				      }
				  }
			  } else {
				  pieces[i][j] = null;
			  }
		  }
	  }
  }
  
  public void drawBoard() {
	  for (int i = 0; i < 8; i++) {
	      for (int j = 0; j < 8; j++) {
	        if ((i + j) % 2 == 0) {
	          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	        } else {
	          StdDrawPlus.setPenColor(StdDrawPlus.RED);
	        }
	        if (i == selectedX && j == selectedY) {
	          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        }
	        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	        if (pieces[i][j] != null) {
	           if (pieces[i][j].getPieceType() == "fire normal") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	        	   }
	           } else if (pieces[i][j].getPieceType() == "fire shield") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	        	   }
	           } else if (pieces[i][j].getPieceType() == "fire bomb") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	        	   }
	           } else if (pieces[i][j].getPieceType() == "water normal") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	        	   }
	           } else if (pieces[i][j].getPieceType() == "water bomb") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	        	   }
	           } else if (pieces[i][j].getPieceType() == "water shield") {
	        	   if (pieces[i][j].isKing() == true) {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	        	   } else {
	        		   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	        	   }
	           }
	      	}
	      }
	  }
  }
	      


  /**
   * gets the Piece at coordinates (x, y)
   * @param  x X-coordinate of Piece to get
   * @param  y Y-coordinate of Piece to get
   * @return   the Piece at (x, y)
   */
  public Piece pieceAt(int x, int y) {
    if (pieces[x][y] == null) {
    	return null;
    } else if (x > 7 || x < 0 || y > 7 || y < 0) {
    	return null;
    } else {
    	return pieces[x][y];
    }
  }

  /**
   * Places a Piece at coordinate (x, y)
   * @param p Piece to place
   * @param x X coordinate of Piece to place
   * @param y Y coordinate of Piece to place
   */
  public void place(Piece p, int x, int y) {
    if (p == null) {
    	
    } else if (x > 7 || x < 0 || y > 7 || y < 0) {
 
    } else {
    	pieces[x][y] = p;
    }
  }

  /**
   * Removes a Piece at coordinate (x, y)
   * @param  x X coordinate of Piece to remove
   * @param  y Y coordinate of Piece to remove
   * @return   Piece that was removed
   */
  public Piece remove(int x, int y) {
	  if (pieces[x][y] == null) {
		  return null;
	  } else if (x > 7 || x < 0 || y > 7 || y < 0) {
		  return null;
	  } else {
		  Piece originalPiece = pieces[x][y];
		  if (originalPiece.getSide() == 0) {
			  firePiecesLeft--;
		  } else if (originalPiece.getSide() == 1) {
			  waterPiecesLeft--;
		  }
		  pieces[x][y] = null;
		  return originalPiece;
	  }
  }

  /**
   * Determines if a Piece can be selected
   * @param  x X coordinate of Piece
   * @param  y Y coordinate of Piece to select
   * @return   true if the Piece can be selected
   */
  public boolean canSelect(int x, int y) {
    if (x > 7 || x < 0 || y > 7 || y < 0) {
    	return false;
    } else if (usedBomb) {
    	return false;
    } else if (pieces[x][y] == null) {
    	if (hasSelectedPiece) {
    		if (validMove(selectedX, selectedY, x, y)) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    } else if (pieces[x][y].getSide() == currentSide) {
    	if (!hasSelectedPiece || !hasMadeMove) {
    		return true;
    	} else {
    		return false;
    	}
    } else if (pieces[x][y] != null && pieces[x][y].getSide() != currentSide) {
    	return false;
    } else {
    	return false;
    }
  }

  /**
   * Selects a square. If no Piece is active, selects the Piece and
   * makes it active. If a Piece is active, performs a move if an empty
   * place is selected. Else, allows you to reselect Pieces
   * @param x X coordinate of place to select
   * @param y Y coordinate of place to select
   */
  public void select(int x, int y) {
    if (hasSelectedPiece) {
    	if (pieces[x][y] == null) {
    		move(selectedX, selectedY, x, y);
    		selectedPiece = pieces[x][y];
            selectedX = x;
            selectedY = y;
    	} else {
        	selectedPiece = pieces[x][y];
        	selectedX = x;
        	selectedY = y;
    	}
    } else if (!hasSelectedPiece) {
    	hasSelectedPiece = true;
    	selectedPiece = pieces[x][y];
    	selectedX = x;
    	selectedY = y;
    } 
  }
  
  public boolean validMove(int x1, int y1, int x2, int y2) {
	  int differenceX = x2 - x1;
	  int differenceY = y2 - y1;
	  int middleX = (x2 + x1) / 2;
	  int middleY = (y2 + y1) / 2;
	  if (pieces[x2][y2] != null) {
		  return false;
	  } else if (pieces[x1][y1].isKing()) {
		  if (Math.abs(differenceX) == 1 && Math.abs(differenceY) == 1) {
			  if (hasMadeMove) {
				  return false;
			  } else {
				  return true;
			  }
		  } else if (Math.abs(differenceX) == 2 && Math.abs(differenceY) == 2) {
			  if (pieces[middleX][middleY].getSide() == pieces[x1][y1].getSide()) {
				  return false;
			  } else if (hasMadeMove && !pieces[x1][x2].hasCaptured()) {
				  return false;
			  } else {
				  return true;
			  }
		  } else {
			  return false;
		  }
	  } else if (pieces[x1][y1].getSide() == 0) {
		  if (Math.abs(differenceX) == 1 && differenceY == 1) {
			  if (hasMadeMove) {
				  return false;
			  } else {
				  return true;
			  }
		  } else if  (Math.abs(differenceX) == 2 && differenceY == 2) {
			  if (pieces[middleX][middleY].getSide() == pieces[x1][y1].getSide()) {
				  return false;
			  } else if (hasMadeMove && !pieces[x1][x2].hasCaptured()) {
				  return false;
			  } else {
				  return true;
			  }
		  } else {
			  return false;
		  }
	  } else if (pieces[x1][y1].getSide() == 1) {
		  if (Math.abs(differenceX) == 1 && differenceY == -1) {
			  if (hasMadeMove) {
				  return false;
			  } else {
				  return true;
			  }
		  } else if (Math.abs(differenceX) == 2 && differenceY == -2) {
			  if (pieces[middleX][middleY].getSide() == pieces[x1][y1].getSide()) {
				  return false;
			  } else if (hasMadeMove && !pieces[x1][x2].hasCaptured()) {
				  return false;
			  } else {
				  return true;
			  }
		  } else {
			  return false;
		  }
	  } else {
		  return false;
	  }
  }

  /**
   * Moves the piece located at Piece at (x1, y1) to (x2, y1)
   * @param x1 Original X coordinate of p
   * @param y1 Origin Y coordinate of p
   * @param x2 X coordinate to move to
   * @param y2 Y coordinate to move to
   */
  public void move(int x1, int y1, int x2, int y2) {
			  
	  pieces[x2][y2] = pieces[x1][y1];
	  pieces[x1][y1] = null;
		  
	  if (pieces[x2][y2].getSide() == 0 && y2 == 7) {
		  pieces[x2][y2].setIsKing(true);
	  } else if (pieces[x2][y2].getSide() == 1 && y2 == 0) {
		  pieces[x2][y2].setIsKing(true);
	  }
	  
	  int absDifferenceX = Math.abs(x2 - x1);
	  int absDifferenceY = Math.abs(y2 - y1);
	  int middleX = (x2 + x1) / 2;
	  int middleY = (y2 + y1) / 2;
	  
	  if (absDifferenceX == 2 && absDifferenceY == 2) {
		  if (pieces[x2][y2].isBomb()) {
			  pieces[x2][y2].explode(x2, y2);
			  this.usedBomb = true;
		  } else {
			  this.remove(middleX, middleY);
			  pieces[x2][y2].startCapturing();
		  }
	  }
	  
	  hasMadeMove = true;
  }
  
  /**
   * Determines if the turn can end
   * @return true if the turn can end
   */
  public boolean canEndTurn() {
    if (hasMadeMove) {
    	return true;
    } else {
    	return false;
    }
  }

  /**
   * Ends the current turn. Changes the player.
   */
  public void endTurn() {
	this.winner();
    this.hasSelectedPiece = false;
    this.hasMadeMove = false;
    if (this.selectedPiece != null) {
    	this.selectedPiece.finishCapturing();
    }
    this.selectedPiece = null;
    this.selectedX = -1;
    this.selectedY = -1;
    this.currentSide = 1 - this.currentSide;
    this.usedBomb = false;
  }

  /**
   * Returns the winner of the game
   * @return The winner of this game
   */
  public String winner() {
	  if (waterPiecesLeft == 0) {
		  return "Fire wins!";
	  } else if (firePiecesLeft == 0) {
		  return "Water wins!";
	  } else if (waterPiecesLeft == 0 && firePiecesLeft == 0) {
		  return "Tie!";
	  } else {
		  return null;
	  }
  }

  /**
   * Starts a game
   */
  public static void main(String[] args) {
    Board b = new Board(false);
    StdDrawPlus.setScale(0, 8);
    while (true) {
    	b.drawBoard();
    	if (StdDrawPlus.mousePressed()) {
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();
            if (b.canSelect(x, y)) {
            	b.select(x,y);
            } 
        } 
    	if (StdDrawPlus.isSpacePressed()) {
            if (b.canEndTurn()) {
            	b.endTurn();
            }
        }  
        StdDrawPlus.show(25);
    }
  }
}