
public class Piece {
	public static final int BLACK = 1;
	public static final int WHITE = -1;
	protected int m_color;
	
	Piece(int color) {
		this.m_color = color;
	}

	public int getColor() {
		return m_color;
	}
	
	public String color_to_string() {
		if(this.m_color == BLACK)
			return "BLACK";
		else
			return "WHITE";
	}
}
