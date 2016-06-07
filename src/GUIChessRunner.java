import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIChessRunner extends JPanel {
	private static final long serialVersionUID = 1;
	
	public static void main(String[] args) {
		Scanner input_scanner = new Scanner(System.in);
		ChessGame game = new ChessGame(input_scanner);
		
		JFrame frame = new JFrame();
		Container pane = frame.getContentPane();
		pane.setPreferredSize(new Dimension(1000, 500));
		frame.pack();
		pane.setLayout(new GridLayout(0, 2));
		
		GUITextBoard history_board = new GUITextBoard();
		game.addObserver(history_board);
		pane.add(new GUIGraphBoard(game));
		pane.add(history_board);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI Chess by Ruoqi Tian");
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
