import java.awt.Point;

public class Bishop extends Piece {
	public Bishop(int x, int y) {
		this.setPosition(x, y);
	}
	public Bishop(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite); 
	}
}
