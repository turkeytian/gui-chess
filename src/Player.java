
public class Player {
	private int m_color;
	
	Player(int color) {
		this.m_color = color;
	}
	
	public String color_to_string() {
		if(this.m_color == Piece.BLACK)
			return "BLACK";
		else
			return "WHITE";
	}
	
	public int get_player_color() {
		return this.m_color;
	}
}
