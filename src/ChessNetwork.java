import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ChessNetwork extends JFrame {
	private JTextField score;
	private String chessBoardImage;
	private BufferedImage chessBoard;
	private JLabel chessBoard1;
	private Piece[][] physicalChessBoard;
	
	public ChessNetwork() {
		score = new JTextField("Score");
		physicalChessBoard = new Piece[8][8];
		try {
			chessBoard = ImageIO.read(new File(chessBoardImage));
			chessBoard1 = new JLabel(new ImageIcon(chessBoard));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MouseHandler mh = new MouseHandler();
		chessBoard1.addMouseListener(mh);
		
		
	}

}
class MouseHandler extends MouseAdapter {
	private Point offset;
	
	@Override
	public void mousePressed(MouseEvent e) {
		Component p = e.getComponent();		
		moveToFront(p);
		offset = e.getPoint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getPoint().x - offset.x;
		int y = e.getPoint().y - offset.y;
		Component comp = e.getComponent();
		Point location = comp.getLocation();
		location.x += x;
		location.y += y;
		comp.setLocation(location);
	}
}

