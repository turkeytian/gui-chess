
public class ChessMove extends Move {

	ChessMove(int from_row, int from_col,
			int to_row, int to_col) {
		super(from_row, from_col, to_row, to_col);
	}
	
	public String move_to_string() {
		char ch_from_row = (char)(56-this.m_from_row);
		char ch_from_col = (char)(this.m_from_col+65);
		char ch_to_row = (char)(56-this.m_to_row);
		char ch_to_col = (char)(this.m_to_col+65);
		return Character.toString(ch_from_col)+Character.toString(ch_from_row)+
				" To "+
				Character.toString(ch_to_col)+Character.toString(ch_to_row);
	}
	
	public int get_from_row() {
		return this.m_from_row;
	}
	
	public int get_from_col() {
		return this.m_from_col;
	}
	
	public int get_to_row() {
		return this.m_to_row;
	}
	
	public int get_to_col() {
		return this.m_to_col;
	}
}
