import java.awt.Point;
import java.util.Set;

public abstract class Piece {
	protected String path;
	protected Point position;
	private boolean isWhite;
	
	public Piece() {}
	
	public Piece(String path, Point position, boolean isWhite) {
		this.path = path;
		this.position = position;
		this.isWhite = isWhite;
	}
	
	public Piece(String path, int x, int y, boolean isWhite) {
		this.path = path;
		this.position = new Point(x, y);
		this.isWhite = isWhite;
		
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isWhite() {
		return isWhite;
	}
	
	public void setIsWhite(boolean iw) {
		isWhite = iw;
	}
	
	public Point position() {
		return position;
	}
	
	public void setPosition(Point p) {
		position = p;
	}
	
	public void setPosition(int x, int y) {
		position = new Point(x, y);
	}
	
}
