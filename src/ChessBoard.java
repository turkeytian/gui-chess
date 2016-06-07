import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChessBoard{
	private ChessLocation[][] m_chess_locations;
	private int m_rows;
	private int m_cols;
	
	ChessBoard(ChessBoard board) {
		this.m_rows = board.get_rows();
		this.m_cols = board.get_cols();
		this.m_chess_locations = new ChessLocation[board.get_rows()][board.get_cols()];
		for(int i = 0; i < board.get_rows(); i++) {
			for(int j = 0; j < this.m_cols; j++) {
				this.m_chess_locations[i][j] = new ChessLocation();
				if(board.m_chess_locations[i][j].has_piece()) {
					if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.BISHOP)
						this.m_chess_locations[i][j]
								.place_piece(new ChessBishopPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
					else if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.KNIGHT)
						this.m_chess_locations[i][j]
								.place_piece(new ChessKnightPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
					else if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.PAWN)
						this.m_chess_locations[i][j]
								.place_piece(new ChessPawnPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
					else if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.ROOK)
						this.m_chess_locations[i][j]
								.place_piece(new ChessRookPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
					else if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.QUEEN)
						this.m_chess_locations[i][j]
								.place_piece(new ChessQueenPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
					else if(board.m_chess_locations[i][j]
							.get_piece().get_role() == ChessPiece.KING)
						this.m_chess_locations[i][j]
								.place_piece(new ChessKingPiece(board.m_chess_locations[i][j]
							.get_piece().getColor()));
				}
			}
		}
	}
	
	ChessBoard(String filename, int rows, int cols) {
		this.m_rows = rows;
		this.m_cols = cols;
		File inputFile = new File(filename); 
		try {
			Scanner b = new Scanner(inputFile);
			try {
				this.m_chess_locations = 
						new ChessLocation[this.m_rows][this.m_cols];
				for(int i = 0; i < this.m_rows; i++) {
					for(int j = 0; j < this.m_cols; j++) {
						this.m_chess_locations[i][j] = new ChessLocation();
					}
					String row = b.nextLine();
					for(int j = 0; j < this.m_cols; j++) {
						if(row.charAt(j) == 'P')
							this.m_chess_locations[i][j].place_piece(new ChessPawnPiece(Piece.BLACK));
						else if(row.charAt(j) == 'p')
							this.m_chess_locations[i][j].place_piece(new ChessPawnPiece(Piece.WHITE));
						else if(row.charAt(j) == 'N')
							this.m_chess_locations[i][j].place_piece(new ChessKnightPiece(Piece.BLACK));
						else if(row.charAt(j) == 'n')
							this.m_chess_locations[i][j].place_piece(new ChessKnightPiece(Piece.WHITE));
						else if(row.charAt(j) == 'B')
							this.m_chess_locations[i][j].place_piece(new ChessBishopPiece(Piece.BLACK));
						else if(row.charAt(j) == 'b')
							this.m_chess_locations[i][j].place_piece(new ChessBishopPiece(Piece.WHITE));
						else if(row.charAt(j) == 'R')
							this.m_chess_locations[i][j].place_piece(new ChessRookPiece(Piece.BLACK));
						else if(row.charAt(j) == 'r')
							this.m_chess_locations[i][j].place_piece(new ChessRookPiece(Piece.WHITE));
						else if(row.charAt(j) == 'Q')
							this.m_chess_locations[i][j].place_piece(new ChessQueenPiece(Piece.BLACK));
						else if(row.charAt(j) == 'q')
							this.m_chess_locations[i][j].place_piece(new ChessQueenPiece(Piece.WHITE));
						else if(row.charAt(j) == 'K')
							this.m_chess_locations[i][j].place_piece(new ChessKingPiece(Piece.BLACK));
						else if(row.charAt(j) == 'k')
							this.m_chess_locations[i][j].place_piece(new ChessKingPiece(Piece.WHITE));
					}
				}
			} finally {
				b.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Board file does not exist!");
			System.exit(0);
		}
	}
	
	public int get_rows() {
		return this.m_rows;
	}
	
	public int get_cols() {
		return this.m_cols;
	}
	
	public ChessLocation [][] get_locations() {
		return this.m_chess_locations;
	}
}
