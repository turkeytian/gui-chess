
public class ChessLocation{
	private ChessPiece m_chess_piece;
	
	public boolean has_piece () {
		return this.m_chess_piece != null;
	}
	
	public void place_piece(ChessPiece piece) {
		this.m_chess_piece = piece;
	}
	
	public ChessPiece get_piece() {
		return this.m_chess_piece;
	}
	
	public void take_piece() {
		this.m_chess_piece.mark_moved();
		this.m_chess_piece = null;
	}
	
	public void print_location() {
		if(this.has_piece())
			this.m_chess_piece.print_piece();
		else
			System.out.print(".");
	}
}
