
public class Move {
	protected int m_from_row;
	protected int m_from_col;
	protected int m_to_row;
	protected int m_to_col;
	
	Move(int from_row, int from_col,
			int to_row, int to_col) {
		this.m_from_row = from_row;
		this.m_from_col = from_col;
		this.m_to_row = to_row;
		this.m_to_col = to_col;
	}
}
