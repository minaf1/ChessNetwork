import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {

	int increment;

	public Rook(int x, int y) {
		super.setPosition(x, y);
		increment = 0;
	}
	public Rook(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite);
	}

	boolean isValidMove(Piece[][] board, int x, int y) {	//4, 5
		ArrayList<Point> validMoves = new ArrayList();
		Point loc = this.position;							//4, 4
		int max, min;
		if(loc.x == x && loc.y != y) {						//y
			max = Math.max(loc.y, y);						//5
			min = Math.min(loc.y, y);						//4
			increment=1;
			
			for(int i=min+1; i<=max; i+=increment) {
				if(i == max) return true;
				
				if(board[x][i] == null || board[x][i].isWhite() == this.isWhite())
					return false;
			}
		}
		
		else if(loc.y == y && loc.x != x) {
			max = Math.max(loc.x, x);
			min = Math.min(loc.x, x);
			
			for(int i=min; i<=max; i+=increment) {
				
			}
		
		}
		
		
		return false;
	}
}
