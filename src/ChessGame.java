import java.util.Observable;
import java.util.Scanner;
import java.util.Vector;

public class ChessGame extends Observable {
	private ChessBoard m_chess_board;
	protected Player m_player1;
	protected Player m_player2;
	protected Player m_current_player;
	
	ChessGame(Scanner input_scanner) {
		this.m_player1 = new Player(Piece.WHITE);
		this.m_player2 = new Player(Piece.BLACK);
		this.m_chess_board = new ChessBoard("ChessBoard.txt", 8, 8);
		this.m_current_player = this.m_player1;
	}
	
	public void switch_current_player() {
		if(this.m_current_player == this.m_player1)
			this.m_current_player = this.m_player2;
		else
			this.m_current_player = this.m_player1;
	}
	
	public ChessBoard get_board() {
		return this.m_chess_board;
	}
	
	public int player_move(int from_row, int from_col, int to_row, int to_col) {
		int result = 1;
		ChessMove move = new ChessMove(from_row, from_col, to_row, to_col);
		ChessLocation loc = this.m_chess_board.get_locations()
				[move.get_from_row()][move.get_from_col()];

		if(loc.get_piece().has_move(move)) {
			apply_move(move, this.m_chess_board, true);
			result = 0;
		}
		else if(loc.get_piece().has_kill(move)) {
			apply_kill(move, this.m_chess_board, true);
			result = 0;
		}
		else if(loc.get_piece().has_cast(move)) {
			apply_cast(move, this.m_chess_board, true);
			result = 0;
		}
		else
			result = 1;
		
		return result;
}
	
	
	public void get_all_moves() {
		Player player = this.m_current_player;
		for(int i = 0; i < this.m_chess_board.get_rows(); i++) {
			for(int j = 0; j < this.m_chess_board.get_cols(); j++) {
				if(this.m_chess_board.get_locations()[i][j].has_piece()) {
					this.m_chess_board.get_locations()[i][j]
							.get_piece().get_moves().removeAllElements();
					this.m_chess_board.get_locations()[i][j]
							.get_piece().get_kills().removeAllElements();
					this.m_chess_board.get_locations()[i][j]
							.get_piece().get_casts().removeAllElements();
					if(this.m_chess_board.get_locations()[i][j]
							.get_piece().getColor() == player.get_player_color()) {
						this.m_chess_board.get_locations()[i][j]
								.get_piece().search_move_list(m_chess_board, player, i, j);
						this.m_chess_board.get_locations()[i][j]
								.get_piece().search_kill_list(m_chess_board, player, i, j);
						this.m_chess_board.get_locations()[i][j]
								.get_piece().search_cast_list(m_chess_board, player, i, j);
						
					}
				}
			}
		}
	}

	public void remove_suicide_moves() {
		Player player = this.m_current_player;
		for(int i = 0; i < this.m_chess_board.get_rows(); i++) {
			for(int j = 0; j < this.m_chess_board.get_cols(); j++) {
				if(this.m_chess_board.get_locations()[i][j].has_piece()) {
					if(this.m_chess_board.get_locations()[i][j]
							.get_piece().getColor() == player.get_player_color()) {
						Vector<ChessMove> remove_moves = new Vector<ChessMove>();
						for(int k = 0; k < this.m_chess_board.get_locations()[i][j]
								.get_piece().get_moves().size(); k++) {
							ChessBoard sim_board = new ChessBoard(this.m_chess_board);
							apply_move(this.m_chess_board.get_locations()[i][j]
									.get_piece().get_moves().get(k), sim_board, false);
							if(is_check(player, sim_board))
								remove_moves.addElement(this.m_chess_board.get_locations()[i][j]
										.get_piece().get_moves().get(k));
						}
						this.m_chess_board.get_locations()[i][j]
								.get_piece().get_moves().
								removeAll(remove_moves);
						
						Vector<ChessMove> remove_kills = new Vector<ChessMove>();
						for(int k = 0; k < this.m_chess_board.get_locations()[i][j]
								.get_piece().get_kills().size(); k++) {
							ChessBoard sim_board = new ChessBoard(this.m_chess_board);
							apply_kill(this.m_chess_board.get_locations()[i][j]
									.get_piece().get_kills().get(k), sim_board, false);
							if(is_check(player, sim_board))
										remove_kills.addElement(this.m_chess_board.get_locations()[i][j]
												.get_piece().get_kills().get(k));
						}
						this.m_chess_board.get_locations()[i][j]
								.get_piece().get_kills().
								removeAll(remove_kills);
						
						Vector<ChessMove> remove_casts = new Vector<ChessMove>();
						for(int k = 0; k < this.m_chess_board.get_locations()[i][j]
								.get_piece().get_casts().size(); k++) {
							ChessBoard sim_board = new ChessBoard(this.m_chess_board);
							apply_cast(this.m_chess_board.get_locations()[i][j]
									.get_piece().get_casts().get(k), sim_board, false);
							if(is_check(player, sim_board))
								remove_casts.addElement(this.m_chess_board.get_locations()[i][j]
										.get_piece().get_casts().get(k));
						}
						this.m_chess_board.get_locations()[i][j]
								.get_piece().get_casts().
								removeAll(remove_casts);
					}
				}
			}
		}
	}
				
