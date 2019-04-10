
public class King extends Piece {
	public King(int x, int y) {
		this.setPosition(x, y);
	}
	public King(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite); 
	}
}
