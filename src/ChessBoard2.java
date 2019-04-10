import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard2 extends JFrame {
	FileCell[][] cell;
	PieceLabel temp;

	Piece bq, bk, br1, br2, bb1, bb2, bn1, bn2, bp1, bp2, bp3, bp4, bp5, bp6, bp7, bp8, wq, wk, wr1, wr2, wb1, wb2, wn1,
			wn2, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8;
	Piece[] b_pieces;
	Piece[] w_pieces;

	public ChessBoard2() {
		this.cell = new FileCell[8][8];
		int r = 0;

		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				cell[i][j] = new FileCell(i + 1, j + 1);
				FileCell temp = cell[i][j];
				int t = (i % 2 == 0) ? 1 : 0;
				temp.setBackground(r++ % 2 == t ? Color.WHITE : Color.DARK_GRAY);
				temp.setFocusable(false);
				temp.addMouseListener(new MouseListener());
				this.add(temp);
			}
		}
	}

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
		for (Piece p : b_pieces)
			addPieces(p);
		for (Piece p : w_pieces)
			addPieces(p);
	}

	private void addPieces(Piece piece) {
		try {
			Point p = piece.position();
			PieceLabel label = new PieceLabel(piece);
			// label.addMouseListener(new MouseListener());
			FileCell fc = cell[p.y - 1][p.x - 1];
			fc.add(label);
			fc.setIsEmpty(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class MouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getComponent();

			if (c instanceof JPanel) {			//selected a cell 
				FileCell cell = (FileCell) c;
				if (!cell.isEmpty()) {			//if isn't empty so it has a piece inside it. 
					PieceLabel jl = (PieceLabel) cell.getComponent(0);
					System.out.println(jl.toString());
					if(temp == null) 			//so check temp if empty, assign Piece to temp
						temp = jl;
					else if(temp != null) {		//and swap if not
						if(temp.getPiece().isWhite() != jl.getPiece().isWhite()) {
							cell.removeAll();
							cell.add(temp);
						}
						temp = null;
					}
				}else {							//its empty.
					if(temp != null) {			//so just put in temp if set 
						cell.add(temp);
						temp = null;
					}
					System.out.println(cell.toString());
				}
			}
			repaint();
			pack();
		}
	}

	private class FileCell extends JPanel {
		private Point point;
		private boolean isEmpty = true;

		FileCell(int x, int y) {
			point = new Point(x, y);
		}

		public Component add(Component c) {
			isEmpty = false;
			return super.add(c);
		}

		public boolean isEmpty() {
			return isEmpty;
		}

		public void setIsEmpty(boolean isEmpty) {
			this.isEmpty = isEmpty;
		}

		@Override
		public String toString() {
			return point.x + "," + point.y;
		}
	}

	private class PieceLabel extends JLabel {
		private Piece piece;

		public PieceLabel(Piece piece) throws IOException {
			super(new ImageIcon(ImageIO.read(new File(piece.getPath()))), JLabel.CENTER);
			setPiece(piece);
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

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ChessBoard2 board = new ChessBoard2();
			board.initChessPieces();
			board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			board.setSize(400, 400);
			board.setLayout(new GridLayout(8, 8));
			board.setVisible(true);
		});
	}
}
