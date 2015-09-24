/*Piece.java*/

/**
 *  Represents a Normal Piece in Checkers61bl
 * @author 
 */

public class Piece {
  
  /**
   *  Define any variables associated with a Piece object here.  These
   *  variables MUST be private or package private.
   */

  private String pieceType;
  private boolean isKing;
  private int side; 
  private Board b;
  private boolean hasCaptured;
  private boolean isBomb = false;
  private boolean isShield = false;
  
  /**
   * Initializes a Piece
   * @param  side The side of the Piece
   * @param  b    The Board the Piece is on
   */
  

  public Piece(int side, Board b, String pieceType, boolean isKing) {
	this.pieceType = pieceType;
	this.isKing = isKing;
	this.side = side;
	this.b = b;
	this.hasCaptured = false;
  }



/**
   * Returns the side that the piece is on
   * @return 0 if the piece is fire and 1 if the piece is water
   */
  
  public int getSide() {
	  return this.side;
  }
  
  public void setSide(int side) {
	  this.side = side;
  }
  
  public String getPieceType() {
	  return this.pieceType;
  }
  
  public void setPieceType(String type) {
	  this.pieceType = type;
  }

  public boolean isKing() {
	  return this.isKing;
  }
  
  public void setIsKing(boolean isItKing) {
	  this.isKing = isItKing;
  }
  
  public Board getBoard() {
	  return this.b;
  }
  
  public boolean isBomb() {
	  return this.isBomb;
  }
  
  public void setIsBomb(boolean isItBomb) {
	  this.isBomb = isItBomb;
  }
  
  public boolean isShield() {
	  return this.isShield;
  }
  
  public void setIsShield(boolean isItShield) {
	  this.isShield = isItShield;
  }


  /**
   * Destroys the piece at x, y. ShieldPieces do not blow up
   * @param x The x position of Piece to destroy
   * @param y The y position of Piece to destroy
   */
  void getBlownUp(int x, int y) {
	  if (b.pieceAt(x, y) == null) {
		  
	  } else if (x > 7 || x < 0 || y > 7 || y < 0) {
		 
	  } else if (b.pieceAt(x,y).getPieceType() == "water shield" || b.pieceAt(x,y).getPieceType() == "fire shield"){
		  
	  } else {
		  b.remove(x, y);
	  }
  }

  /**
   * Does nothing. For bombs, destroys pieces adjacent to it
   * @param x The x position of the Piece that will explode
   * @param y The y position of the Piece that will explode
   */
  void explode(int x, int y) {
    
  }

  /**
   * Signals that this Piece has begun to capture (as in it captured a Piece)
   */
  void startCapturing() {
    this.hasCaptured = true;
  }

  /**
   * Returns whether or not this piece has captured this turn
   * @return true if the Piece has captured
   */
  public boolean hasCaptured() {
    return this.hasCaptured;
  }

  /**
   * Resets the Piece for future turns
   */
  public void finishCapturing() {
	this.hasCaptured = false;
  }

}