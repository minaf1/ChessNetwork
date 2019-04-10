import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard extends JFrame {

	FileCell[][] file;
	boolean selected = false;
	PieceLabel temp = null;
	JPanel source;

	Piece bq, bk, br1, br2, bb1, bb2, bn1, bn2, bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8, wq, wk, wr1, wr2, wb1, wb2, wn1,
			wn2, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8;
	Piece[] b_pieces;
	Piece[] w_pieces;

	private void initChessPieces() {
		br1 = new Rook("resources/blk_r.png", 1, 8, false);
		br2 = new Rook("resources/blk_r.png", 8, 8, false);
		wr1 = new Rook("resources/whi_r.png", 1, 1, true);
		wr2 = new Rook("resources/whi_r.png", 8, 1, true);

		bb1 = new Bishop("resources/blk_b.png", 2, 8, false);
		bb2 = new Bishop("resources/blk_b.png", 7, 8, false);
		wb1 = new Bishop("resources/whi_b.png", 2, 1, true);
		wb2 = new Bishop("resources/whi_b.png", 7, 1, true);

		bn1 = new Knight("resources/blk_n.png", 3, 8, false);
		bn2 = new Knight("resources/blk_n.png", 6, 8, false);
		wn1 = new Knight("resources/whi_n.png", 3, 1, true);
		wn2 = new Knight("resources/whi_n.png", 6, 1, true);

		bq = new Queen("resources/blk_q.png", 4, 8, false);
		wq = new Queen("resources/whi_q.png", 4, 1, true);
		bk = new King("resources/blk_k.png", 5, 8, false);
		wk = new King("resources/whi_k.png", 5, 1, true);

		bp1 = new Pawn("resources/blk_p.png", 1, 7, false);
		bp2 = new Pawn("resources/blk_p.png", 2, 7, false);
		bp3 = new Pawn("resources/blk_p.png", 3, 7, false);
		bp4 = new Pawn("resources/blk_p.png", 4, 7, false);
		bp5 = new Pawn("resources/blk_p.png", 5, 7, false);
		bp6 = new Pawn("resources/blk_p.png", 6, 7, false);
		bp7 = new Pawn("resources/blk_p.png", 7, 7, false);
		bp8 = new Pawn("resources/blk_p.png", 8, 7, false);

		wp1 = new Pawn("resources/whi_p.png", 1, 2, true);
		wp2 = new Pawn("resources/whi_p.png", 2, 2, true);
		wp3 = new Pawn("resources/whi_p.png", 3, 2, true);
		wp4 = new Pawn("resources/whi_p.png", 4, 2, true);
		wp5 = new Pawn("resources/whi_p.png", 5, 2, true);
		wp6 = new Pawn("resources/whi_p.png", 6, 2, true);
		wp7 = new Pawn("resources/whi_p.png", 7, 2, true);
		wp8 = new Pawn("resources/whi_p.png", 8, 2, true);

		b_pieces = new Piece[] { bq, bk, br1, br2, bb1, bb2, bn1, bn2, bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8 };
		w_pieces = new Piece[] { wq, wk, wr1, wr2, wb1, wb2, wn1, wn2, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8 };
		for (Piece p : b_pieces) {
			addPieces(p);
		}
		for (Piece p : w_pieces) {
			addPieces(p);
		}
	}

	public ChessBoard() {
		file = new FileCell[8][8];
		int i = 0, t = 0;

		for (int index = 7; index >= 0; index--) {
			for (int j = 0; j < 8; j++) {
				int ind = index * 8 + j;

				file[ind][j] = new FileCell(index, j);
				t = (index % 2 == 0) ? 1 : 0;
				file[ind][j].setBackground(i++ % 2 == t ? Color.WHITE : Color.DARK_GRAY);
				file[ind][j].setFocusable(true);
				file[ind][j].addMouseListener(new MousePressed());
				file[ind][j].addMouseListener(new MouseSelector());
				this.add(file[ind][j]);
			}
		}
	}

	private void addPieces(Piece piece) {
		try {
			Point p = piece.position();
			PieceLabel label = new PieceLabel(piece);
			//System.out.println(label.getIcon().toString());
			label.addMouseListener(new MouseSelector());
			file[(p.y - 1) * 8 + (p.x - 1)].add(label);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static BufferedImage flip(BufferedImage image) {
		AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(1, -1));
		at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
		return createTransformed(image, at);
	}

	private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.transform(at);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ChessBoard board = new ChessBoard();
			board.initChessPieces();
			board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			board.setSize(400, 400);
			board.setLayout(new GridLayout(8, 8));
			board.setVisible(true);
		});
	}

	private class MousePressed extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JPanel jp = (JPanel) e.getComponent();
			System.out.println(jp.toString());
		}
	}

	private class MouseSelector extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getComponent();
			if (c instanceof FileCell) {
				FileCell jp = (FileCell) c;
				Component[] pc = jp.getComponents();
				Arrays.stream(pc)
					.forEach((a)->System.out.println((PieceLabel) a));
				//PieceLabel pl = (PieceLabel) pc[0];
				if (selected) {
					if (pc != null && pc[0] instanceof PieceLabel && pl.getPiece().isWhite() != temp.piece.isWhite()) {
						jp.removeAll();
						jp.add(temp);
					}
				}
				selected = false;
			} else if (c instanceof JLabel) {
				temp = (PieceLabel) c;
				selected = true;
			}
			repaint();
		}
	}

	private class FileCell extends JPanel {
		private Point p;
		private boolean isEmpty;

		FileCell(int x, int y) {
			p = new Point(x, y);
		}

		@Override
		public String toString() {
			return p.x + "," + p.y;
		}

		public boolean isEmpty() {
			return isEmpty;
		}

		public void setIsEmpty(boolean isEmpty) {
			this.isEmpty = isEmpty;
		}
	}

	private class PieceLabel extends JLabel {
		private Piece piece;

		public PieceLabel(Piece piece) throws IOException {
			super(new ImageIcon(ImageIO.read(new File(piece.getPath()))), JLabel.CENTER);
		}

		public Piece getPiece() {
			return piece;
		}

		public void setPiece(Piece piece) {
			this.piece = piece;
		}

		@Override
		public String toString() {
			return "PieceLabel [piece=" + piece + "]";
		}

	}
}
