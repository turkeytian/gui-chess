
public class ChessRookPiece extends ChessPiece {
	
	public ChessRookPiece(int color) {
		super(color, ROOK);
	}

	@Override
	public void search_move_list(ChessBoard board,
			Player player, int from_row, int from_col) {
		for(int to_row = from_row+1, to_col = from_col;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_row++) {
			if(board.get_locations()[to_row][to_col].has_piece())
				break;
			else
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
		
		for(int to_row = from_row-1, to_col = from_col;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_row--) {
			if(board.get_locations()[to_row][to_col].has_piece())
				break;
			else
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
		
		for(int to_row = from_row, to_col = from_col+1;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_col++) {
			if(board.get_locations()[to_row][to_col].has_piece())
				break;
			else
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
		
		for(int to_row = from_row, to_col = from_col-1;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_col--) {
			if(board.get_locations()[to_row][to_col].has_piece())
				break;
			else
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	}
	
	@Override
	public void search_kill_list(ChessBoard board,
			Player player, int from_row, int from_col) {		
		for(int to_row = from_row, to_col = from_col+1;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_col++) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color()) {
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
					break;
				}
			}
		}
		
		for(int to_row = from_row, to_col = from_col-1;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_col--) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color()) {
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
					break;
				}
			}
		}
		
		for(int to_row = from_row+1, to_col = from_col;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_row++) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color()) {
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
					break;
				}
			}
		}
		
		for(int to_row = from_row-1, to_col = from_col;
				to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols();
				to_row--) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color()) {
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
					break;
				}
			}
		}
	}
}
