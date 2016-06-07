
public class ChessKingPiece extends ChessPiece {

	public ChessKingPiece(int color) {
		super(color, KING);
	}
	
	@Override
	public void search_move_list(ChessBoard board,
			Player player, int from_row, int from_col) {
		int to_row = from_row+1, to_col = from_col+1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
		
		to_row = from_row+1;
		to_col = from_col;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
		
		to_row = from_row+1;
		to_col = from_col-1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	
		to_row = from_row;
		to_col = from_col+1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	
		to_row = from_row;
		to_col = from_col-1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	
		to_row = from_row-1;
		to_col = from_col+1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	
		to_row = from_row-1;
		to_col = from_col;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	
		to_row = from_row-1;
		to_col = from_col-1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(!board.get_locations()[to_row][to_col].has_piece())
				this.add_move(new ChessMove(from_row, from_col, to_row, to_col));
		}
	}
	
	@Override
	public void search_cast_list(ChessBoard board,
			Player player, int from_row, int from_col) {
		if(from_col == 4 && (from_row == 0 || from_row == 7)) {
			if(!board.get_locations()[from_row][from_col]
					.get_piece().has_been_moved()) {
				for(int i = 3; i >= 0; i--) {
					if(i == 0) {
						if(board.get_locations()[from_row][i].has_piece()) {
							if(!board.get_locations()[from_row][i]
									.get_piece().has_been_moved()) {
								this.add_cast(new ChessMove(from_row, from_col, from_row, from_col-2));
							}
						}
					}
					else if(board.get_locations()[from_row][i].has_piece())
							break;
				}
				
				for(int i = 5; i < board.get_cols(); i++) {
					if(i == 7) {
						if(board.get_locations()[from_row][i].has_piece()) {
							if(!board.get_locations()[from_row][i]
									.get_piece().has_been_moved()) {
								this.add_cast(new ChessMove(from_row, from_col, from_row, from_col+2));
							}
						}
					}
					else if(board.get_locations()[from_row][i].has_piece())
						break;
				}
			}
		}
	}
	
	@Override
	public void search_kill_list(ChessBoard board,
			Player player, int from_row, int from_col) {
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
		to_col = from_col;
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
		
		to_row = from_row;
		to_col = from_col+1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color())
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
			}
		}
		to_row = from_row;
		to_col = from_col-1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color())
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
			}
		}
		to_row = from_row-1;
		to_col = from_col+1;
		if(to_row >= 0 && to_row < board.get_rows()
				&& to_col >= 0 && to_col < board.get_cols()) {
			if(board.get_locations()[to_row][to_col].has_piece()) {
				if(board.get_locations()[to_row][to_col]
						.get_piece().getColor() != player.get_player_color())
					this.add_kill(new ChessMove(from_row, from_col, to_row, to_col));
			}
		}
		to_row = from_row-1;
		to_col = from_col;
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
}