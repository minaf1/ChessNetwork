import java.awt.Point;

public class Chess {
	Piece[][] board;
	Piece blkqueen, whiqueen, blkking, whiking, blkrook1, blkrook2, whirook1, whirook2, blkbishop1, blkbishop2, whibishop1, whibishop2, 
			blkknight1, blkknight2, whiknight1, whiknight2,  blkpawn1, blkpawn2, blkpawn3, blkpawn4, blkpawn5, blkpawn6, 
			blkpawn7, blkpawn8, whipawn1, whipawn2, whipawn3, whipawn4, whipawn5, whipawn6, whipawn7, whipawn8;
	
	Piece[] blkPieces = { blkrook1, blkrook2, blkbishop1, blkbishop2, blkknight1, blkknight2, blkqueen, blkking,
			blkpawn1, blkpawn2, blkpawn3, blkpawn4, blkpawn5, blkpawn6, blkpawn7, blkpawn8 },
			whiPieces = { whirook1, whirook2, whibishop1, whibishop2, whiknight1, whiknight2, whiqueen, whiking,
					whipawn1, whipawn2, whipawn3, whipawn4, whipawn5, whipawn6, whipawn7, whipawn8 };

	Chess() {
		board = new Piece[9][9];
		initChessPieces();
		for (Piece p : blkPieces) {
			p.setIsWhite(false);
			Point loc = p.position();
			board[loc.x][loc.y] = p;
		}
		for (Piece p : whiPieces) {
			p.setIsWhite(true);
			Point loc = p.position;
			board[loc.x][loc.y] = p;
		}

	}

	private void initChessPieces() {
		blkrook1 = new Rook(1, 8);
		blkrook2 = new Rook(8, 8);
		whirook1 = new Rook(1, 1);
		whirook2 = new Rook(8, 1);

		blkbishop1 = new Bishop(2, 8);
		blkbishop2 = new Bishop(7, 8);
		whibishop1 = new Bishop(2, 1);
		whibishop2 = new Bishop(7, 1);

		blkknight1 = new Knight(3, 8);
		blkknight2 = new Knight(6, 8);
		whiknight1 = new Knight(3, 1);
		whiknight2 = new Knight(6, 1);

		blkqueen = new Queen(4, 8);
		whiqueen = new Queen(4, 1);
		blkking = new King(5, 8);
		whiking = new King(5, 1);

		blkpawn1 = new Pawn(1, 8);
		blkpawn2 = new Pawn(2, 8);
		blkpawn3 = new Pawn(3, 8);
		blkpawn4 = new Pawn(4, 8);
		blkpawn5 = new Pawn(5, 8);
		blkpawn6 = new Pawn(6, 8);
		blkpawn7 = new Pawn(7, 8);
		blkpawn8 = new Pawn(8, 8);

		whipawn1 = new Pawn(1, 1);
		whipawn2 = new Pawn(2, 1);
		whipawn3 = new Pawn(3, 1);
		whipawn4 = new Pawn(4, 1);
		whipawn5 = new Pawn(5, 1);
		whipawn6 = new Pawn(6, 1);
		whipawn7 = new Pawn(7, 1);
		whipawn8 = new Pawn(8, 1);
	}

}
