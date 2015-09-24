/*BombPiece.java*/

/**
 *  Represents a BombPiece ins Checkers61bl
 * @author 
 */

public class BombPiece extends Piece {
 
  /**
   *  Define any variables associated with a BombPiece object here.  These
   *  variables MUST be private or package private.
   */

  /**
   * Constructs a new BombPiece
   * @param  side what side this BombPiece is on
   * @param  b    Board that this BombPiece belongs to
   */
  public BombPiece(int side, Board b, String pieceType, boolean isKing) {
	  super(side, b, pieceType, isKing);
	  this.setIsBomb(true);
  }
  
  
  @Override
  public void explode(int x, int y) {
	  
	  if (x + 1 <= 7 && y + 1 <= 7 && this.getBoard().pieceAt(x + 1, y + 1) != null) {
		  this.getBlownUp(x + 1, y + 1);
	  }
	  if (x + 1 <= 7 && y - 1 >= 0 && this.getBoard().pieceAt(x + 1, y - 1) != null) {
		  this.getBlownUp(x + 1, y - 1);
	  }
	  if (x - 1 >= 0 && y + 1 <=7 && this.getBoard().pieceAt(x - 1, y + 1) != null) {
		  this.getBlownUp(x - 1, y + 1);
	  }
	  if (x - 1 >= 0 && y - 1 >= 0 && this.getBoard().pieceAt(x - 1, y - 1) != null) {
		  this.getBlownUp(x - 1, y - 1);
	  }
	  
	  this.getBlownUp(x, y);

  }

}
