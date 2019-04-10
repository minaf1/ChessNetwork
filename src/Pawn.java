import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
	public Pawn(int x, int y) {
		setPosition(x, y);
	}
	public Pawn(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite); 
	}
	
	boolean isValidMove(Piece[][] board, int x, int y) {
		Point loc = this.position();
		boolean color = this.isWhite();
		
		if(loc.y == y && color != !board[x][y].isWhite())
			if(loc.x == x || (loc.x+1) == x || (loc.x-1) == x)
				return true;
		return false;
	}
}