	public void apply_move(ChessMove move, ChessBoard board, Boolean notify) {
		String str = this.m_current_player.color_to_string()+" ";
		ChessPiece piece = board.get_locations()
				[move.get_from_row()][move.get_from_col()].get_piece();
		str+=piece.role_to_string()+" ";
		board.get_locations()
		[move.get_from_row()][move.get_from_col()].take_piece();
		board.get_locations()
		[move.get_to_row()][move.get_to_col()].place_piece(piece);
		str+=move.move_to_string();
		if(notify) {
			setChanged();
			notifyObservers(str);
		}
	}
	
	public void apply_kill(ChessMove move, ChessBoard board, Boolean notify) {
		String str = this.m_current_player.color_to_string()+" ";
		ChessPiece piece = board.get_locations()
				[move.get_from_row()][move.get_from_col()].get_piece();
		str+=piece.role_to_string()+": ";
		board.get_locations()
		[move.get_from_row()][move.get_from_col()].take_piece();
		ChessPiece killed_piece = board.get_locations()
				[move.get_to_row()][move.get_to_col()].get_piece();
		board.get_locations()
		[move.get_to_row()][move.get_to_col()].place_piece(piece);
		str+=move.move_to_string();
		if(notify) {
			setChanged();
			notifyObservers(str);
			setChanged();
			notifyObservers("A "+killed_piece.color_to_string()+" "
			+killed_piece.role_to_string()
			+" has has been eliminated.");
		}
	}
	
	public void apply_cast(ChessMove move, ChessBoard board, boolean notify) {
		String str = this.m_current_player.color_to_string()+" ";
		ChessPiece piece = board.get_locations()
				[move.get_from_row()][move.get_from_col()].get_piece();
		str+=piece.role_to_string()+": ";
		board.get_locations()
		[move.get_from_row()][move.get_from_col()].take_piece();
		board.get_locations()
		[move.get_to_row()][move.get_to_col()].place_piece(piece);
		if(move.get_from_col() < move.get_to_col()) {
			piece = board.get_locations()
					[move.get_from_row()][7].get_piece();
			board.get_locations()
			[move.get_to_row()][7].take_piece();
			board.get_locations()
			[move.get_to_row()][5].place_piece(piece);
		}
		else {
			piece = board.get_locations()
					[move.get_from_row()][0].get_piece();
			board.get_locations()
			[move.get_to_row()][0].take_piece();
			board.get_locations()
			[move.get_to_row()][3].place_piece(piece);
		}
		str+=move.move_to_string();
		if(notify) {
			setChanged();
			notifyObservers(str+" (Castling move)");
		}
	}
	
