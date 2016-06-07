import java.util.Vector;

public class ChessPiece extends Piece {
	public static final int PAWN = 0;
	public static final int KNIGHT = 1;
	public static final int BISHOP= 2;
	public static final int ROOK = 3;
	public static final int QUEEN = 4;
	public static final int KING = 5;
	private int m_role;
	private boolean m_moved;
	private Vector<ChessMove> m_move_list;
	private Vector<ChessMove> m_kill_list;
	private Vector<ChessMove> m_cast_list;
	
	ChessPiece(int color, int role) {
		super(color);
		this.m_role = role;
		this.m_moved = false;
		this.m_move_list = new Vector<ChessMove>();
		this.m_kill_list = new Vector<ChessMove>();
		this.m_cast_list = new Vector<ChessMove>();
	}
	
	public boolean has_been_moved() {
		return this.m_moved;
	}
	
	public void mark_moved() {
		this.m_moved = true;
	}
	
	public String role_to_string() {
		if(this.m_role == PAWN)
			return "PAWN";
		else if(this.m_role == KNIGHT)
			return "KNIGHT";
		else if(this.m_role == BISHOP)
			return "BISHOP";
		else if(this.m_role == ROOK)
			return "ROOK";
		else if(this.m_role == QUEEN)
			return "QUEEN";
		else
			return "KING";
	}
	
	public void print_piece() {
		if(this.m_color == BLACK) {
			if(this.m_role == PAWN)
				System.out.print("P");
			else if(this.m_role == KNIGHT)
				System.out.print("N");
			else if(this.m_role == BISHOP)
				System.out.print("B");
			else if(this.m_role == ROOK)
				System.out.print("R");
			else if(this.m_role == QUEEN)
				System.out.print("Q");
			else if(this.m_role == KING)
				System.out.print("K");
		}
		if(this.m_color == WHITE) {
			if(this.m_role == PAWN)
				System.out.print("p");
			else if(this.m_role == KNIGHT)
				System.out.print("n");
			else if(this.m_role == BISHOP)
				System.out.print("b");
			else if(this.m_role == ROOK)
				System.out.print("r");
			else if(this.m_role == QUEEN)
				System.out.print("q");
			else if(this.m_role == KING)
				System.out.print("k");
		}
	}
	
	public int get_role() {
		return this.m_role;
	}
	
	public Vector<ChessMove> get_moves() {
		return this.m_move_list;
	}
	
	public Vector<ChessMove> get_kills() {
		return this.m_kill_list;
	}
	
	public Vector<ChessMove> get_casts() {
		return this.m_cast_list;
	}
	
	public void add_move(ChessMove move) {
		this.m_move_list.addElement(move);
	}
	
	public void add_kill(ChessMove move) {
		this.m_kill_list.addElement(move);
	}
	
	public void add_cast(ChessMove move) {
		this.m_cast_list.addElement(move);
	}
	
	public boolean has_move(ChessMove move) {
		boolean has = false;
		for(int i = 0; i < this.m_move_list.size(); i++) {
			if(this.m_move_list.get(i).get_from_row() == move.get_from_row()
					&& this.m_move_list.get(i).get_from_col() == move.get_from_col()
					&& this.m_move_list.get(i).get_to_row() == move.get_to_row()
					&& this.m_move_list.get(i).get_to_col() == move.get_to_col())
				has = true;
		}
		return has;
	}
	
	public boolean has_kill(ChessMove move) {
		boolean has = false;
		for(int i = 0; i < this.m_kill_list.size(); i++) {
			if(this.m_kill_list.get(i).get_from_row() == move.get_from_row()
					&& this.m_kill_list.get(i).get_from_col() == move.get_from_col()
					&& this.m_kill_list.get(i).get_to_row() == move.get_to_row()
					&& this.m_kill_list.get(i).get_to_col() == move.get_to_col())
				has = true;
		}
		return has;
	}
	
	public boolean has_cast(ChessMove move) {
		boolean has = false;
		for(int i = 0; i < this.m_cast_list.size(); i++) {
			if(this.m_cast_list.get(i).get_from_row() == move.get_from_row()
					&& this.m_cast_list.get(i).get_from_col() == move.get_from_col()
					&& this.m_cast_list.get(i).get_to_row() == move.get_to_row()
					&& this.m_cast_list.get(i).get_to_col() == move.get_to_col())
				has = true;
		}
		return has;
	}
	
	public void search_move_list(ChessBoard board,
			Player player, int from_row, int from_col) {
	}
	
	public void search_kill_list(ChessBoard board,
			Player player, int from_row, int from_col) {
	}
	
	public void search_cast_list(ChessBoard board,
			Player player, int from_row, int from_col) {
	}
}
