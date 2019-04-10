
public class Knight extends Piece {
	public Knight(int x, int y) {
		this.setPosition(x, y);
	}
	public Knight(String path, int x, int y, boolean isWhite) {
		super(path, x, y, isWhite); 
	}
}