	public void check_promotion() {
		for(int i = 0; i < this.m_chess_board.get_cols(); i++) {
			if(this.m_chess_board.get_locations()[0][i].has_piece()) {
				if(this.m_chess_board.get_locations()[0][i]
						.get_piece().getColor() == Piece.WHITE
						&& this.m_chess_board.get_locations()[0][i]
								.get_piece().get_role() == ChessPiece.PAWN) {
					this.m_chess_board.get_locations()[0][i].take_piece();
					this.m_chess_board.get_locations()[0][i]
							.place_piece(new ChessQueenPiece(Piece.WHITE));
					setChanged();
					notifyObservers("A WHITE PAWN was promoted to a QUEEN.");
				}
			}
			
			if(this.m_chess_board.get_locations()[7][i].has_piece()) {
				if(this.m_chess_board.get_locations()[7][i]
						.get_piece().getColor() == Piece.BLACK
						&& this.m_chess_board.get_locations()[7][i]
								.get_piece().get_role() == ChessPiece.PAWN) {
					this.m_chess_board.get_locations()[7][i].take_piece();
					this.m_chess_board.get_locations()[7][i]
							.place_piece(new ChessQueenPiece(Piece.BLACK));
					setChanged();
					notifyObservers("A BLACK PAWN was promoted to a QUEEN.");
				}
			}
		}
	}
	
	public int check_ended() {
		Player player = this.m_current_player;
		int status = 0;
		if(is_check(player, this.m_chess_board)
				&& no_legal_move(player, this.m_chess_board))
			status = 3;
		else if(is_check(player, this.m_chess_board)
				&& !no_legal_move(player, this.m_chess_board))
			status = 2;
		else if(!is_check(player, this.m_chess_board)
				&& no_legal_move(player, this.m_chess_board))
			status = 1;

		return status;
	}

	public boolean no_legal_move(Player player, ChessBoard board) {
		for(int i = 0; i < board.get_rows(); i++) {
			for(int j = 0; j < board.get_cols(); j++) {
				if(board.get_locations()[i][j].has_piece()) {
					if(board.get_locations()[i][j]
							.get_piece().getColor() == player.get_player_color()) {
						if(!board.get_locations()[i][j]
								.get_piece().get_kills().isEmpty()
								|| !board.get_locations()[i][j]
										.get_piece().get_moves().isEmpty()
										|| !board.get_locations()[i][j]
												.get_piece().get_casts().isEmpty())
							return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean is_check(Player checked_player, ChessBoard board) {
		for(int i = 0; i < board.get_rows(); i++) {
			for(int j = 0; j < board.get_cols(); j++) {
				if(board.get_locations()[i][j].has_piece()) {
					if(board.get_locations()[i][j]
							.get_piece().getColor() != checked_player.get_player_color()) {
						board.get_locations()[i][j]
								.get_piece().search_kill_list
								(board, new Player(checked_player.get_player_color()*(-1)), i, j);
						for(int k = 0; k < board.get_locations()[i][j]
								.get_piece().get_kills().size(); k++) {
							int row = board.get_locations()[i][j]
									.get_piece().get_kills().get(k).m_to_row;
							int col = board.get_locations()[i][j]
									.get_piece().get_kills().get(k).m_to_col;
							if(board.get_locations()[row][col]
									.equals(get_kings_location(checked_player, board))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public ChessLocation get_kings_location(Player player, ChessBoard board) {
		for(int i = 0; i < board.get_rows(); i++) {
			for(int j = 0; j < board.get_cols(); j++) {
				if(board.get_locations()[i][j].has_piece()) {
					if(board.get_locations()[i][j]
							.get_piece().get_role() == ChessPiece.KING
							&& board.get_locations()[i][j]
									.get_piece().getColor() == player.get_player_color()) {
						return board.get_locations()[i][j];
					}
				}
			}
		}
		return null;
	}

	public int is_own_piece_location(int row, int col) {
		ChessLocation loc = this.m_chess_board.get_locations()[row][col];
		if(!loc.has_piece())
			return 1;
		else if(loc.get_piece().getColor() != m_current_player.get_player_color())
			return 2;
		else
			return 0;
	}
}
