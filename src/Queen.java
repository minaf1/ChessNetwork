
import java.awt.Point;
import java.util.Set;

public class Queen extends Piece {
	public Queen(int x, int y) {
		this.setPosition(x, y);
	}
	public Queen(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite); 
	}
}
