import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGraphBoard extends JPanel {
	private static final long serialVersionUID = 1;
	private ChessGame m_game;
	private boolean m_circled;
	private int m_status;
	private int m_row_selected;
	private int m_col_selected;
	private JLabel player_indicator, message_bar;
	
	GUIGraphBoard(ChessGame game) {
		this.m_game = game;
		this.m_circled = false;
		this.m_status = 0;
		this.m_row_selected = 0;
		this.m_col_selected = 0;

		this.m_game.get_all_moves();
		this.m_game.remove_suicide_moves();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getX() >= 50 && e.getX() <450
						&& e.getY() >= 50 && e.getY() < 450) {
					int row_new = e.getY()/50 - 1;
					int col_new = e.getX()/50 - 1;
					if(!m_circled) {
							if(m_game.is_own_piece_location(row_new, col_new) == 0) {
								m_circled = true;
								m_row_selected = row_new;
								m_col_selected = col_new;
								message_bar.setText("Please choose a location to place the piece.");
							}
							else if(m_game.is_own_piece_location
									(row_new, col_new) == 1)
								message_bar.setText("Please select a piece.");
							else
								message_bar.setText("You can only touch your own pieces.");
						}
					else {
						if(row_new == m_row_selected && col_new == m_col_selected) {
							m_circled = false;
							message_bar.setText("Please select a piece.");
						}
						else {
							int result = m_game.player_move(m_row_selected,
									m_col_selected, row_new, col_new);
							if(result == 1)
								message_bar.setText("Invalid move!");
							else {
								m_circled = false;
								message_bar.setText("Please select a piece.");
								m_game.check_promotion();
								m_game.switch_current_player();
								m_game.get_all_moves();
								m_game.remove_suicide_moves();
								m_status = m_game.check_ended();
								if(m_status == 1 || m_status == 3) {
									message_bar.setText("The game has ended.");
									removeMouseListener(this);
								}
							}
						}
					}
				}
				repaint();
			}
		});
		
		player_indicator = new JLabel();
		player_indicator.setForeground(Color.BLACK);
		player_indicator.setText("White's turn: ");
		add(player_indicator);
		
		message_bar = new JLabel();
		message_bar.setForeground(Color.BLACK);
		message_bar.setText("Please select a piece.");
		add(message_bar);
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawImage(new ImageIcon("background/left.jpg").getImage(),
				0, 0, this);
		graphics.drawImage(new ImageIcon("background/monitor.png").getImage(),
				30, 5, 440, 30, this);
		
		for(int i = 0; i < this.m_game.get_board().get_rows(); i++) {
			for(int j = 0; j < this.m_game.get_board().get_cols(); j++) {
					graphics.drawImage(new ImageIcon("background/t"+
							Integer.toString((i+j)%2)+".jpg").getImage(),
							50*(j+1), 50*(i+1), 50, 50, this);
					graphics.drawImage(new ImageIcon("icons/letters/Letter-"
					+Integer.toString(j+1)+"-icon.png").getImage(),
							10+(j+1)*50, 460, 30, 30, this);
					
					ChessLocation loc = this.m_game.get_board().get_locations()[i][j];
					if(loc.has_piece())
						graphics.drawImage(new ImageIcon("icons/pieces/"+
								Integer.toString(+loc.get_piece().getColor())
								+loc.get_piece().get_role()+".png").getImage(),
								50*(j+1), 50*(i+1), 50, 50, this);
			}
			graphics.drawImage(new ImageIcon("icons/numbers/Number-"
			+Integer.toString(i+1)+"-icon.png").getImage(),
					10, 10+(8-i)*50, 30, 30, this);
		}
		
		if(this.m_circled) {
			graphics.drawImage(new ImageIcon("icons/circle.png").getImage(),
					50*(m_col_selected+1), 50*(m_row_selected+1), 50, 50, this);
		}
		if(this.m_status == 1)
			this.player_indicator.setText("It's a draw: ");
		else {
			String indicator_str = "";
			if(this.m_game.m_current_player.get_player_color() == Piece.BLACK)
				indicator_str += "Black";
			else
				indicator_str += "White";
			if(this.m_status == 2)
				indicator_str += " (Checked)";
			else if(this.m_status == 3)
				indicator_str += " is checkmated";
			indicator_str += ": ";
			this.player_indicator.setText(indicator_str);
		}
	}
}
