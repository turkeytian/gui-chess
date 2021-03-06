
public class ChessPawnPiece extends ChessPiece {

	public ChessPawnPiece(int color) {
		super(color, PAWN);
	}
	
	@Override
	public void search_move_list(ChessBoard board,
			Player player, int from_row, int from_col) {
		if(this.m_color == Piece.WHITE) {
			int to_row, to_col;
			if(!this.has_been_moved()) {
				to_row = from_row-2;
				to_col = from_col;
				if(to_row >= 0 && to_row < board.get_rows()
						&& to_col >= 0 && to_col < board.get_cols()) {
					if(!board.get_locations()[to_row][to_col].has_piece())
						this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
			to_row = from_row-1;
			to_col = from_col;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(!board.get_locations()[to_row][to_col].has_piece())
					this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
			}
		}
		else {
			int to_row, to_col;
			if(!this.has_been_moved()) {
				to_row = from_row+2;
				to_col = from_col;
				if(to_row >= 0 && to_row < board.get_rows()
						&& to_col >= 0 && to_col < board.get_cols()) {
					if(!board.get_locations()[to_row][to_col].has_piece())
						this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
			to_row = from_row+1;
			to_col = from_col;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(!board.get_locations()[to_row][to_col].has_piece())
					this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
			}
		}
	}
	
	@Override
	public void search_kill_list(ChessBoard board,
			Player player, int from_row, int from_col) {
		if(this.m_color == Piece.WHITE) {
			int to_row = from_row-1, to_col = from_col+1;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(board.get_locations()[to_row][to_col].has_piece()) {
					if(board.get_locations()[to_row][to_col]
							.get_piece().getColor() != player.get_player_color())
						this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
			to_row = from_row-1;
			to_col = from_col-1;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(board.get_locations()[to_row][to_col].has_piece()) {
					if(board.get_locations()[to_row][to_col]
							.get_piece().getColor() != player.get_player_color())
						this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
		}
		else {
			int to_row = from_row+1, to_col = from_col+1;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(board.get_locations()[to_row][to_col].has_piece()) {
					if(board.get_locations()[to_row][to_col]
							.get_piece().getColor() != player.get_player_color())
						this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
			to_row = from_row+1;
			to_col = from_col-1;
			if(to_row >= 0 && to_row < board.get_rows()
					&& to_col >= 0 && to_col < board.get_cols()) {
				if(board.get_locations()[to_row][to_col].has_piece()) {
					if(board.get_locations()[to_row][to_col]
							.get_piece().getColor() != player.get_player_color())
						this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
				}
			}
		}
	}
}
